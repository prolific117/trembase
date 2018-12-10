/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.AllergiesProfileController;
import com.tremendoc.Controllers.Customers.HealthProfile;
import com.tremendoc.Controllers.Customers.LifestyleProfileController;
import com.tremendoc.Controllers.Customers.MedicationProfileController;
import com.tremendoc.Controllers.Customers.PregnancyProfileController;
import com.tremendoc.Controllers.Customers.SymptomsProfileController;
import com.tremendoc.Controllers.Customers.TreatmentsProfileController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.HealthProfileResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prolific
 */
@Service
public class HealthProfileImpl implements HealthProfile {

    private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
     
    @Autowired 
    private CustomerRepository customerRepository;
    
    @Autowired
    private PregnancyProfileController pregnancyProfileController;
        
    @Autowired
    private LifestyleProfileController lifestyleProfileController;

    @Autowired
    private AllergiesProfileController allergiesProfileController;

    @Autowired
    private MedicationProfileController medicationProfileController;

    @Autowired
    private SymptomsProfileController symptomsProfileController;

    @Autowired
    private TreatmentsProfileController treatmentsProfileController;
    
   
    @Override
    public HealthProfileResponse getHealthProfile(SessionDetail sessionDetail, String customerId) {
  
        
        HealthProfileResponse response  = new HealthProfileResponse(ServiceResponse.SUCCESS);

        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
       
        try{
            Customer customer = null;
            
            if((customerId != null) && (!"".equals(customerId))){
                 customer = customerRepository.getById(Long.valueOf(customerId));
             }
             else{
                 customer = customerRepository.getOne(user.getId());
             }
             
             response.setAllergiesProfile(allergiesProfileController.getAllergies(customer));
             response.setLifestyleProfile(lifestyleProfileController.getLifestyleProfile(customer));
             response.setMedicationProfile(medicationProfileController.getMedication(customer));
             response.setPregnancyProfile(pregnancyProfileController.getPregnancyProfile(customer));
             response.setSymptomsProfile(symptomsProfileController.getSymptoms(customer));
             response.setTreatmentsProfile(treatmentsProfileController.getTreatments(customer));
        
             response.setCode(ServiceResponse.SUCCESS);
        }
        catch(Exception ex){
            logger.log(Level.SEVERE, "Health profile retrieval failed for "+user.getUserName());
        }
        
        return response;
    }
    
    
    
}
