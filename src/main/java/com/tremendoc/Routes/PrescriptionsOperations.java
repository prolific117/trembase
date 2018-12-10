/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorPrescriptionsController;
import com.tremendoc.Request.SessionDetail;
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
@Api("Set of endpoints for Managing Prescriptions.")
@RequestMapping(path="/prescriptions") // This means URL's start with /demo (after Application path)
public class PrescriptionsOperations {
    
    @Autowired
    private DoctorPrescriptionsController doctorPrescriptionsController;
    
    @ApiOperation("Add a prescription")
    @RequestMapping(value = "/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse pregnancyProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "medication", required = true, value = "drug name", defaultValue = "") @RequestParam("medication") String medication,
		@ApiParam(name = "dosage", required = true, value = "dosage e.g 2 times a day", defaultValue = "") @RequestParam("dosage") String dosage,
                @ApiParam(name = "startDate", required = false, value = "start date yyyy/mm/dd", defaultValue = "") @RequestParam("startDate") String startDate,
                @ApiParam(name = "endDate", required = false, value = "end date yyyy/mm/dd", defaultValue = "") @RequestParam("endDate") String endDate,
                @ApiParam(name = "specialInstruction", required = false, value = "any other required instruction", defaultValue = "") @RequestParam("specialInstruction") String specialInstruction,
                @ApiParam(name = "doctorReason", required = false, value = "reason for additional instruction", defaultValue = "") @RequestParam("doctorReason") String doctorReason,                        
                @ApiParam(name = "patientId", required = true, value = "patient id", defaultValue = "") @RequestParam("patientId") String patientId,
                @ApiParam(name = "consultationId", required = true, value = "consultation id", defaultValue = "") @RequestParam("consultationId") String consultationId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response =  doctorPrescriptionsController.addPrescription(medication, dosage, startDate, endDate, specialInstruction, doctorReason, Long.valueOf(patientId), Long.valueOf(consultationId), sessionDetail);
                       
		return response;
	}
        
    @ApiOperation("Get patient prescription")
    @RequestMapping(value = "/patient/{patientId}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerPrescriptionsResponse getCustomerPrescriptions (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("patientId") String patientId){
                       
                CustomerPrescriptionsResponse response = doctorPrescriptionsController.getPrescriptions(Long.valueOf(patientId));
		return response;
	}
}
