/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Routes;

/**
 *
 * @author prolific
 */

import com.tremendoc.Controllers.Customers.CustomerManagementController;
import com.tremendoc.Controllers.Customers.CustomerOverviewController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CustomerOverviewResponse;
import com.proxy.leanstack.commons.security.Secured;
import com.proxy.leanstack.commons.util.RequestUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/customers")
public class CustomerOverview {
    
     @Autowired
     private CustomerOverviewController customerOverviewController;
     
     @Autowired
     private CustomerManagementController customerManagementController;
       
     private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
	
     @Secured
     @GetMapping(path="/overview")
	public CustomerOverviewResponse getOverview(@RequestAttribute(name = "session_detail") SessionDetail sessionDetail) {
		// This returns a JSON or XML with the users
		return customerOverviewController.getOverview(sessionDetail);
     }
        
     @GetMapping(path="/all")
     public @ResponseBody Iterable<Customer> getAllUsers(@RequestAttribute(name = "session_detail") SessionDetail sessionDetail) {
               // This returns a JSON or XML with the users
               return customerOverviewController.getAll(sessionDetail);
     }
}
