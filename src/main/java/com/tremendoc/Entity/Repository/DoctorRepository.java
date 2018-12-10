/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.Specialty;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    @Query("select c from Doctor c where c.email = ?1")
    Doctor findByEmail(String email);
    
    @Query("select c from Doctor c where c.id = ?1")
    Doctor findByDoctorId(Long id);
    
    @Query("select c from Doctor c where c.preferredUsername = ?1")
    Doctor findBypreferredUsername(String preferredUsername);
    
    @Query("select count(*) from Doctor WHERE isActive=true AND specialty=?1")
    public abstract BigInteger countDoctorsBySpecialty (Specialty specialty);
    
    @Query("select count(*) from Doctor WHERE isActive=true AND onlineStatus=?1")
    public abstract BigInteger countByStatus(String status);
    
     @Query("select count(*) from Doctor WHERE isActive=true AND firstname like ?1 OR lastname like ?1")
    public abstract BigInteger countDoctorsByName (String name);
    
    @Query(value = "select * FROM doctor WHERE isActive=true AND specialty=?2 ORDER BY createDate DESC LIMIT 50 OFFSET ?1", nativeQuery = true)
    public abstract List<Doctor> getDoctorBySpecialty(int start, Specialty specialty);
    
    @Query(value = "select * FROM doctor WHERE isActive=true AND onlineStatus = ?1 AND specialty=?2 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    public abstract Doctor getOnlineBySpecialty(String status, Specialty specialty);
    
    @Query(value = "select * FROM doctor WHERE isActive=true AND firstname like ?1 OR lastName like ?1 ORDER BY createDate DESC LIMIT 50 OFFSET ?2", nativeQuery = true)
    public abstract List<Doctor> getDoctorByName(String name, int page);
    
    @Query(value = "select * FROM doctor WHERE isActive=true AND onlineStatus = ?1 DESC LIMIT 50 OFFSET ?1", nativeQuery = true)
    public abstract List<Doctor> getDoctorByStatus(String name);
}
