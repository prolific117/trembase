/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.AllergiesProfileController;
import com.tremendoc.Entity.AllergiesProfile;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Repository.AllergiesProfileRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
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
public class AllergiesProfileControllerImpl implements AllergiesProfileController {

   @Autowired 
   private AllergiesProfileRepository allergiesProfileRepository;
   
   @Autowired 
   private CustomerRepository customerRepository;
   
   @Override
   public ServiceResponse createAllergiesProfile(String allergies, SessionDetail sessionDetail) {
        ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
         
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         AllergiesProfile existingProfile = allergiesProfileRepository.getCustomerProfile(customer);
         
         if(existingProfile == null){
             existingProfile = new AllergiesProfile();
         }
         
         existingProfile.setAllergies(allergies);
         existingProfile.setCustomer(customer);
         allergiesProfileRepository.save(existingProfile);
         
         return response;
   } 

    @Override
    public List<String> getAllergies(Customer customer) {
        
        List<String> allergies = new ArrayList<>();
        
        AllergiesProfile profile = allergiesProfileRepository.getCustomerProfile(customer);
        
        if(profile == null){
            return allergies;
        }
        
        allergies = new ArrayList<>(Arrays.asList(profile.getAllergies().split("/"))); 
        if(allergies.isEmpty()){
            allergies.add(profile.getAllergies());
        }
        
        return allergies;
    }
}
