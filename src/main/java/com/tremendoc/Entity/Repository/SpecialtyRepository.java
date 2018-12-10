/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Category;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.Specialty;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long>{
    
    @Query("select c from Specialty c where c.name like ?1")
    Specialty findByName(String name);
    
    @Query("select c from Specialty c where c.id like ?1")
    Specialty getById(Long id);
   
    @Query(value = "select * from specialty where category = ?1",  nativeQuery = true)
    List<Specialty> findByCategory(Category category);
}
