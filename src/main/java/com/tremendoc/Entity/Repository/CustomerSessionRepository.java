/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerSession;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author prolific
 */
@Repository
public interface CustomerSessionRepository extends JpaRepository<CustomerSession, Long> {
    
     @Query("select c from CustomerSession c where c.sessionId = ?1")
     CustomerSession findBySessionID(String sessionId);
     
     @Transactional
     @Modifying
     @Query("UPDATE CustomerSession e SET e.isActive = false, e.lastUpdateDate = ?1 WHERE e.customer = ?2 AND e.isActive = true")
     public void disableActiveSessions(Date date, Customer existingCustomer);
}
