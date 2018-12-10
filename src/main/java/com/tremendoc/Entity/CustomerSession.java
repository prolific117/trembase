/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity;

import com.proxy.leanstack.commons.repository.AbstractRepositoryModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author prolific
 */
@Entity
@Table (name = "CustomerSession")
@NamedQueries({
    @NamedQuery(name="CustomerSession.findBySessionId", query="SELECT e from CustomerSession e where e.sessionId=:sessionId"),
    @NamedQuery(name="Session.disableCustomerActiveSessions", query="UPDATE CustomerSession e SET e.isActive = false, e.lastUpdateDate=:lastUpdateDate WHERE e.customer=:customer and e.isActive = true")
})

public class CustomerSession extends AbstractRepositoryModel{
    @ManyToOne
    @JoinColumn (name = "CUSTOMER_ID", referencedColumnName = "id")
    private Customer customer;
    
    @Column (nullable = false, unique = true, updatable = false, name = "SESSION_ID")
    private String sessionId;
    
    @Column(name="longitude")
    private double longitude;
     
    @Column(name="latitude")
    private double latitude;
    
    @Column(name="lifetime")
    private double lifetime;

    public double getLifetime() {
        return lifetime;
    }

    public void setLifetime(double lifetime) {
        this.lifetime = lifetime;
    }
    
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    
}



