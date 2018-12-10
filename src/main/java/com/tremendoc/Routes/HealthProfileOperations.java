/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.AllergiesProfileController;
import com.tremendoc.Controllers.Customers.CustomerManagementController;
import com.tremendoc.Controllers.Customers.HealthProfile;
import com.tremendoc.Controllers.Customers.LifestyleProfileController;
import com.tremendoc.Controllers.Customers.MedicationProfileController;
import com.tremendoc.Controllers.Customers.PregnancyProfileController;
import com.tremendoc.Controllers.Customers.SymptomsProfileController;
import com.tremendoc.Controllers.Customers.TreatmentsProfileController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.HealthProfileResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.logging.Logger;
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
@Api(description = "Set of endpoints for Managing Customer Profile.")
@RequestMapping(path="/customer") // This means URL's start with /demo (after Application path)
public class HealthProfileOperations {
    
        @Autowired
        private HealthProfile healthProfile;
    
        @Autowired
        private PregnancyProfileController pregnancyProfileController;
        
        @Autowired
        private LifestyleProfileController lifestyleProfileController;
        
        @Autowired
        private AllergiesProfileController allergiesProfileController;
        
        @Autowired
        private MedicationProfileController medicationProfileController;
        
        @Autowired
        private SymptomsProfileController symptomsProfileController;
        
        @Autowired
        private TreatmentsProfileController treatmentsProfileController;
        
        private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
        
        @ApiOperation("Retrieve all health profiles (User and Doctor), padd customerId if call is coming from Doctor")
        @RequestMapping(value = "/profile", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public HealthProfileResponse healthProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "customerId", required = false, value = "customer id", defaultValue = "") @RequestParam("customerId") String customerId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   HealthProfileResponse response = 
                       healthProfile.getHealthProfile(sessionDetail, customerId);
                       
		return response;
	}
	
        @ApiOperation("Create Pregnancy Profile (User)")
        @RequestMapping(value = "/profile/pregnancy", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse pregnancyProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "currentlyPregnant", required = true, value = "is user currently pregnant", defaultValue = "") @RequestParam("currentlyPregnant") boolean currentlyPregnant,
		@ApiParam(name = "noOfTimesPregnant", required = false, value = "no of times user has been pregnant", defaultValue = "") @RequestParam("noOfTimesPregnant") int noOfTimesPregnant,
                @ApiParam(name = "noOfFullTermPregnancies", required = false, value = "no of full term pregnancies", defaultValue = "") @RequestParam("noOfFullTermPregnancies") int noOfFullTermPregnancies,
                @ApiParam(name = "noOfPrematureBirths", required = false, value = "no of premature births", defaultValue = "") @RequestParam("noOfPrematureBirths") int noOfPrematureBirths,
                @ApiParam(name = "noOfInducedAbortions", required = false, value = "no of induced abortions", defaultValue = "") @RequestParam("noOfInducedAbortions") int noOfInducedAbortions,
                @ApiParam(name = "noOfMiscarriages", required = false, value = "no of miscarriages", defaultValue = "") @RequestParam("noOfMiscarriages") int noOfMiscarriages,                        
                @ApiParam(name = "noOfChildren", required = true, value = "no of children", defaultValue = "") @RequestParam("noOfChildren") int noOfChildren,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       pregnancyProfileController.createPregnancyProfile(currentlyPregnant, noOfTimesPregnant, noOfFullTermPregnancies, noOfPrematureBirths, noOfInducedAbortions, noOfMiscarriages, noOfChildren, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("Create Lifestyle Profile (User)")
        @RequestMapping(value = "/profile/lifestyle", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse lifestyleProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "sexuallyActive", required = true, value = "is user sexually active", defaultValue = "") @RequestParam("sexuallyActive") boolean sexuallyActive,
		@ApiParam(name = "recreationalDrugs", required = true, value = "does user take recreational drugs", defaultValue = "") @RequestParam("recreationalDrugs") boolean recreationalDrugs,
		@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       lifestyleProfileController.createLifestyleProfile(sexuallyActive, recreationalDrugs, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("Create Allergies Profile (User)")
        @RequestMapping(value = "/profile/allergies", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse allergiesProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "allergies", required = true, value = "list of customer allergies separated by '/' e.g groundnuts / milk  ", defaultValue = "") @RequestParam("allergies") String allergies,
		@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       allergiesProfileController.createAllergiesProfile(allergies, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("Creaete Medication Profile (User)")
        @RequestMapping(value = "/profile/medication", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse medicationProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "medication", required = true, value = "list of customer medication separated by '/' e.g lumenfantrine (10 grammes a day) / paracetamol (2 tablets a day)", defaultValue = "") @RequestParam("medication") String medication,
		@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       medicationProfileController.createMedicationProfile(medication, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("Create Symptoms Profile (User)")
        @RequestMapping(value = "/profile/symptoms", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse symptomsProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "symptoms", required = true, value = "list of customer symptoms separated by '/' e.g Occasional Headache / Frequent rumbling in the tummy", defaultValue = "") @RequestParam("symptoms") String symptoms,
		@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       symptomsProfileController.createSymptomsProfile(symptoms, sessionDetail);
                       
		return response;
	}
        
        @ApiOperation("Create Treatments Profile (User)")
        @RequestMapping(value = "/profile/treatments", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse treatmentsProfile (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "treatments", required = true, value = "list of customer past treatments separated by '/' e.g Kidney Stones / Vasectomy", defaultValue = "") @RequestParam("treatments") String treatments,
		@ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       treatmentsProfileController.createTreatmentsProfile(treatments, sessionDetail);
                       
		return response;
	}
}
