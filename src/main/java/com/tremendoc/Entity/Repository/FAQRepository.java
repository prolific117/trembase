/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.DoctorSession;
import com.tremendoc.Entity.FAQ;
import com.tremendoc.Entity.HealthTip;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author olatunji.oduro
 */
public interface FAQRepository extends JpaRepository<FAQ, Long>{
    
    @Query("select c from FAQ c where c.id = ?1")
    FAQ getById(Long id);
    
    @Query("select count(*) from FAQ where isActive=true")
    public abstract BigInteger countActive();
    
    @Query(value = "select * FROM FAQ WHERE isActive=true", nativeQuery = true)
    public abstract List<FAQ> getFaq(); 
}
