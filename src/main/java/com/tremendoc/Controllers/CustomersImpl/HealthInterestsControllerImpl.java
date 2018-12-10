/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.HealthInterestsController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerHealthInterests;
import com.tremendoc.Entity.HealthInterest;
import com.tremendoc.Entity.MedicationProfile;
import com.tremendoc.Entity.Repository.CustomerHealthInterestsRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.HealthInterestRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.HealthInterestsResponse;
import com.tremendoc.subsresponse.CustomerHealthInterestResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prolific
 */
@Service
public class HealthInterestsControllerImpl implements HealthInterestsController {

    @Autowired 
    CustomerHealthInterestsRepository customerHealthInterestRepository;
    
    @Autowired 
    HealthInterestRepository healthInterestRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    
    private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
    
    @Override
    public ServiceResponse createCustomerHealthInterests(String healthInterests, SessionDetail sessionDetail) {
        ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
        
         
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Customer customer = customerRepository.getOne(user.getId());
       
        
        List<String> healthInterestsList = new ArrayList<>();
        
        healthInterestsList = new ArrayList<String>(Arrays.asList(healthInterests.split("/"))); 
        if(healthInterestsList.isEmpty()){
            if("".equals(healthInterests)){
                response.setDescription("No health Interest(s) chosen");
                return response;
            }
            else{
                healthInterestsList.add(healthInterests);
            }
        }
        
        try{
             CustomerHealthInterests interest = customerHealthInterestRepository.getCustomerHealthInterests(customer);
                 
                if(interest == null){
                    interest =  new CustomerHealthInterests();
                }
                 
                interest.setCount(healthInterestsList.size());
                
                for(int i = 0; i < healthInterestsList.size(); i++ ){
                    HealthInterest healthInterest = healthInterestRepository.getHealthInterestById(Long.valueOf(healthInterestsList.get(i)));
                    interest.setInterest(i+1, healthInterest);
                }
                
              interest.setCustomer(customer);
              customerHealthInterestRepository.save(interest);
        }
        catch(Exception ex){
            logger.log(Level.SEVERE, "Cannot create health interests"+customer.getEmail(), ex);
            response.setCode(ServiceResponse.ERROR);
        }
        
        return response;
    }

    @Override
    public HealthInterestsResponse getCustomerHealthInterests(SessionDetail sessionDetail) {
        HealthInterestsResponse response  = new HealthInterestsResponse(ServiceResponse.SUCCESS);
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Customer customer = customerRepository.getOne(user.getId());
        
        List<CustomerHealthInterestResponse> healthInterestsList = new ArrayList<>();
        
         try{
               CustomerHealthInterests interest = customerHealthInterestRepository.getCustomerHealthInterests(customer);
                 
                if(interest == null){
                    response.setInterests(healthInterestsList);
                    return response;
                }
                 
                for(int i = 0; i < interest.getCount(); i++ ){
                    HealthInterest specInterest = interest.getInterest(i+1);
                    
                    if(specInterest.getIsActive()){
                        CustomerHealthInterestResponse resp = new CustomerHealthInterestResponse();
                        resp.setId(specInterest.getId());
                        resp.setDescription(specInterest.getDescription());
                        resp.setInterest(specInterest.getInterest());
                        resp.setInterestTag(specInterest.getInterestTag());
                        
                        healthInterestsList.add(resp);
                    }
                 }
                
              response.setInterests(healthInterestsList);
        }
        catch(Exception ex){
            logger.log(Level.SEVERE, "Cannot pull health interests for "+customer.getEmail(), ex);
            response.setCode(ServiceResponse.ERROR);
        }
         
       return response;  
    }

    @Override
    public ServiceResponse addHealthInterests(String healthInterest, String description, SessionDetail sessionDetail) {
        
        //check if admin
        
        ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
        
        String tag  = healthInterest.replaceAll(" ", "_");
        
        HealthInterest interest = healthInterestRepository.getHealthInterestByTag(tag);
        
        if(interest != null){
            response.setDescription("Health Interest already exists");
            return response;
        }
        else{
            interest = new HealthInterest();
        }
        
        interest.setDescription(description);
        interest.setInterest(healthInterest);
        interest.setInterestTag(tag);
        
        healthInterestRepository.save(interest);
        
        return response;
    }

    @Override
    public HealthInterestsResponse getHealthInterests(SessionDetail sessionDetail) {
        
       //admin check 
        
        HealthInterestsResponse response  = new HealthInterestsResponse(ServiceResponse.SUCCESS);
        
        List<CustomerHealthInterestResponse> healthInterestsList = new ArrayList<>();
        
         try{
                List<HealthInterest> interest = healthInterestRepository.findAll();
                 
                if(interest == null){
                    response.setInterests(healthInterestsList);
                }
                 
                for(int i = 0; i < interest.size(); i++ ){
                   
                     if(interest.get(i).getIsActive()){
                        CustomerHealthInterestResponse resp = new CustomerHealthInterestResponse();
                        resp.setId(interest.get(i).getId());
                        resp.setInterest(interest.get(i).getInterest());
                        resp.setDescription(interest.get(i).getDescription());
                        resp.setInterestTag(interest.get(i).getInterestTag());

                        healthInterestsList.add(resp);
                     }
                }
                
              response.setInterests(healthInterestsList);
        }
        catch(Exception ex){
            logger.log(Level.SEVERE, "Cannot pull health interests for Admin", ex);
            response.setCode(ServiceResponse.ERROR);
        }
         
       return response;  
    }

    @Override
    public ServiceResponse removeHealthInterests(Long id, SessionDetail sessionDetail) {
      ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
        
        HealthInterest interest = healthInterestRepository.getHealthInterestById(id);
        
        if(interest == null){
            response.setCode(ServiceResponse.ERROR);
            response.setDescription("Health Interest does not exist");
        }
        
        
        interest.setIsActive(false);
        
        healthInterestRepository.save(interest);
        
        return response; 
    }

    @Override
    public ServiceResponse editHealthInterests(Long id, String healthInterest, String description, SessionDetail sessionDetail) {
         //check if admin
         
         ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
        
        HealthInterest interest = healthInterestRepository.getHealthInterestById(id);
        
        if(interest != null){
            response.setDescription("Health Interest does not exist");
            response.setCode(ServiceResponse.ERROR);
            return response;
        }
       
        
        String tag  = healthInterest.replaceAll(" ", "_");
        
        interest.setDescription(description);
        interest.setInterest(healthInterest);
        interest.setInterestTag(tag);
        
        healthInterestRepository.save(interest);
        
        return response;
    }
    
}
