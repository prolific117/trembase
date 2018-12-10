/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.FAQController;
import com.tremendoc.Controllers.Admin.HealthTipsController;
import com.tremendoc.Controllers.Doctors.SpecialtiesController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.FAQResponse;
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
@Api(description = "Set of endpoints for managing FAQs.")
@RequestMapping(path="") // This means URL's start with /demo (after Application path)
public class FAQOperations {
    
    @Autowired
    FAQController faqController;
    
    @ApiOperation("Add an faq")
    @RequestMapping(value = "/faq/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addFaq (
                //@RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "question", required = true, value = "question", defaultValue = "") @RequestParam("question") String question,
	        @ApiParam(name = "answer", required = true, value = "answer", defaultValue = "") @RequestParam("answer") String answer)
                //@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail)
                {
                   
                ServiceResponse response =  faqController.createorEditFAQ(Long.valueOf(0), question, answer);
		return response; 
     }
     
    @ApiOperation("Edit an faq")
    @RequestMapping(value = "/faq/edit", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse editFaq (
                //@RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "id", required = false, value = "id of faq", defaultValue = "0") @RequestParam("id") String id,
		 @ApiParam(name = "question", required = true, value = "question", defaultValue = "") @RequestParam("question") String question,
	        @ApiParam(name = "answer", required = true, value = "answer", defaultValue = "") @RequestParam("answer") String answer)
                //@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail)
                {
                   
                   
                ServiceResponse response =  faqController.createorEditFAQ(Long.valueOf(id), question, answer);
		return response; 
     }
       
    @ApiOperation("Remove an faq")
    @RequestMapping(value = "/faq/{id}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse removeFaq (
                //@RequestHeader(name = "sessionid", required = true) final String sessionId,
                 @PathVariable("id") String id
		//@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail)
                )  {
                   
                   
                ServiceResponse response =  faqController.removeFAQ(Long.valueOf(id));
		return response; 
     }
    
        
    @ApiOperation("Retrieve FAQs")
    @GetMapping(path="/faqs/get")
    public FAQResponse getFAQ() {
            // This returns a JSON or XML with the users
            return faqController.getFAQ();
     }
}