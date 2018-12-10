/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Entity;

import com.proxy.leanstack.commons.repository.AbstractRepositoryModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author prolific
 */
@Entity
@Table(name = "lifestyleProfile")
public class LifestyleProfile extends AbstractRepositoryModel implements Serializable {
    
    @Column(name="sexuallyActive")
    private boolean sexuallyActive;
     
    @Column(name="recreationalDrugs")
    private boolean recreationalDrugs;
    
    @OneToOne
    @JoinColumn (name = "customer", referencedColumnName = "id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isSexuallyActive() {
        return sexuallyActive;
    }

    public void setSexuallyActive(boolean sexuallyActive) {
        this.sexuallyActive = sexuallyActive;
    }

    public boolean isRecreationalDrugs() {
        return recreationalDrugs;
    }

    public void setRecreationalDrugs(boolean recreationalDrugs) {
        this.recreationalDrugs = recreationalDrugs;
    }
      
     
}
