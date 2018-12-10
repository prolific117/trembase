/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.Referee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long> {
    
    @Query("select c from Referee c where c.doctor = ?2 AND c.email = ?1")
    Referee findByEmail(String email, Doctor doctor);
    
    @Query("select c from Referee c where c.doctor = ?1")
    List<Referee> findByDoctor(Doctor doctor);
}
