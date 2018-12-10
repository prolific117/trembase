/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.NotificationController;
import com.tremendoc.Request.SessionDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author prolific
 */
@RestController
@Api(description = "Set of endpoints for Managing Customer Notifications.")
@RequestMapping(path="/customer") // This means URL's start with /demo (after Application path)
public class NotificationOperations {
    
        
        @Autowired
        NotificationController notificationController;
        
        private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
        
        @ApiOperation("Retrieve all customer notifications")
        @RequestMapping(value = "/notifications", method = RequestMethod.GET, params = "start",
                  produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse treatmentsProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @RequestParam(name="start") final int start,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       notificationController.getNotifications(sessionDetail, 0);
                       
		return response;
	}
	
}
