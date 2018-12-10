/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.MedicationProfile;
import com.tremendoc.Entity.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    
     @Query("select c from ResetToken c where c.token = ?1")
     ResetToken getToken(String token);
}
