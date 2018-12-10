/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import PleskAccountCreationResponses.Result;
import com.leanstack.Plesk.AccountManager.AccountCreationService;
import com.tremendoc.Controllers.Customers.CustomerAuthenticationController;
import com.tremendoc.Controllers.Customers.SessionController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.response.CustomerCreationResponse;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.proxy.leanstack.commons.security.EncryptionTool;
import java.util.logging.Level;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.tremendoc.Controllers.Customers.CustomerManagementController;
import com.tremendoc.Entity.CustomerNotification;
import com.tremendoc.Entity.Repository.NotificationRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.VerifyResponse;
import com.tremendoc.security.impl.SessionDetailProvider;
import javax.management.Notification;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author prolific
 */
@Service
public class CustomerManagementControllerImpl implements CustomerManagementController {
    
   @Autowired 
   private CustomerRepository customerRepository;
   
   @Autowired 
   private NotificationRepository notificationRepository;
   
   @Autowired
   protected SessionDetail sessionDetail;
   
   @Autowired
   private SessionController sessionController;
   
   @Autowired
   private CustomerAuthenticationController customerAuthenticationController;
   
   public static final String WELCOME_MESSAGE = "<p><b>Welcome to Tremendoc<b></p> "
            + "<p>Do a voice call, video call or chat with a doctor at your convenience. Get a free consultation once you add your card.</p> "
            + "<p>Schedule doctor visits and much more</p>"
            + " <p> Reach out to info@tremendoc.com if you have any questions an we hope you enjoy this service as much as we do</p>";
    
   
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
    //private static final Logger LOGGER = LoggerFactory.getLogger(CustomerCreationControllerImpl.class);

    @Override
    public CustomerCreationResponse createCustomer(String firstName, String lastname, 
                                                   String gender, String age, String phone, 
                                                   String email, String password) {
        
        CustomerCreationResponse response = new CustomerCreationResponse(ServiceResponse.ERROR);
        
        //check for customer existence
        Customer existingCustomer = customerRepository.findByEmail(email);
        if(existingCustomer != null){
            response.setDescription("Customer with provided email already exists");
            return response;
        }
        
        //create customer
        try{
            Customer newCustomer = new Customer();
        
            newCustomer.setEmail(email);
            newCustomer.setGender(gender);
            newCustomer.setFirstname(firstName);
            newCustomer.setLastName(lastname);
            newCustomer.setPhone(phone);
            newCustomer.setPassword(EncryptionTool.hashPassword(password));
            newCustomer.setAge(age);
            newCustomer.setCreateDate(new Date());
            newCustomer.setIsActive(Boolean.TRUE);
            newCustomer.setInviteCode(randomAlphanumericString());
            
            String message = validateData(newCustomer);
            if("".equals(message)){
                customerRepository.save(newCustomer);
            
                //create notification
                createNotification(firstName+" "+lastname, email, newCustomer);

                //send welcome mail
                response.setEmail(email);
                response.setFirstname(firstName);
                response.setLastName(lastname);
                response.setGender(gender);
                response.setAge(age);
                response.setCustomerId(newCustomer.getId());
                response.setInviteCode(newCustomer.getInviteCode());
                
                //create redis session
                String sessionKey = sessionController.createCustomerSession(newCustomer);
                
                if(!"".equals(sessionKey)){
                   LOGGER.info("New customer successfully registered");
                   response.setSessionId(sessionKey);
                   response.setCode(ServiceResponse.SUCCESS);
                   response.setDescription(ServiceResponse.GENERAL_SUCCESS_MESSAGE); 
                }
                else{
                   response.setCode(ServiceResponse.INCOMPLETE_ERROR);
                   response.setDescription("Customer created but session was not created, please log in"); 
                }                         
            }
            else{
                response.setDescription(message);
            }
            
        }
        catch(Exception ex){
            response.setDescription(ServiceResponse.GENERAL_ERROR_MESSAGE);
        }
        
        //send response
        return response;
    }
    
    
    private void createNotification(String name, String email, Customer customer){
        
        CustomerNotification notification = new CustomerNotification();
        notification.setTitle("Welcome To Tremendoc");
        notification.setNotification(WELCOME_MESSAGE);
        notification.setCustomer(customer);
        
        notificationRepository.save(notification);
        
        //send to notification service
    }
    
    
    private String randomAlphanumericString() {
        String generatedString = RandomStringUtils.randomAlphanumeric(5);

        return generatedString;
    }
    
    private String validateData(Customer customer){
        String message = "";
        
        if(!customer.getEmail().toLowerCase().contains("@") || !customer.getEmail().toLowerCase().contains(".")){
            message = "Please enter proper email address";
        }
        
        boolean hasUpperCase = !customer.getPassword().equals(customer.getPassword().toLowerCase());
        
        if(!hasUpperCase){
            message = "Please include an upper case character in password";
        }
        
        boolean hasNumber = customer.getPassword().matches(".*\\d.*");
        
        if(!hasNumber){
            message = "Please include a number";
        }
        
        return message;
    }
    
  
    @Override
    public CustomerCreationResponse authenticateCustomer(String email, String password) {
        return customerAuthenticationController.authenticateCustomer(email, password);
    }

    @Override
    public VerifyResponse verifySession(String sessionId) {
        return customerAuthenticationController.verifySession(sessionId);
    }
    
}
