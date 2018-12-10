/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerLanguages;
import com.tremendoc.Entity.LifestyleProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author prolific
 */
public interface CustomerLanguagesRepository extends JpaRepository<CustomerLanguages, Long> {
    
     @Query("select c from CustomerLanguages c where c.customer = ?1")
     CustomerLanguages getCustomerLanguages(Customer customer);
}
