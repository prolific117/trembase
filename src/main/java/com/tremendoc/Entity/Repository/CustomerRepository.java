/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;
import com.tremendoc.Entity.Customer;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author prolific
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
     @Query("select c from Customer c where c.email = ?1")
     Customer findByEmail(String email);
     
     @Query("select c from Customer c where c.id = ?1")
     Customer getById(Long id);
     
     @Query("select count(*) from Customer")
     public abstract BigInteger countCurrentCustomers ();
     
     @Query("select count(*) from Customer where createDate < ?1")
     public abstract BigInteger countLastWeekCustomers (Date firstDayOfTheWeek);
     
     @Query(value = "select * FROM Customer  WHERE is_active=true ORDER BY create_date DESC LIMIT 4", nativeQuery = true)
     public abstract List<Customer> getLastFour();
}