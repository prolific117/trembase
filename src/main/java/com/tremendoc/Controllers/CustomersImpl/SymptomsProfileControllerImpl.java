/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.SymptomsProfileController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.MedicationProfile;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.MedicationProfileRepository;
import com.tremendoc.Entity.Repository.SymptomsProfileRepository;
import com.tremendoc.Entity.SymptomsProfile;
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
public class SymptomsProfileControllerImpl implements SymptomsProfileController {

   @Autowired 
   private SymptomsProfileRepository symptomsProfileRepository;
   
   @Autowired 
   private CustomerRepository customerRepository;
   
    @Override
    public ServiceResponse createSymptomsProfile(String symptoms, SessionDetail sessionDetail) {
         ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
         
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         SymptomsProfile existingProfile = symptomsProfileRepository.getCustomerProfile(customer);
         
         if(existingProfile == null){
             existingProfile = new SymptomsProfile();
         }
         
         existingProfile.setSymptoms(symptoms);
         existingProfile.setCustomer(customer);
         
         symptomsProfileRepository.save(existingProfile);
         
         return response;
    }

    @Override
    public List<String> getSymptoms(Customer customer) {
        List<String> symptoms = new ArrayList<>();
        
        SymptomsProfile profile = symptomsProfileRepository.getCustomerProfile(customer);
        
        if(profile == null){
            return symptoms;
        }
        
        symptoms = new ArrayList<String>(Arrays.asList(profile.getSymptoms().split("/"))); 
        if(symptoms.isEmpty()){
            symptoms.add(profile.getSymptoms());
        }
        
        return symptoms;
    }
    
}
