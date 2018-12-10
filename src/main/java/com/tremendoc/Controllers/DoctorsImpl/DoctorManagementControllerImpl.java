/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.security.EncryptionTool;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.SessionController;
import com.tremendoc.Controllers.Doctors.DoctorManagementController;
import com.tremendoc.Controllers.Doctors.ProfileManager;
import com.tremendoc.Entity.Category;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorDocuments;
import com.tremendoc.Entity.DoctorSession;
import com.tremendoc.Entity.Repository.CategoryRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.DoctorDocumentsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.DoctorSessionRepository;
import com.tremendoc.Entity.Repository.NotificationRepository;
import com.tremendoc.Entity.Repository.ResetTokenRepository;
import com.tremendoc.Entity.Repository.SpecialtyRepository;
import com.tremendoc.Entity.ResetToken;
import com.tremendoc.Entity.Specialty;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.Categories;
import com.tremendoc.response.CategoriesResponse;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.DoctorCreationResponse;
import com.tremendoc.response.DoctorProfileResponse;
import com.tremendoc.response.SpecialtyData;
import com.tremendoc.response.StatusResponse;
import com.tremendoc.response.VerifyResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class DoctorManagementControllerImpl implements DoctorManagementController{
    
   @Autowired 
   private DoctorRepository doctorRepository;
   
   @Autowired
   private DoctorSessionRepository doctorSessionRepository;
   
   @Autowired
   private DoctorDocumentsRepository documentsRepository;
   
   @Autowired
   private ProfileManager profileManager;
   
   @Autowired
   private SpecialtyRepository specialtyRepository;
   
   @Autowired
   protected SessionDetail sessionDetail;
   
   @Autowired 
   private ResetTokenRepository resetTokenRepository;
   
   @Autowired
   private SessionController sessionController;

  private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RequestUtils.class.getName());
    
   @Override
    public DoctorCreationResponse createDoctor(String firstName, String lastname, String gender, 
            String age, String phone, String email, String password, String preferredUsername, 
            String dob, String preferredLanguage, String country,  String specialty,
            String homeAddress, String state, String practiceDescription, String currentProfessionalStatus,
            String goals) {
        DoctorCreationResponse response = new DoctorCreationResponse(ServiceResponse.ERROR);
        
        //check if doctor exists
        Doctor existingByEmail = doctorRepository.findByEmail(email);
        
        if(existingByEmail != null){
            response.setDescription("A doctor with this email already exists");
            return response;
        }
        
        Doctor existingByUsername = doctorRepository.findBypreferredUsername(preferredUsername);
        
        if(existingByUsername != null){
            response.setDescription("A doctor with this username already exists");
            return response;
        }
        
        Doctor doctor = new Doctor();
        
        doctor.setIsActive(Boolean.FALSE);
        doctor.setFirstname(firstName);
        doctor.setLastName(lastname);
        doctor.setGender(gender);
        doctor.setPhone(phone);
        doctor.setEmail(email);
        doctor.setAdminDeactivated(false);
        doctor.setPassword(password);
        doctor.setPreferredUsername(preferredUsername);
        doctor.setCountry(country);
        doctor.setPracticeDescription(practiceDescription);
        doctor.setDob(dob);
        doctor.setOnlineStatus("OFFLINE");
        doctor.setPreferredLanguage(preferredLanguage);
        doctor.setCountry(country);
        doctor.setHomeAddress(homeAddress);
        doctor.setState(state.toLowerCase());
        doctor.setGoals(goals);
        doctor.setCurrentProfessionalStatus(currentProfessionalStatus);
        doctor.setPassword(EncryptionTool.hashPassword(password));
        doctor.setInviteCode(randomAlphanumericString(5));
        
        String message = validateData(doctor);
        if("".equals(message)){
            //specialty
            Specialty doctorSpecialty = specialtyRepository.findByName(specialty);
            if(doctorSpecialty == null){
                response.setDescription("Invalid Specialty");
                return response;
            }

            doctor.setSpecialty(doctorSpecialty);
            doctor.setCategory(doctorSpecialty.getCategory());
            
            doctorRepository.save(doctor);

             //send welcome mail
            response.setEmail(email);
            response.setFirstname(firstName);
            response.setLastName(lastname);
            response.setGender(gender);
            response.setAge(age);
            response.setDoctorId(doctor.getId());
            response.setInviteCode(doctor.getInviteCode());

            //create redis session
            String sessionKey = sessionController.createDoctorSession(doctor);

            if(!"".equals(sessionKey)){
               logger.log(Level.INFO, "New Doctor successfully registered");
               response.setSessionId(sessionKey);
               response.setCode(ServiceResponse.SUCCESS);
               response.setDescription(ServiceResponse.GENERAL_SUCCESS_MESSAGE); 
            }
            else{
               response.setCode(ServiceResponse.INCOMPLETE_ERROR);
               response.setDescription("Doctor created but session was not created, please log in"); 
            }                         
        }
        else{
            response.setDescription(message);
            return response;
        }
        
        return response;
     }
    
    @Override
    public DoctorCreationResponse authenticateDoctor(String email, String password) {
        DoctorCreationResponse response = new DoctorCreationResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Doctor existingDoctor = doctorRepository.findByEmail(email);
        if(existingDoctor == null){
            response.setDescription("Doctor does not exist");
            return response;
        }
        
        if(existingDoctor.isAdminDeactivated()){
            response.setDescription("Account deactivated by Admin");
            return response;
        }
        
        //validate password
        try{
            Boolean passwordValid = EncryptionTool.comparePassword(password, existingDoctor.getPassword());
            if(passwordValid){
                response.setEmail(email);
                response.setFirstname(existingDoctor.getFirstname());
                response.setLastName(existingDoctor.getLastName());
                response.setGender(existingDoctor.getGender());
                response.setDoctorId(existingDoctor.getId());
                response.setInviteCode(existingDoctor.getInviteCode());
                response.setHasUploadedAcademicInfo(profileManager.checkAcademicProfile(existingDoctor));
                response.setHasUploadedDocuments(profileManager.checkDocouments(existingDoctor));
                response.setHasUploadedReferes(profileManager.checkReferees(existingDoctor));
                response.setProfileComplete(profileManager.checkCompletion(existingDoctor));
                
                DoctorDocuments documents = documentsRepository.findByDoctor(existingDoctor);
                if(documents != null){
                  response.setImage(documents.getProffessionalProfilePhoto());
                }
                //delete existing
                //create redis session
                String sessionKey = sessionController.createDoctorSession(existingDoctor);
                
                if(!"".equals(sessionKey)){
                   response.setSessionId(sessionKey);
                   response.setCode(ServiceResponse.SUCCESS);
                   response.setDescription(ServiceResponse.GENERAL_SUCCESS_MESSAGE); 
                }
                else{
                   response.setCode(ServiceResponse.INCOMPLETE_ERROR);
                   response.setDescription("Unable to create session"); 
                }                         
            }
            else{
                response.setDescription(ServiceResponse.INCORRECT_PASSWORD);
            }
            
        }
        catch(Exception ex){
            response.setDescription(ServiceResponse.GENERAL_ERROR_MESSAGE);
        }
        
        //send response
        return response;
    }

    @Override
    public VerifyResponse verifySession(String sessionId) {
        VerifyResponse response = new VerifyResponse();
        DoctorSession csession = doctorSessionRepository.findBySessionID(sessionId);
        
        if(csession == null){
            response.setCode(ServiceResponse.ERROR);
            response.setValid(false);
            return response;
        }
        
        if(csession.getIsActive() == false){
            response.setCode(ServiceResponse.ERROR);
            response.setValid(false);
            return response;
        }
        else{
            response.setValid(true);
        }
        
        response.setCode(ServiceResponse.SUCCESS);
        response.setAccountId(csession.getDoctor().getId());
        response.setEmail(csession.getDoctor().getEmail());
        response.setStatus("DOCTOR");
        
        return response;
    }

    private String randomAlphanumericString(int size) {
        String generatedString = RandomStringUtils.randomAlphanumeric(size);

        return generatedString;
    }
    
    private String validateData(Doctor doctor){
        String message = "";
        
        if(!doctor.getEmail().toLowerCase().contains("@") || !doctor.getEmail().toLowerCase().contains(".")){
            message = "Please enter proper email address";
        }
        
        boolean hasUpperCase = !doctor.getPassword().equals(doctor.getPassword().toLowerCase());
        
        if(!hasUpperCase){
            message = "Please include an upper case character in password";
        }
        
        boolean hasNumber = doctor.getPassword().matches(".*\\d.*");
        
        if(!hasNumber){
            message = "Please include a number";
        }
        
        return message;
    }


    @Override
    public ServiceResponse updateProfile(String firstName, String lastname, String gender,
            String age, String phone, String dob, String preferredLanguage, String country, 
            String specialty, String homeAddress, String state, String practiceDescription, 
            String currentProfessionalStatus, String goals, Long doctorId) {
        
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //check if doctor exists
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        
        if(doctor == null){
            response.setDescription("Doctor does not exist");
            return response;
        }
        
        doctor.setFirstname(firstName);
        doctor.setLastName(lastname);
        doctor.setGender(gender);
        doctor.setPhone(phone);
        doctor.setCountry(country);
        doctor.setPracticeDescription(practiceDescription);
        doctor.setDob(dob);
        doctor.setPreferredLanguage(preferredLanguage);
        doctor.setCountry(country);
        doctor.setHomeAddress(homeAddress);
        doctor.setState(state);
        doctor.setGoals(goals);
        doctor.setCurrentProfessionalStatus(currentProfessionalStatus);
        doctor.setInviteCode(randomAlphanumericString(5));
        
        doctorRepository.save(doctor);
        
        response.setCode(ServiceResponse.SUCCESS);
        return response;
  }

    @Override
    public DoctorProfileResponse getDoctorProfile(Long doctorId) {
    
        DoctorProfileResponse response = new DoctorProfileResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Doctor does not exist");
            return response;
        }
    
        response.setCountry(doctor.getCountry());
        response.setDob(doctor.getDob());
        response.setEmail(doctor.getEmail());
        response.setOnlineStatus(doctor.getOnlineStatus());
        response.setFirstname(doctor.getFirstname());
        response.setGender(doctor.getGender());
        response.setLastName(doctor.getLastName());
        response.setPhone(doctor.getPhone());
        response.setPreferredLanguage(doctor.getPreferredLanguage());
        response.setPreferredUsername(doctor.getPreferredUsername());
        response.setActive(doctor.getIsActive());
        response.setSpecialty(doctor.getSpecialty().getName());
        response.setState(doctor.getState());
        response.setHomeAddress(doctor.getHomeAddress());
        response.setGoals(doctor.getGoals());
        
        response.setCode(ServiceResponse.SUCCESS);
        
        return response;
    }

    @Override
    public ServiceResponse setOnlineStatus(String status, Long doctorId) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Doctor does not exist");
            return response;
        }
        
        doctor.setOnlineStatus(status);
        doctorRepository.save(doctor);
        
        response.setCode(ServiceResponse.SUCCESS);
        return response;
    }

    @Override
    public StatusResponse getOnlineStatus(Long doctorId) {
       StatusResponse response = new StatusResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Doctor does not exist");
            return response;
        }
        
        response.setOnlineStatus(doctor.getOnlineStatus());
        
        response.setCode(ServiceResponse.SUCCESS);
        return response;
    }

    @Override
    public ServiceResponse resetPassword(String email) {
       DoctorCreationResponse response = new DoctorCreationResponse(ServiceResponse.ERROR);
        
        //check if doctor exists
        Doctor existingByEmail = doctorRepository.findByEmail(email);
        
        if(existingByEmail == null){
            response.setDescription("Account does not exist");
            return response;
        }
        
        //generate link and send an email with a reset link to customer
        ResetToken token = new ResetToken();
        token.setRequestor_status("DOCTOR");
        token.setToken(randomAlphanumericString(15));
        
        resetTokenRepository.save(token);
        
        logger.log(Level.INFO, token.getToken());
        response.setCode(ServiceResponse.SUCCESS);
        
        return response;
     }

    @Override
    public ServiceResponse completeReset(String email, String token, String password) {
    ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Doctor existingByEmail = doctorRepository.findByEmail(email);
        
        if(existingByEmail == null){
            response.setDescription("Account does not exist");
            return response;
        }
        
        ResetToken token_ = resetTokenRepository.getToken(token);
        //do validity check here
        
        Date current = new Date();
       
        long diffInMillies = Math.abs(current.getTime() - token_.getCreateDate().getTime());
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        if(diff > 10){
            response.setDescription("Token expired, please create new token");
            
            token_.setIsActive(Boolean.FALSE);
            resetTokenRepository.save(token_);
            
            return response;
        }
        
        if(!token_.getIsActive()){
            response.setDescription("This reset token has already been used, please create a new one");
            return response;
        }
      
        //reset password
        existingByEmail.setPassword(EncryptionTool.hashPassword(password));
        doctorRepository.save(existingByEmail);
        
        token_.setIsActive(Boolean.FALSE);
        resetTokenRepository.save(token_);
        
        response.setCode(ServiceResponse.SUCCESS);
        
        return response; }
    
    
    
}
