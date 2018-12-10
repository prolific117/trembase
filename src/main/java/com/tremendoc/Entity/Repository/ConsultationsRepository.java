/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Consultation;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Prescriptions;
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
public interface ConsultationsRepository extends JpaRepository<Consultation, Long> {
    
    @Query("select c from Consultation c where c.id = ?1")
    Consultation getById(Long id);
    
    @Query(value = "select count(*) from consultation WHERE isActive = true", nativeQuery = true)
    public BigInteger count_();
     
}
