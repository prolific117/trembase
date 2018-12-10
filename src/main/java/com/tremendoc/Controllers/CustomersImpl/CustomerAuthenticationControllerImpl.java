/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.security.EncryptionTool;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.CustomerAuthenticationController;
import com.tremendoc.Controllers.Customers.SessionController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerSession;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.CustomerSessionRepository;
import com.tremendoc.Entity.Repository.ResetTokenRepository;
import com.tremendoc.Entity.ResetToken;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.VerifyResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prolific
 */
@Service
public class CustomerAuthenticationControllerImpl implements CustomerAuthenticationController{
    
    @Autowired 
    private CustomerRepository customerRepository;
   
    @Autowired
    private SessionController sessionController;
   
    @Autowired 
    private CustomerSessionRepository customerSessionRepository;
    
    @Autowired 
    private ResetTokenRepository resetTokenRepository;
    
    private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());

    @Override
    public CustomerCreationResponse authenticateCustomer(String email, String password) {
        
        CustomerCreationResponse response = new CustomerCreationResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Customer existingCustomer = customerRepository.findByEmail(email);
        if(existingCustomer == null){
            response.setDescription("Customer does not exist");
            return response;
        }
        
        //validate password
        try{
            Boolean passwordValid = EncryptionTool.comparePassword(password, existingCustomer.getPassword());
            if(passwordValid){
                response.setEmail(email);
                response.setFirstname(existingCustomer.getFirstname());
                response.setLastName(existingCustomer.getLastName());
                response.setGender(existingCustomer.getGender());
                response.setAge(existingCustomer.getAge());
                response.setCustomerId(existingCustomer.getId());
                response.setInviteCode(existingCustomer.getInviteCode());
                
                //delete existing
                //create redis session
                String sessionKey = sessionController.createCustomerSession(existingCustomer);
                
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
        CustomerSession csession = customerSessionRepository.findBySessionID(sessionId);
        
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
        response.setAccountId(csession.getCustomer().getId());
        response.setEmail(csession.getCustomer().getEmail());
        response.setStatus("CUSTOMER");
        
        return response;
    }

    @Override
    public ServiceResponse resetPassword(String email) {
        
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Customer existingCustomer = customerRepository.findByEmail(email);
        if(existingCustomer == null){
            response.setDescription("Customer does not exist");
            return response;
        }
        
        //generate link and send an email with a reset link to customer
        ResetToken token = new ResetToken();
        token.setRequestor_status("CUSTOMER");
        token.setToken(randomAlphanumericString());
        
        resetTokenRepository.save(token);
        
        logger.log(Level.INFO, token.getToken());
        response.setCode(ServiceResponse.SUCCESS);
        
        return response;
        
     }
    
    private String randomAlphanumericString() {
        String generatedString = RandomStringUtils.randomAlphanumeric(15);
        return generatedString;
    }

    @Override
    public ServiceResponse completeReset(String email, String token, String password) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Customer existingCustomer = customerRepository.findByEmail(email);
        if(existingCustomer == null){
            response.setDescription("Customer does not exist");
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
        existingCustomer.setPassword(EncryptionTool.hashPassword(password));
        customerRepository.save(existingCustomer);
        
        token_.setIsActive(Boolean.FALSE);
        resetTokenRepository.save(token_);
        
        response.setCode(ServiceResponse.SUCCESS);
        
        return response;
    }
        
}
