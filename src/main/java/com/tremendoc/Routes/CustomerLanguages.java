/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.CustomerLanguagesController;
import com.tremendoc.Controllers.Customers.MedicationProfileController;
import com.tremendoc.Request.SessionDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description = "Set of endpoints for Managing Customer Languages.")
@RequestMapping(path="/customer") // This means URL's start with /demo (after Application path)

public class CustomerLanguages {
    
      @Autowired
      private CustomerLanguagesController customerLanguagesController;
    
      @ApiOperation("Create Languages")
      @RequestMapping(value = "/languages", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse setLanguages (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "english", required = true, value = "english", defaultValue = "") @RequestParam("english") boolean english,
	        @ApiParam(name = "yoruba", required = true, value = "yoruba", defaultValue = "") @RequestParam("yoruba") boolean yoruba,
	        @ApiParam(name = "ibo", required = true, value = "ibo", defaultValue = "") @RequestParam("ibo") boolean ibo,
	        @ApiParam(name = "hausa", required = true, value = "hausa", defaultValue = "") @RequestParam("hausa") boolean hausa,
	        @ApiParam(name = "french", required = true, value = "french", defaultValue = "") @RequestParam("french") boolean french,
		@ApiParam(name = "spanish", required = true, value = "spanish", defaultValue = "") @RequestParam("spanish") boolean spanish,
	        @ApiParam(name = "pidgin", required = true, value = "pidgin", defaultValue = "") @RequestParam("pidgin") boolean pidgin,
	        @ApiParam(name = "portuguese", required = true, value = "portuguese", defaultValue = "") @RequestParam("portuguese") boolean portuguese,
		@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       customerLanguagesController.setCustomerLanguages(english, yoruba, ibo, hausa, french, pidgin, spanish, portuguese, sessionDetail);
                       
		return response;
	}
        
      @ApiOperation("Get Languages")
      @RequestMapping(value = "/languages", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse getLanguages (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       customerLanguagesController.getCustomerLanguages(sessionDetail);
                       
		return response;
	}
}
