/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.SymptomsProfile;
import com.tremendoc.Entity.TreatmentsProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author prolific
 */
public interface TreatmentsProfileRepository extends JpaRepository<TreatmentsProfile, Long> {
    
    @Query("select c from TreatmentsProfile c where c.customer = ?1")
    TreatmentsProfile getCustomerProfile(Customer customer);
}
