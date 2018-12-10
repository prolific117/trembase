/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.LifestyleProfile;
import com.tremendoc.Entity.PregnancyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author prolific
 */
@Repository
public interface LifestyleProfileRepository extends JpaRepository<LifestyleProfile, Long> {
    
     @Query("select c from LifestyleProfile c where c.customer = ?1")
     LifestyleProfile getCustomerProfile(Customer customer);
}
