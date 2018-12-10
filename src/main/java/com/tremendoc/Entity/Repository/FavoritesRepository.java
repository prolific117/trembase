/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.FAQ;
import com.tremendoc.Entity.Favorites;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author olatunji.oduro
 */
public interface FavoritesRepository extends JpaRepository<Favorites, Long>{
    
    @Query("select c from Favorites c where c.doctor = ?1 AND c.customer = ?2")
    Favorites retrieve(Doctor doctor, Customer customer);
    
    @Query(value = "select * FROM favorites WHERE patient = ?1 AND isActive=true ORDER BY createDate", nativeQuery = true)
    List<Favorites> retrieve(Customer customer);
}
