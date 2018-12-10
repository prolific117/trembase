/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.LifestyleProfileController;
import com.tremendoc.Entity.AllergiesProfile;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.LifestyleProfile;
import com.tremendoc.Entity.PregnancyProfile;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.LifestyleProfileRepository;
import com.tremendoc.Entity.Repository.PregnancyProfileRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prolific
 */
@Service
public class LifestyleProfileControllerImpl implements LifestyleProfileController{
    
   @Autowired 
   private LifestyleProfileRepository lifestyleProfileRepository;
   
   @Autowired 
   private CustomerRepository customerRepository;

    @Override
    public ServiceResponse createLifestyleProfile(boolean sexuallyActive, boolean recreationalDrugs, SessionDetail sessionDetail) {
        ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
         
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         LifestyleProfile existingProfile = lifestyleProfileRepository.getCustomerProfile(customer);
         
         if(existingProfile == null){
             existingProfile = new LifestyleProfile();
         }
         
         existingProfile.setRecreationalDrugs(recreationalDrugs);
         existingProfile.setSexuallyActive(sexuallyActive);
         existingProfile.setCustomer(customer);
         
         lifestyleProfileRepository.save(existingProfile);
         
         return response;
    }
    
   @Override
   public com.tremendoc.subsresponse.LifestyleProfile getLifestyleProfile(Customer customer) {
        
        com.tremendoc.subsresponse.LifestyleProfile lifestyle = new com.tremendoc.subsresponse.LifestyleProfile();
        
        LifestyleProfile profile = lifestyleProfileRepository.getCustomerProfile(customer);
        
        if(profile == null){
            return null;
        }
        
        lifestyle.setRecreationalDrugs(profile.isRecreationalDrugs());
        lifestyle.setSexuallyActive(profile.isSexuallyActive());
        
        return lifestyle;
    }
    
}
