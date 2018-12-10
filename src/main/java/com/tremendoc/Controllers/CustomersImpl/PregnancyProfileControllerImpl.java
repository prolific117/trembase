/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.tremendoc.Controllers.Customers.PregnancyProfileController;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.PregnancyProfile;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.PregnancyProfileRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prolific
 */
@Service
public class PregnancyProfileControllerImpl implements PregnancyProfileController {
    
   @Autowired 
   private PregnancyProfileRepository pregnancyProfileRepository;
   
   @Autowired 
   private CustomerRepository customerRepository;
   
   @Override
   public ServiceResponse createPregnancyProfile(boolean currentlyPregnant, int noOfTimesPregnant, int noOfFullTermPregnancies, int noOfPrematureBirths, int noOfInducedAbortions, int noOfMiscarriages, int noOfChildren, SessionDetail sessionDetail) {
         ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
         
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         PregnancyProfile existingProfile = pregnancyProfileRepository.getCustomerProfile(customer);
         
         if(existingProfile == null){
             existingProfile = new PregnancyProfile();
         }
         
         existingProfile.setCurrentlyPregnant(currentlyPregnant);
         existingProfile.setNoOfChidren(noOfChildren);
         existingProfile.setNoOfFullTermPregnancies(noOfFullTermPregnancies);
         existingProfile.setNoOfInducedAbortions(noOfInducedAbortions);
         existingProfile.setNoOfMiscarriages(noOfMiscarriages);
         existingProfile.setNoOfPrematureBirths(noOfPrematureBirths);
         existingProfile.setNoOfTimesPregnant(noOfTimesPregnant);
         existingProfile.setCustomer(customer);
         
         pregnancyProfileRepository.save(existingProfile);
         
         return response;
    }

    @Override
    public com.tremendoc.subsresponse.PregnancyProfile getPregnancyProfile(Customer customer) {
        com.tremendoc.subsresponse.PregnancyProfile pregnancy = new com.tremendoc.subsresponse.PregnancyProfile();
        
        PregnancyProfile profile = pregnancyProfileRepository.getCustomerProfile(customer);
        
        if(profile == null){
            return null;
        }
        
        pregnancy.setCurrentlyPregnant(profile.isCurrentlyPregnant());
        pregnancy.setNoOfChidren(profile.getNoOfChidren());
        pregnancy.setNoOfFullTermPregnancies(profile.getNoOfFullTermPregnancies());
        pregnancy.setNoOfInducedAbortions(profile.getNoOfInducedAbortions());
        pregnancy.setNoOfMiscarriages(profile.getNoOfMiscarriages());
        pregnancy.setNoOfPrematureBirths(profile.getNoOfPrematureBirths());
        pregnancy.setNoOfTimesPregnant(profile.getNoOfTimesPregnant());
        
        return pregnancy;
    }
    
}
