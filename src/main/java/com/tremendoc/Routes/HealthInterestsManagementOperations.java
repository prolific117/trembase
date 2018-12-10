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
import com.tremendoc.response.VerifyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(description = "Set of endpoints for adding new health interests to the service.")
@RequestMapping(path="admin") // This means URL's start with /demo (after Application path)
public class HealthInterestsManagementOperations {
    
    @Autowired
    private HealthInterestsController healthInterestsController;
    
    private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
       
        @ApiOperation("create an health interest (for admin)")
        @RequestMapping(value = "/health/interests", method = RequestMethod.POST, 
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse createHealthInterest (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "name", required = false, value = "name of the health interest e.g Childbirth, Skin", defaultValue = "") @RequestParam("name") String name,                        
                @ApiParam(name = "description", required = false, value = "description of health interest. 200 words or less", defaultValue = "") @RequestParam("description") String description,                        
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       healthInterestsController.addHealthInterests(name, description, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("create an health interest (for admin)")
        @RequestMapping(value = "/health/interests", method = RequestMethod.PUT, 
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse createHealthInterest (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "id", required = false, value = "id of health interest to be edited", defaultValue = "") @RequestParam("id") Long id,                        
                @ApiParam(name = "name", required = false, value = "name of the health interest e.g Childbirth, Skin", defaultValue = "") @RequestParam("name") String name,                        
                @ApiParam(name = "description", required = false, value = "description of health interest. 200 words or less", defaultValue = "") @RequestParam("description") String description,                        
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       healthInterestsController.editHealthInterests(id, name, description, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("pull health interest (for admin)")
        @RequestMapping(value = "/health/interests", method = RequestMethod.GET, 
                produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse pullAllHealthInterests (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       healthInterestsController.getHealthInterests(sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("Remove an health interest.")
        @RequestMapping(value = "/health/interests/{id}", method = RequestMethod.DELETE,
        produces = {MediaType.APPLICATION_JSON_VALUE})
        public ServiceResponse verifyAdministratorSession(
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail,
                @PathVariable("id") Long id) throws Exception {
               return healthInterestsController.removeHealthInterests(id, sessionDetail);
        }
}
