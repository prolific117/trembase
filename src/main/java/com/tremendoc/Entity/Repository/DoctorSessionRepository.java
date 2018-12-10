/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorSession;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorSessionRepository extends JpaRepository<DoctorSession, Long> {
     @Query("select c from DoctorSession c where c.sessionId = ?1")
     DoctorSession findBySessionID(String sessionId);
     
     @Transactional
     @Modifying
     @Query("UPDATE DoctorSession e SET e.isActive = false, e.lastUpdateDate = ?1 WHERE e.doctor = ?2 AND e.isActive = true")
     public void disableActiveSessions(Date date, Doctor doctor);
}
