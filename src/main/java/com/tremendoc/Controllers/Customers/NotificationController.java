/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.NotificationsResponse;
import com.tremendoc.subsresponse.PregnancyProfile;

/**
 *
 * @author prolific
 */
public interface NotificationController {
     public NotificationsResponse getNotifications(SessionDetail sessionDetail, int start);
}
