/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Consultation;
import com.tremendoc.Entity.HealthTip;
import com.tremendoc.Entity.Specialty;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface HealthtipRepository extends JpaRepository<HealthTip, Long>{
    
    @Query("select c from HealthTip c where c.id = ?1")
    HealthTip getById(Long id);
    
    @Query("select count(*) from HealthTip where isActive=true")
    public abstract BigInteger countActive();
    
    @Query(value = "select * FROM healthTip WHERE isActive=true ORDER BY createDate DESC LIMIT 20 OFFSET ?1", nativeQuery = true)
    public abstract List<HealthTip> getTips(int start); 
    
}
