/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.CustomerAuthenticationController;
import com.tremendoc.Controllers.Customers.CustomerManagementController;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.VerifyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prolific
 */
    // This means that this class is a Controller
@RestController
@Api(description = "Set of endpoints for Creating, Retrieving and Authentication Customers.")
@RequestMapping(path="/account") // This means URL's start with /demo (after Application path)
public class CustomerManagement {
        
        @Autowired
        private CustomerManagementController customerManagementController;
        
        @Autowired
        private CustomerAuthenticationController customerAuthenticationController;
        
        private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
	
        @ApiOperation("Creates Users on the tremendoc platform.")
        @RequestMapping(value = "/create", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerCreationResponse addNewUser (
                @ApiParam(name = "firstname", required = true, value = "User's first name", defaultValue = "") @RequestParam("firstname") String firstname,
		@ApiParam(name = "lastname", required = true, value = "User's last name", defaultValue = "") @RequestParam("lastname") String lastname,
                @ApiParam(name = "gender", required = true, value = "User's gender", defaultValue = "") @RequestParam("gender") String gender,
                @ApiParam(name = "age", required = true, value = "User's age", defaultValue = "") @RequestParam("age") String age,
                @ApiParam(name = "phone", required = true, value = "User's phone", defaultValue = "") @RequestParam("phone") String phone,
                @ApiParam(name = "email", required = true, value = "User's email", defaultValue = "") @RequestParam("email") String email,                        
                @ApiParam(name = "password", required = true, value = "password", defaultValue = "") @RequestParam("password") String password){
                    CustomerCreationResponse response = 
                       customerManagementController.createCustomer(firstname,lastname,gender,age,phone,email,password);
                       
		return response;
	}
        
        @ApiOperation("Authenticates Users on the tremendoc platform.")
        @RequestMapping(value = "/authenticate", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerCreationResponse authenticateUser (  
                @ApiParam(name = "email", required = true, value = "User's email", defaultValue = "") @RequestParam("email") String email,                        
                @ApiParam(name = "password", required = true, value = "password", defaultValue = "") @RequestParam("password") String password){
             
		
            CustomerCreationResponse response = 
                       customerManagementController.authenticateCustomer(email,password);
        	return response;
	}
        
        @ApiOperation("Initiate password reset.")
        @RequestMapping(value = "/reset", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse resetUser (  
                @ApiParam(name = "email", required = true, value = "User's email", defaultValue = "") @RequestParam("email") String email){
             
		
            ServiceResponse response = 
                       customerAuthenticationController.resetPassword(email);
            
            return response;
	}
        
        @ApiOperation("Complete password reset.")
        @RequestMapping(value = "/complete/reset", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse authenticateUser (  
                @ApiParam(name = "email", required = true, value = "User's email", defaultValue = "") @RequestParam("email") String email,
                @ApiParam(name = "token", required = true, value = "token", defaultValue = "") @RequestParam("token") String token,
                @ApiParam(name = "password", required = true, value = "User's new password", defaultValue = "") @RequestParam("password") String password){
             
	       ServiceResponse response = 
                       customerAuthenticationController.completeReset(email, token, password);
            
            return response;
	}
        
        @ApiOperation("Verifes Users Sessions on the tremendoc platform.")
        @RequestMapping(value = "/session/verify/{sessionId}", method = RequestMethod.GET,
        produces = {MediaType.APPLICATION_JSON_VALUE})
        public VerifyResponse verifyCustomerSession(@PathVariable("sessionId") String sessionId) throws Exception {
           return customerManagementController.verifySession(sessionId);
        }	
  }

