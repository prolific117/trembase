/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.ConsultationsController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.ConsultationsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author olatunji.oduro
 */
@RestController
@Api("Set of endpoints for Managing Consultations.")
@RequestMapping(path="/consultation") // This means URL's start with /demo (after Application path)
public class ConsultationRoutes {
    
    @Autowired
    ConsultationsController consultationsController;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
    
    @ApiOperation("initiate a consultation in the event that there is a doctor available to route to")
    @RequestMapping(value = "/initiate", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse initiateConsultation (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "paymentMode", required = true, value = "payment Mode", defaultValue = "") @RequestParam("paymentMode") String paymentMode,
	        @ApiParam(name = "cardId", required = false, value = "card id if payment mode is card", defaultValue = "") @RequestParam("cardId") String cardId,
	 	@ApiParam(name = "consultationType", required = true, value = "consultation Type", defaultValue = "") @RequestParam("consultationType") String consultationType,
                @ApiParam(name = "doctorId", required = false, value = "doctor id", defaultValue = "") @RequestParam("doctorId") String doctorId,
                @ApiParam(name = "patientId", required = false, value = "patient id", defaultValue = "") @RequestParam("patientId") String patientId,
                @ApiParam(name = "specialtyId", required = false, value = "specialty id", defaultValue = "") @RequestParam("specialtyId") String specialtyId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                ServiceResponse response =  consultationsController.createConsultation(Long.valueOf(patientId), Long.valueOf(doctorId), Long.valueOf(specialtyId), consultationType, paymentMode);
		return response;
	}
        
      @ApiOperation("search for consultations")
      @RequestMapping(value = "/retrieve", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse getConsultations (
                @RequestHeader(name = "sessionid", required = false) final String sessionId,
                @RequestParam(name ="page", required = false) String page,
                @RequestParam(name ="paymentMode", required = false) String paymentMode,
	        @RequestParam(name ="consultationType", required = false) String consultationType,
                @RequestParam(name ="consultationStatus", required = false) String consultationStatus,
                @RequestParam(name ="doctorId", required = false) String doctorId,
                @RequestParam(name ="patientId", required = false) String patientId,
                @RequestParam(name ="specialtyId", required = false) String specialtyId,
                @RequestParam(name ="startDate", required = false) String startDate,
                @RequestParam(name ="endDate", required = false) String endDate,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                ConsultationsResponse response = new ConsultationsResponse(ServiceResponse.ERROR);
                
                Long patient = null;
                Long doctor = null;
                Long specialty = null;
                Date start = null;
                Date end = null;
                Calendar cal = null;
                
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                if(startDate != null && endDate != null)
                    try {
                        start = format.parse(startDate);
                        end = format.parse(endDate);
                        
                        cal = Calendar.getInstance();
                        cal.setTime(end);
                        cal.add(Calendar.DATE, 1); //minus number would decrement the days
                        
                    } catch (ParseException ex) {
                        java.util.logging.Logger.getLogger(ConsultationRoutes.class.getName()).log(Level.SEVERE, null, ex);
                        response.setDescription("Dates are not properly formed - Pass yyyy-mm-dd");
                        return response;
                    }
                
                
                if(patientId != null){
                    patient = Long.valueOf(patientId);
                }
                
                if(doctorId != null){
                    doctor = Long.valueOf(doctorId);
                }
                
                if(specialtyId != null){
                    specialty = Long.valueOf(specialtyId);
                }
                
                response =  consultationsController.getConsultations(patient, doctor, specialty, consultationType, paymentMode, consultationStatus, start, cal, Integer.parseInt(page));
		return response;
	}
}
