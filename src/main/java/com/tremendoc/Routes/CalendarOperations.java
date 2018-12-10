/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorCalendarController;
import com.tremendoc.Controllers.Doctors.DoctorNotesController;
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
 * @author olatunji.oduro
 */
@RestController
@Api("Set of endpoints for Managing Doctor Calendar.")
@RequestMapping(path="/calendar") // This means URL's start with /demo (after Application path)
public class CalendarOperations {
    
    @Autowired
    private DoctorCalendarController doctorCalendarController;
    
    @ApiOperation("Set up Calendar")
    @RequestMapping(value = "/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse setCalendar (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "commaSeperatedDays", required = true, value = "pass days as comma separated - does not have to include all days, only days doctor is available e.g. monday,tuesday,wednesday,thursday,friday,saturday,sunday", defaultValue = "") @RequestParam("commaSeperatedDays") String commaSeperatedDay,
		@ApiParam(name = "commaSeperatedTime", required = true, value = "pass doctor availability timeRange (24 hours) as comma separted value in correspondence with days e,.g 1-2,13-15,3-12,18-23,1-5,8-10,5-7 ", defaultValue = "") @RequestParam("commaSeperatedTime") String commaSeperatedTime,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                ServiceResponse response =  doctorCalendarController.createDoctorCalendar(commaSeperatedDay, commaSeperatedTime, sessionDetail);
		return response;
	}
        
    @ApiOperation("Retrieve Calendar")
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse getCalendar (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                ServiceResponse response =  doctorCalendarController.getDoctorCalendar(sessionDetail);
		return response;
	}
}
