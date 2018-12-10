/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.TreatmentsProfileController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.SymptomsProfileRepository;
import com.tremendoc.Entity.Repository.TreatmentsProfileRepository;
import com.tremendoc.Entity.SymptomsProfile;
import com.tremendoc.Entity.TreatmentsProfile;
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
public class TreatmentsProfileControllerImpl implements TreatmentsProfileController {
   @Autowired 
   private TreatmentsProfileRepository treatmentsProfileRepository;
   
   @Autowired 
   private CustomerRepository customerRepository;
   
    @Override
    public ServiceResponse createTreatmentsProfile(String treatments, SessionDetail sessionDetail) {
         ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
         
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         TreatmentsProfile existingProfile = treatmentsProfileRepository.getCustomerProfile(customer);
         
         if(existingProfile == null){
             existingProfile = new TreatmentsProfile();
         }
         
         existingProfile.setTreatments(treatments);
         existingProfile.setCustomer(customer);
         
         treatmentsProfileRepository.save(existingProfile);
         
         return response;
    }

    @Override
    public List<String> getTreatments(Customer customer) {
        
        List<String> treatments = new ArrayList<>();
        
        TreatmentsProfile profile = treatmentsProfileRepository.getCustomerProfile(customer);
        
        if(profile == null){
            return treatments;
        }
        
        treatments = new ArrayList<String>(Arrays.asList(profile.getTreatments().split("/"))); 
        if(treatments.isEmpty()){
            treatments.add(profile.getTreatments());
        }
        
        return treatments;
    }
}
