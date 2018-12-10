/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.NotificationController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.CustomerNotification;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.NotificationRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.NotificationsResponse;
import com.tremendoc.subsresponse.SubNotification;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author prolific
 */
@Service
public class NotificationControllerImpl implements NotificationController {

    @Autowired
    NotificationRepository notificationRepository;
    
    @Autowired 
    private CustomerRepository customerRepository;
    
    @Override
    public NotificationsResponse getNotifications(SessionDetail sessionDetail, int start) {
        NotificationsResponse response = new NotificationsResponse(ServiceResponse.SUCCESS);
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Customer customer = customerRepository.getOne(user.getId());
        
        BigInteger totalCount = notificationRepository.getCustomerNotificationsCount(customer);
        
        if(totalCount == BigInteger.ZERO){
            response.setCurrentCount(0);
            response.setNoOfPages(0);
            response.setTotalCount(0);
            response.setNotifications(new ArrayList<SubNotification>());
            
            return response;
        }
        
        if(start == 0){
            response.setCode(ServiceResponse.ERROR);
            response.setDescription("Pagination cannot start from 0");
            
            return response;
        }
         
        start = (start - 1 ) * 20;
        
        List<CustomerNotification> notifications = notificationRepository.getNotifications(customer, start);
        List<SubNotification> refinedNotifications = new ArrayList<>();
        
        response.setTotalCount(totalCount.intValue());
        response.setNoOfPages(totalCount.divide(BigInteger.valueOf(20)).add(BigInteger.valueOf(1)).intValue());
        response.setCurrentCount(notifications.size());
        
        for(int i = 0; i < notifications.size(); i++){
            CustomerNotification dbNotification = notifications.get(i);
            SubNotification notification = new SubNotification();
            
            notification.setId(dbNotification.getId());
            notification.setNotification(dbNotification.getNotification());
            notification.setTitle(dbNotification.getTitle());
            notification.setRead(dbNotification.isCustomerRead());
            
            refinedNotifications.add(notification);
        }
        
        response.setNotifications(refinedNotifications);
       
        return response;
    }
    
}
