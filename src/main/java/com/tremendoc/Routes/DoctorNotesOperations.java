/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorNotesController;
import com.tremendoc.Controllers.Doctors.DoctorPrescriptionsController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CustomerNotesResponse;
import com.tremendoc.response.CustomerPrescriptionsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @author olatunji.oduro
 */
@RestController
@Api("Set of endpoints for Managing Doctor Notes.")
@RequestMapping(path="/doctor_notes") // This means URL's start with /demo (after Application path)
public class DoctorNotesOperations {
    
    @Autowired
    private DoctorNotesController doctorNotesController;
    
    @ApiOperation("Add a note")
    @RequestMapping(value = "/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse pregnancyProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "symptoms", required = true, value = "observed symptoms", defaultValue = "") @RequestParam("symptoms") String symptoms,
		@ApiParam(name = "diagnosis", required = true, value = "derived diagnosis", defaultValue = "") @RequestParam("diagnosis") String diagnosis,
                @ApiParam(name = "treatment", required = false, value = "suggested treatment", defaultValue = "") @RequestParam("treatment") String treatment,
                @ApiParam(name = "patientId", required = true, value = "patient id", defaultValue = "") @RequestParam("patientId") String patientId,
                @ApiParam(name = "consultationId", required = true, value = "consultation id", defaultValue = "") @RequestParam("consultationId") String consultationId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                ServiceResponse response =  doctorNotesController.addNote(symptoms, diagnosis, treatment, Long.valueOf(patientId), Long.valueOf(consultationId), sessionDetail);
		return response;
	}
        
    @ApiOperation("Get patient notes")
    @RequestMapping(value = "/patient/{patientId}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerNotesResponse getCustomerPrescriptions (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("patientId") String patientId){
                       
                CustomerNotesResponse response =doctorNotesController.getNotes(Long.valueOf(patientId));
		return response;
	}
}
