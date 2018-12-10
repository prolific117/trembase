/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Category;
import com.tremendoc.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
 
    @Query("select c from Category c where c.name = ?1")
    Category findByName(String name);
    
    @Query("select c from Category c where c.id = ?1")
    Category getById(Long id);
}
