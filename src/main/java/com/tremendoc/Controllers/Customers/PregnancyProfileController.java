/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;


import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.HealthProfileResponse;
import com.tremendoc.subsresponse.PregnancyProfile;

/**
 *
 * @author prolific
 */
public interface PregnancyProfileController {
    
    public ServiceResponse createPregnancyProfile(boolean currentlyPregnant, int noOfTimesPregnant,
            int noOfFullTermPregnancies, int noOfPrematureBirths, int noOfInducedAbortions,
            int noOfMiscarriages, int noOfChildren, SessionDetail sessionDetail);
   
    public PregnancyProfile getPregnancyProfile(Customer customer);
}
