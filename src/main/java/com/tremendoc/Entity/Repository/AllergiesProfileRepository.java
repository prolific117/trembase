/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.AllergiesProfile;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.PregnancyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author prolific
 */
@Repository
public interface AllergiesProfileRepository extends JpaRepository<AllergiesProfile, Long> {
    
     @Query("select c from AllergiesProfile c where c.customer = ?1")
     AllergiesProfile getCustomerProfile(Customer customer);
}
