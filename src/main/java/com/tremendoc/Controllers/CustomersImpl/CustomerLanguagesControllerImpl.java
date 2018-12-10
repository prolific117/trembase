/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.CustomerLanguagesController;
import com.tremendoc.Entity.AllergiesProfile;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerLanguages;
import com.tremendoc.Entity.Repository.CustomerLanguagesRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.LanguagesResponse;
import com.tremendoc.subsresponse.LanguagesProfile;
import com.tremendoc.subsresponse.LifestyleProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prolific
 */
@Service
public class CustomerLanguagesControllerImpl implements CustomerLanguagesController {
    
   @Autowired
   CustomerLanguagesRepository customerLanguagesRepository;
   
   @Autowired
   CustomerRepository customerRepository;

   @Override
   public ServiceResponse setCustomerLanguages(boolean english, boolean yoruba, boolean ibo, boolean hausa, boolean french, boolean pidgin, boolean spanish, boolean portuguese, SessionDetail sessionDetail) {
        
         ServiceResponse response  = new ServiceResponse(ServiceResponse.SUCCESS);
         
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         CustomerLanguages customerLanguage = customerLanguagesRepository.getCustomerLanguages(customer);
         
         if(customerLanguage == null){
             customerLanguage = new CustomerLanguages();
         }
         
         customerLanguage.setEnglish(english);
         customerLanguage.setYoruba(yoruba);
         customerLanguage.setIbo(ibo);
         customerLanguage.setHausa(hausa);
         customerLanguage.setFrench(french);
         customerLanguage.setPidgin(pidgin);
         customerLanguage.setSpanish(spanish);
         customerLanguage.setPortuguese(portuguese);
         customerLanguage.setCustomer(customer);
         
         customerLanguagesRepository.save(customerLanguage);
         
         return response;
    }

    @Override
    public LanguagesResponse getCustomerLanguages(SessionDetail sessionDetail) {
  
         LanguagesResponse response  = new LanguagesResponse(ServiceResponse.SUCCESS);
         
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());
       
         CustomerLanguages customerLanguage = customerLanguagesRepository.getCustomerLanguages(customer);
         
         if(customerLanguage == null){
             return response;
         }
         
         LanguagesProfile profile = new LanguagesProfile();
         profile.setEnglish(customerLanguage.isEnglish());
         profile.setYoruba(customerLanguage.isYoruba());
         profile.setIbo(customerLanguage.isIbo());
         profile.setHausa(customerLanguage.isHausa());
         profile.setFrench(customerLanguage.isFrench());
         profile.setPidgin(customerLanguage.isPidgin());
         profile.setSpanish(customerLanguage.isSpanish());
         profile.setPortuguese(customerLanguage.isPortuguese());
         
         response.setLanguages(profile);
         
         return response;
    }
    
}
