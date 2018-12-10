/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerHealthInterests;
import com.tremendoc.Entity.CustomerLanguages;
import com.tremendoc.Entity.HealthInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author prolific
 */
public interface HealthInterestRepository extends JpaRepository<HealthInterest, Long> {
    
    @Query("select c from HealthInterest c where c.id = ?1 and c.isActive = true")
    HealthInterest getHealthInterestById(Long id);

    @Query("select c from HealthInterest c where c.interestTag like ?1 and c.isActive = true")
    public HealthInterest getHealthInterestByTag(String tag);
}
