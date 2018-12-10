/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.security.Secured;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.CustomerManagementController;
import com.tremendoc.Controllers.Doctors.DoctorDocumentsController;
import com.tremendoc.Controllers.Doctors.DoctorManagementController;
import com.tremendoc.Controllers.Doctors.DoctorQualificationsController;
import com.tremendoc.Controllers.Doctors.DoctorRefereeController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CategoriesResponse;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.CustomerOverviewResponse;
import com.tremendoc.response.DoctorCreationResponse;
import com.tremendoc.response.DoctorDocumentsResponse;
import com.tremendoc.response.DoctorProfileResponse;
import com.tremendoc.response.DoctorQualificationsResponse;
import com.tremendoc.response.RefereeResponse;
import com.tremendoc.response.VerifyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.logging.Logger;
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

/**
 *
 * @author olatunji.oduro
 */
@RestController
@Api(description = "Set of endpoints for Creating, Retrieving and Authenticating   Doctors.")
@RequestMapping(path="/doctor") // This means URL's start with /demo (after Application path)
public class DoctorManagement {
    
        @Autowired
        private DoctorManagementController doctorManagementController;
        
        @Autowired
        private DoctorQualificationsController qualificationsController;
        
        @Autowired
        private DoctorDocumentsController doctorDocumentsController;
        
        @Autowired
        private DoctorRefereeController doctorRefereeController;
        
        private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
        
        
        @ApiOperation("Get doctor profile")
        @GetMapping(path="/profile/{doctorId}")
	public DoctorProfileResponse getProfile(@PathVariable("doctorId") String doctorId, @RequestHeader(name = "sessionid", required = true) final String sessionId) {
		// This returns a JSON or XML with the users
	 	return doctorManagementController.getDoctorProfile(Long.valueOf(doctorId));
        }
        
        @ApiOperation("Update Doctor Qualification.")
        @GetMapping(path="/qualifications/{doctorId}")
	public DoctorQualificationsResponse getQualifications(@PathVariable("doctorId") String doctorId, @RequestHeader(name = "sessionid", required = true) final String sessionId) {
		// This returns a JSON or XML with the users
	 	return qualificationsController.getDoctorDocuments(Long.valueOf(doctorId));
        }
        
        @ApiOperation("Update Doctor status.")
        @GetMapping(path="/status/{doctorId}/{status}")
	public ServiceResponse setStatus(@PathVariable("doctorId") String doctorId, @PathVariable("status") String status, @RequestHeader(name = "sessionid", required = true) final String sessionId) {
		// This returns a JSON or XML with the users
	 	return doctorManagementController.setOnlineStatus(status, Long.valueOf(doctorId));
        }
        
        @ApiOperation("Get Doctor status.")
        @GetMapping(path="/status/{doctorId}")
	public ServiceResponse getStatus(@PathVariable("doctorId") String doctorId, @RequestHeader(name = "sessionid", required = true) final String sessionId) {
		// This returns a JSON or XML with the users
	 	return doctorManagementController.getOnlineStatus(Long.valueOf(doctorId));
        }
        
        @ApiOperation("Get Doctor Documents.")
        @GetMapping(path="/documents/{doctorId}")
	public DoctorDocumentsResponse getDocuments(@PathVariable("doctorId") String doctorId, @RequestHeader(name = "sessionid", required = true) final String sessionId) {
		// This returns a JSON or XML with the users
	 	return doctorDocumentsController.getDoctorDocuments(Long.valueOf(doctorId));
        }
        
