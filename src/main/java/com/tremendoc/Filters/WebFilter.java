/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Filters;

/**
 *
 * @author prolific
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tremendoc.Controllers.Customers.CustomerManagementController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.security.impl.SessionDetailProvider;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
    
	private static final boolean CONDITION = true;
        
        private static final String UNAUTHENTICATED_MESSAGE = "Authorization credentials invalid";
        
        @Autowired
        private SessionDetailProvider sessionDetailProvider;
        
        @Autowired
        private SessionDetail sessionDetail;
    
        
        @Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initiating WebFilter >> ");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
            
             final HttpServletResponse response = (HttpServletResponse) res;
             response.setHeader("Access-Control-Allow-Origin", "*");
             response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
             response.setHeader("Access-Control-Allow-Headers", "sessionId, Authorization, Content-Type");
             response.setHeader("Access-Control-Max-Age", "3600");
                    
		if (CONDITION == true) {
                    
                        String path = ((HttpServletRequest) request).getServletPath();
                        LOGGER.info(path);
                        if( path.startsWith("/swagger-ui.html") 
                                || path.startsWith("/webjars") 
                                || path.startsWith("/v2/api-docs")
                                || path.startsWith("/csrf")
                                || path.startsWith("/specialties/get")
                                || path.startsWith("/doctor/authenticate")
                                || path.startsWith("/doctor/create")
                                || path.startsWith("/doctor/reset")
                                || path.startsWith("/faq")
                                || path.startsWith("/countries")
                                || path.startsWith("/healthtip")
                                || path.startsWith("/languages")
                                || path.startsWith("/doctor/complete/reset")
                                || path.startsWith("/account")
                                || path.startsWith("/swagger-resources")){
                          chain.doFilter(request, response); 

                          return;
                         } 
                        
                        HttpServletRequest req = (HttpServletRequest) request;
                        Map<String, String> map = new HashMap<String, String>();

                        Enumeration headerNames = req.getHeaderNames();
                        while (headerNames.hasMoreElements()) {
                           String key = (String) headerNames.nextElement();
                           String value = req.getHeader(key);
                           map.put(key, value);
                        }
                        
                        HeaderMapRequestWrapper requestWrapper = new 
					HeaderMapRequestWrapper(req);
			String session_id = map.getOrDefault("sessionid","xxxxxxx");
			requestWrapper.addHeader("sessionid", session_id);
                        
                        sessionDetail = sessionDetailProvider.getSession(session_id); 
                        
                        String[] split = session_id.split("@");
                        /*if(!"ADMIN".equals(split[0])){
                             String newContent = "{\"code\" : \"10\", \"description\" : \"Authorization Error, you are not authorized\" }";
                             response.setContentLength(newContent .length());
                             response.getWriter().write(newContent);
                        }*/
                        
                        if(sessionDetail == null || !sessionDetail.getIsActive()){
                             String newContent = "{\"code\" : \"10\", \"description\" : \"Authentication Error, sessionid expired or absent\" }";
                             response.setContentLength(newContent .length());
                             response.getWriter().write(newContent);
                             
                             return;
                         }
      
                       
                        requestWrapper.setAttribute("session_detail", sessionDetail);
                        // Goes to default servlet
		        chain.doFilter(requestWrapper, response); 
                   } else {
                         LOGGER.info("Session id not provided");
                         String newContent = "{\"code\" : \"10\", \"description\" : \"Authorization Error, provide sessionId\" }";
                         response.setContentLength(newContent .length());
                         response.getWriter().write(newContent);
  
		}
	}
        
      
	@Override
	public void destroy() {
		LOGGER.debug("Destroying WebFilter >> ");
	}
        
      
}