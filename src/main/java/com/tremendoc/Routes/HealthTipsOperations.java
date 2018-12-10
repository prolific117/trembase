/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.HealthTipsController;
import com.tremendoc.Controllers.Doctors.SpecialtiesController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.HealthTips;
import com.tremendoc.response.SpecialtiesResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @author olatunji.oduro
 */
@RestController
@Api(description = "Set of endpoints for managing health tips.")
@RequestMapping(path="") // This means URL's start with /demo (after Application path)
public class HealthTipsOperations {
    
    @Autowired
    HealthTipsController healthTipsController;
    
    @ApiOperation("Add an health tip")
    @RequestMapping(value = "/healthtip/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addTip (
                //@RequestHeader(name = "sessionid", required = true) final String sessionId,
               @ApiParam(name = "body", required = true, value = "body of health tip, html preferably", defaultValue = "") @RequestParam("body") String body,
	        @ApiParam(name = "summary", required = true, value = "summary of health tip", defaultValue = "") @RequestParam("summary") String summary,
	        @ApiParam(name = "title", required = false, value = "title of health tip", defaultValue = "") @RequestParam("title") String title,
		@ApiParam(name = "imagePath", required = true, value = "image path", defaultValue = "") @RequestParam("imagePath") String imagePath)
                //@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail)
                {
                   
                ServiceResponse response =  healthTipsController.createorEditHealthTip(Long.valueOf(0), body, summary, title, imagePath);
		return response; 
     }
     
     @ApiOperation("Edit an health tip")
    @RequestMapping(value = "/healthtip/edit", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse editTip (
                //@RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "id", required = false, value = "id of health tip", defaultValue = "0") @RequestParam("id") String id,
		@ApiParam(name = "body", required = true, value = "body of health tip, html preferably", defaultValue = "") @RequestParam("body") String body,
	        @ApiParam(name = "summary", required = true, value = "summary of health tip", defaultValue = "") @RequestParam("summary") String summary,
	        @ApiParam(name = "title", required = false, value = "title of health tip", defaultValue = "") @RequestParam("title") String title,
		@ApiParam(name = "imagePath", required = true, value = "image path", defaultValue = "") @RequestParam("imagePath") String imagePath)
                //@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail)
                {
                   
                ServiceResponse response =  healthTipsController.createorEditHealthTip(Long.valueOf(id), body, summary, title, imagePath);
		return response; 
     }
     
     @ApiOperation("Like an health tip")
    @RequestMapping(value = "/healthtip/like", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addLike (
                //@RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "id", required = false, value = "id of health tip", defaultValue = "") @RequestParam("id") String id)
		//@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail)
                {
                   
                ServiceResponse response =  healthTipsController.likeHealthTip(Long.valueOf(id));
		return response; 
     }
        
    @ApiOperation("Remove an health tip")
    @RequestMapping(value = "/healthtip/{id}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse removeTip (
                //@RequestHeader(name = "sessionid", required = true) final String sessionId,
                 @PathVariable("id") String id
		//@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail)
                )  {
                   
                   
                ServiceResponse response =  healthTipsController.removeHealthTip(Long.valueOf(id));
		return response; 
     }
        
    @ApiOperation("Retrieve tips")
    @GetMapping(path="/healthtips/get")
    public HealthTips getSpecialties(@RequestParam("page") int start) {
            // This returns a JSON or XML with the users
            return healthTipsController.getHealthTips(start);
     }
}