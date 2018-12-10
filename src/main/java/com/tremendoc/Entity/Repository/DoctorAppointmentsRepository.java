/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.CustomerSession;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorAppointments;
import com.tremendoc.Entity.DoctorDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface DoctorAppointmentsRepository extends JpaRepository<DoctorAppointments, Long> {
 
    @Query("select c from DoctorAppointments c where c.doctor = ?1")
    DoctorAppointments findByDoctor(Doctor doctor);
}
