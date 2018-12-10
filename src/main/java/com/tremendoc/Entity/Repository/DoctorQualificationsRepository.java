/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorQualifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface DoctorQualificationsRepository extends JpaRepository<DoctorQualifications, Long> {
    
    @Query("select c from DoctorQualifications c where c.doctor = ?1")
    DoctorQualifications findByDoctor(Doctor doctor);
}
