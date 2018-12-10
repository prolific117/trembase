/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import java.util.Date;


/**
 *
 * @author prolific
 */
public interface SessionController {
      public String createCustomerSession(Customer customer);
      public String createDoctorSession(Doctor customer);
}
