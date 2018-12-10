/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.VerifyResponse;


/**
 *
 * @author prolific
 */
public interface CustomerAuthenticationController {
     public CustomerCreationResponse authenticateCustomer(String email, String password);
     public VerifyResponse verifySession(String sessionId);    
     public ServiceResponse resetPassword(String email);
     public ServiceResponse completeReset(String email, String token, String password);
}

