/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.subsresponse.SubNotification;
import java.util.List;

/**
 *
 * @author prolific
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationsResponse extends ServiceResponse{
    
    public NotificationsResponse(int code) {
        super(code);
     }
    
    public int totalCount;
    
    public int noOfPages;
    
    public int currentCount;
    
    List<SubNotification> notifications;

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }
    
    public List<SubNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<SubNotification> notifications) {
        this.notifications = notifications;
    }
    
}
