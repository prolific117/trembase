/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.Card;
import com.tremendoc.Entity.Category;
import com.tremendoc.Entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author olatunji.oduro
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    
    @Query("select c from Card c where c.customer = ?1 and c.bin = ?2 and c.last4 = ?3 and c.isActive = true")
    Card findByParams(Customer customer, String bin, String lastFour);
    
    @Query("select c from Card c where c.id = ?1 and c.isActive = true")
    Card getById(Long id);
    
    @Query("select c from Card c where c.customer = ?1 and c.isActive = true")
    List<Card> getByCustomer(Customer customer);
}
