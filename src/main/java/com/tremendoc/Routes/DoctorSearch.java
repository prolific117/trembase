/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.ConsultationsController;
import com.tremendoc.Controllers.Doctors.SearchController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.DoctorResponse;
import com.tremendoc.response.DoctorsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api("Set of endpoints for Managing Doctor Search.")
@RequestMapping(path="/doctor") // This means URL's start with /demo (after Application path)
public class DoctorSearch {
    
    @Autowired
    SearchController searchController;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
    
    @ApiOperation("Search for doctors with a specific specialty, pass ?page=1 for initial data page")
    @RequestMapping(value = "/search/speciality/{specialtyId}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse searchBySpecialty (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("specialtyId") String specialtyId,
                @RequestParam("page") int page,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                DoctorsResponse response = searchController.searchSpecialty(Long.valueOf(specialtyId), page);
		return response;
	}
       
     @ApiOperation("Search for doctors with name, pass ?page=1 for initial data page, pass name as urlencoded value")
     @RequestMapping(value = "/search/name/{name}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse searchByName (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("name") String name,
                @RequestParam("page") int page,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                LOGGER.info(name);
                DoctorsResponse response = searchController.searchByName(name, page);
		return response;
	}
        
     @ApiOperation("Search by doctorId, doctor could be in a patient's list of favorites and this endpoint can be called to confirm if the doctor is online")
     @RequestMapping(value = "/search/{id}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse gindDoctor (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("id") String id,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                DoctorResponse response = searchController.searchById(Long.valueOf(id));
		return response;
     }
        
     @ApiOperation("Get available Doctor")
     @RequestMapping(value = "/available/{specialtyId}", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse searchAvailable(
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("specialtyId") String specialtyId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                DoctorResponse response = searchController.getAvailableDoctor(Long.valueOf(specialtyId));
		return response;
	}
    
}
