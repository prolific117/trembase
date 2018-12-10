/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.HealthInterestsController;
import com.tremendoc.Request.SessionDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description = "Set of endpoints for adding and editing health interests for a user.")
@RequestMapping(path="customer") // This means URL's start with /demo (after Application path)
public class HealthInterestsOperations {
    
    @Autowired
    private HealthInterestsController healthInterestsController;
    
    private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
        
        @ApiOperation("create or update customer health interest")
        @RequestMapping(value = "/health/interests", method = RequestMethod.POST, 
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse createCustomerHealthInterests (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "interests", required = false, value = "commas seperated list of health interests ids i.e 2,6,7,1", defaultValue = "") @RequestParam("interests") String interests,                        
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       healthInterestsController.createCustomerHealthInterests(interests, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("pull all health interests")
        @RequestMapping(value = "/health/interests", method = RequestMethod.GET, 
                produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse pullCustomerHealthInterests (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       healthInterestsController.getCustomerHealthInterests(sessionDetail);
                       
		return response;
	}
 
}