        @ApiOperation("Initiate password reset.")
        @RequestMapping(value = "/reset", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse reset (  
                @ApiParam(name = "email", required = true, value = "User's email", defaultValue = "") @RequestParam("email") String email){
             
		
            ServiceResponse response = 
                       doctorManagementController.resetPassword(email);
            
            return response;
	}
        
        @ApiOperation("Complete password reset.")
        @RequestMapping(value = "/complete/reset", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse authenticateUser (  
                @ApiParam(name = "email", required = true, value = "User's email", defaultValue = "") @RequestParam("email") String email,
                @ApiParam(name = "token", required = true, value = "token", defaultValue = "") @RequestParam("token") String token,
                @ApiParam(name = "password", required = true, value = "User's password", defaultValue = "") @RequestParam("password") String password){
             
	       ServiceResponse response = 
                       doctorManagementController.completeReset(email, token, password);
            
            return response;
	}
        
        @ApiOperation("Authenticates Doctors on the tremendoc platform.")
        @RequestMapping(value = "/authenticate", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public DoctorCreationResponse authenticateDoctor (  
                @ApiParam(name = "email", required = true, value = "Doctor's email", defaultValue = "") @RequestParam("email") String email,                        
                @ApiParam(name = "password", required = true, value = "password", defaultValue = "") @RequestParam("password") String password){
             
		
            DoctorCreationResponse response = 
                       doctorManagementController.authenticateDoctor(email,password);
            
            return response;
	}
        
        @ApiOperation("Verifes Doctor's Sessions on the tremendoc platform.")
        @RequestMapping(value = "/session/verify/{sessionId}", method = RequestMethod.GET,
        produces = {MediaType.APPLICATION_JSON_VALUE})
        public VerifyResponse verifyCustomerSession(@PathVariable("sessionId") String sessionId) throws Exception {
           return doctorManagementController.verifySession(sessionId);
        }	
	
        @ApiOperation("Update Doctor Profile.")
        @RequestMapping(value = "/update", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse updateDoctor (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "firstname", required = true, value = "Doctor's first name", defaultValue = "") @RequestParam("firstname") String firstname,
		@ApiParam(name = "lastname", required = true, value = "Doctor's last name", defaultValue = "") @RequestParam("lastname") String lastname,
                @ApiParam(name = "gender", required = true, value = "Doctor's gender", defaultValue = "") @RequestParam("gender") String gender,
                @ApiParam(name = "phone", required = true, value = "Doctor's phone", defaultValue = "") @RequestParam("phone") String phone,
                @ApiParam(name = "dob", required = true, value = "Doctor's date of birth yyyy/mm/dd", defaultValue = "") @RequestParam("dob") String dob,                        
                @ApiParam(name = "preferredLanguage", required = true, value = "Doctor's preferred language", defaultValue = "") @RequestParam("preferredLanguage") String preferredLanguage,                        
                @ApiParam(name = "country", required = true, value = "Doctor's country", defaultValue = "") @RequestParam("country") String country,
                @ApiParam(name = "specialty", required = true, value = "Doctor's specialty", defaultValue = "") @RequestParam("specialty") String specialty,                        
                @ApiParam(name = "homeAddress", required = true, value = "Doctor's home address", defaultValue = "") @RequestParam("homeAddress") String homeAddress,
                @ApiParam(name = "state", required = true, value = "Doctor's state", defaultValue = "") @RequestParam("state") String state,
                @ApiParam(name = "goals", required = true, value = "Doctor's tremendoc goals (comma separated)", defaultValue = "") @RequestParam("goals") String goals,                        
                @ApiParam(name = "practiceDescription", required = true, value = "Decribe doctor's practice (what it entails)", defaultValue = "") @RequestParam("practiceDescription") String practiceDescription,
                @ApiParam(name = "currentProfessionalStatus", required = true, value = "Doctor's satus at place of work (ceo, partner etc.)", defaultValue = "") @RequestParam("currentProfessionalStatus") String currentProfessionalStatus,
                @ApiParam(name = "doctorId", required = true, value = "the doctor's id", defaultValue = "") @RequestParam("doctorId") String doctorId){
                    ServiceResponse response = 
                            doctorManagementController.updateProfile(firstname, lastname, gender, state, phone, dob, preferredLanguage, country, specialty, homeAddress, state, practiceDescription, currentProfessionalStatus, goals, Long.valueOf(doctorId));
                return response;
	}
        
         @ApiOperation("Creates Doctors on the tremendoc platform.")
        @RequestMapping(value = "/create", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public DoctorCreationResponse addNewDoctor (
                @ApiParam(name = "firstname", required = true, value = "Doctor's first name", defaultValue = "") @RequestParam("firstname") String firstname,
		@ApiParam(name = "lastname", required = true, value = "Doctor's last name", defaultValue = "") @RequestParam("lastname") String lastname,
                @ApiParam(name = "gender", required = true, value = "Doctor's gender", defaultValue = "") @RequestParam("gender") String gender,
                @ApiParam(name = "phone", required = true, value = "Doctor's phone", defaultValue = "") @RequestParam("phone") String phone,
                @ApiParam(name = "email", required = true, value = "Doctor's email", defaultValue = "") @RequestParam("email") String email,                        
                @ApiParam(name = "preferredUsername", required = true, value = "Doctor's preferred Username", defaultValue = "") @RequestParam("preferredUsername") String preferredUsername,                        
                @ApiParam(name = "dob", required = true, value = "Doctor's date of birth yyyy/mm/dd", defaultValue = "") @RequestParam("dob") String dob,                        
                @ApiParam(name = "preferredLanguage", required = true, value = "Doctor's preferred language", defaultValue = "") @RequestParam("preferredLanguage") String preferredLanguage,                        
                @ApiParam(name = "country", required = true, value = "Doctor's country", defaultValue = "") @RequestParam("country") String country,
                @ApiParam(name = "specialty", required = true, value = "Doctor's specialty", defaultValue = "") @RequestParam("specialty") String specialty,                        
                @ApiParam(name = "homeAddress", required = true, value = "Doctor's home address", defaultValue = "") @RequestParam("homeAddress") String homeAddress,
                @ApiParam(name = "state", required = true, value = "Doctor's state", defaultValue = "") @RequestParam("state") String state,
                @ApiParam(name = "goals", required = true, value = "Doctor's tremendoc goals (comma separated)", defaultValue = "") @RequestParam("goals") String goals,                        
                @ApiParam(name = "practiceDescription", required = true, value = "Decribe doctor's practice (what it entails)", defaultValue = "") @RequestParam("practiceDescription") String practiceDescription,
                @ApiParam(name = "currentProfessionalStatus", required = true, value = "Doctor's satus at place of work (ceo, partner etc.)", defaultValue = "") @RequestParam("currentProfessionalStatus") String currentProfessionalStatus,                        
                @ApiParam(name = "password", required = true, value = "password", defaultValue = "") @RequestParam("password") String password){
                    DoctorCreationResponse response = 
                            doctorManagementController.createDoctor(firstname, lastname, gender, state, phone, email, password, preferredUsername, dob, preferredLanguage, country, specialty, homeAddress, state, practiceDescription, currentProfessionalStatus, goals);
		
                return response;
	}
        
        @ApiOperation("Creates or Updates Doctors documents on the tremendoc platform.")
        @RequestMapping(value = "/documents/create", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addOrUpdateDocuments (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "degree", required = true, value = "url where the doctor's degree is uploaded", defaultValue = "") @RequestParam("degree") String degree,
		@ApiParam(name = "practicingLicense", required = true, value = "url where the practising license was uploaded", defaultValue = "") @RequestParam("practicingLicense") String practicingLicense,
                @ApiParam(name = "professionalProfilePhoto", required = true, value = "url to the location of the profile photo", defaultValue = "") @RequestParam("professionalProfilePhoto") String proffessionalProfilePhoto,
                @ApiParam(name = "validId", required = true, value = "url to the location of the valid id", defaultValue = "") @RequestParam("validId") String validId,
                @ApiParam(name = "nyscCertificate", required = true, value = "url to the location of the nysc certificate", defaultValue = "") @RequestParam("nyscCertificate") String nyscCertificate,                        
                @ApiParam(name = "doctorId", required = true, value = "the doctor's id", defaultValue = "") @RequestParam("doctorId") String doctorId){
                    ServiceResponse response = doctorDocumentsController.createDoctorDocuments(degree, practicingLicense, proffessionalProfilePhoto, validId, nyscCertificate, Long.valueOf(doctorId));
                return response;
	}
        
        @ApiOperation("Creates or Updates Doctors qualifications on the tremendoc platform.")
        @RequestMapping(value = "/qualifications/create", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addOrUpdateQualifications (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "university", required = true, value = "Doctor's university", defaultValue = "") @RequestParam("university") String university,
		@ApiParam(name = "location", required = true, value = "location of doctor's university", defaultValue = "") @RequestParam("location") String location,
                @ApiParam(name = "degree", required = true, value = "degree awarded from the institution (B.Sc, B.Ed etc.)", defaultValue = "") @RequestParam("degree") String degree,
                @ApiParam(name = "year", required = true, value = "graduation year", defaultValue = "") @RequestParam("year") String year,
                @ApiParam(name = "folioNumber", required = true, value = "doctor folio number", defaultValue = "") @RequestParam("folioNumber") String folioNumber,                        
                @ApiParam(name = "licenseExpiryDate", required = true, value = "doctor license expiry date yyyy/mm/dd", defaultValue = "") @RequestParam("licenseExpiryDate") String licenseExpiryDate,
		@ApiParam(name = "housemanshipHospital", required = true, value = "hospital where housemanship was carried out", defaultValue = "") @RequestParam("housemanshipHospital") String housemanshipHospital,
                @ApiParam(name = "housemanshipLocation", required = true, value = "location of housemanship hospital", defaultValue = "") @RequestParam("housemanshipLocation") String housemanshipLocation,
                @ApiParam(name = "housemanshipYear", required = true, value = "housemanship year (year completed)", defaultValue = "") @RequestParam("housemanshipYear") String housemanshipYear,
                @ApiParam(name = "hospitalOfPracticeName", required = true, value = "hospital of practice name", defaultValue = "") @RequestParam("hospitalOfPracticeName") String hospitalOfPracticeName,                        
                @ApiParam(name = "hospitalOfPracticeLocation", required = true, value = "hospital of practice location", defaultValue = "") @RequestParam("hospitalOfPracticeLocation") String hospitalOfPracticeLocation,
                @ApiParam(name = "hospitalOfPracticePhone", required = true, value = "hospital of practice phone", defaultValue = "") @RequestParam("hospitalOfPracticePhone") String hospitalOfPracticePhone,                        
                @ApiParam(name = "doctorId", required = true, value = "the doctor's id", defaultValue = "") @RequestParam("doctorId") String doctorId){
                    ServiceResponse response = qualificationsController.createDoctorQualifications(university, location, degree, year, folioNumber, licenseExpiryDate, housemanshipHospital, housemanshipLocation, housemanshipYear, hospitalOfPracticeName, hospitalOfPracticeLocation, hospitalOfPracticePhone, Long.valueOf(doctorId));
                            return response;
	}
        
        @ApiOperation("Creates or Updates Doctors referees on the tremendoc platform.")
        @RequestMapping(value = "/referee/create", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addReferee (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "bio", required = true, value = "referee short bio", defaultValue = "") @RequestParam("bio") String bio,
		@ApiParam(name = "email", required = true, value = "referee email", defaultValue = "") @RequestParam("email") String email,
                @ApiParam(name = "fullname", required = true, value = "referee fullname", defaultValue = "") @RequestParam("fullname") String fullname,
                @ApiParam(name = "phone", required = true, value = "referee phone", defaultValue = "") @RequestParam("phone") String phone,
                @ApiParam(name = "workplace", required = true, value = "referee workplace", defaultValue = "") @RequestParam("workplace") String workplace,                        
                @ApiParam(name = "doctorId", required = true, value = "the doctor's id", defaultValue = "") @RequestParam("doctorId") String doctorId){
                    ServiceResponse response = doctorRefereeController.createDoctorReferee(bio, email, fullname, phone, phone, workplace, Long.valueOf(doctorId));
                            return response;
	}
        
        @ApiOperation("Creates or Updates Doctors referees on the tremendoc platform.")
        @RequestMapping(value = "/referees/get", method = RequestMethod.POST, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public RefereeResponse getReferees (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "doctorId", required = true, value = "the doctor's id", defaultValue = "") @RequestParam("doctorId") String doctorId){
                    RefereeResponse response = doctorRefereeController.getDoctorReferees( Long.valueOf(doctorId));
                            return response;
	}
}
