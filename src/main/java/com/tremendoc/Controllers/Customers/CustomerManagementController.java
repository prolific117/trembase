/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.tremendoc.Entity.Customer;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.VerifyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author prolific
 */

public interface CustomerManagementController {
     public CustomerCreationResponse createCustomer(String firstName, String lastname, String gender, String age, String phone, String email, String password);
     public CustomerCreationResponse authenticateCustomer(String email, String passwword);
     public VerifyResponse verifySession(String sessionId);
}
