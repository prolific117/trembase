/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity.Repository;

import com.tremendoc.Entity.AllergiesProfile;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerNotification;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author prolific
 */
@Repository
public interface NotificationRepository extends JpaRepository<CustomerNotification, Long> {
    
     
     @Query("select count(*) from CustomerNotification c where c.customer = ?1")
     public abstract BigInteger getCustomerNotificationsCount(Customer customer);
     
     @Query(value = "select * FROM Customer WHERE c.customer = ?1 ORDER BY createDate DESC LIMIT 20 OFFSET ?2", nativeQuery = true)
     public abstract List<CustomerNotification> getNotifications(Customer customer, int start);
}
