/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.HealthProfileResponse;

/**
 *
 * @author prolific
 */
public interface HealthProfile {
    
     public HealthProfileResponse getHealthProfile(SessionDetail sessionDetail, String customerId);
}
