/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.HealthInterestsResponse;

/**
 *
 * @author prolific
 */
public interface HealthInterestsController {
     public HealthInterestsResponse getHealthInterests(SessionDetail sessionDetail);
     public ServiceResponse addHealthInterests(String healthInterest, String description, SessionDetail sessionDetail);
     public ServiceResponse editHealthInterests(Long id, String healthInterest, String description, SessionDetail sessionDetail);
     public ServiceResponse removeHealthInterests(Long id, SessionDetail sessionDetail);
     public ServiceResponse createCustomerHealthInterests(String healthInterests, SessionDetail sessionDetail);
     public HealthInterestsResponse getCustomerHealthInterests(SessionDetail sessionDetail);
}
