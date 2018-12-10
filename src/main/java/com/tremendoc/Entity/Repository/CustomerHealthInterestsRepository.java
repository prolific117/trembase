/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerHealthInterests;
import com.tremendoc.Entity.HealthInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author prolific
 */
public interface CustomerHealthInterestsRepository extends JpaRepository<CustomerHealthInterests, Long> {
    
    @Query("select c from CustomerHealthInterests c where c.customer = ?1")
    CustomerHealthInterests getCustomerHealthInterests(Customer customer);
}
