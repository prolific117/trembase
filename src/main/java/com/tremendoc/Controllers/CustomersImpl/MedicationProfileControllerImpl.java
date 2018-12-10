/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.MedicationProfileController;
import com.tremendoc.Entity.AllergiesProfile;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.MedicationProfile;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.MedicationProfileRepository;
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
public class MedicationProfileControllerImpl implements MedicationProfileController {

   @Autowired 
   private MedicationProfileRepository medicationProfileRepository;
   
   @Autowired 
   private CustomerRepository customerRepository;
   
    @Override
    public ServiceResponse createMedicationProfile(String medication, SessionDetail sessionDetail) {
         ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
         
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         MedicationProfile existingProfile = medicationProfileRepository.getCustomerProfile(customer);
         
         if(existingProfile == null){
             existingProfile = new MedicationProfile();
         }
         
         existingProfile.setMedication(medication);
         existingProfile.setCustomer(customer);
         
         medicationProfileRepository.save(existingProfile);
         
         return response;
    }

    @Override
    public List<String> getMedication(Customer customer) {
        List<String> medications = new ArrayList<>();
        
        MedicationProfile profile = medicationProfileRepository.getCustomerProfile(customer);
        
        if(profile == null){
            return medications;
        }
        
        medications = new ArrayList<String>(Arrays.asList(profile.getMedication().split("/"))); 
        if(medications.isEmpty()){
            medications.add(profile.getMedication());
        }
        
        return medications;
    }
    
}
