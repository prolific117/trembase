/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity;

import com.proxy.leanstack.commons.repository.AbstractRepositoryModel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 *
 * @author olatunji.oduro
 */
@Entity
@Table(name = "favorites")
@NamedQueries({
  }     
)
public class Favorites extends AbstractRepositoryModel implements Serializable{
    
    @ManyToOne
    @JoinColumn (name = "patient", referencedColumnName = "id")
    Customer customer;
    
    @ManyToOne
    @JoinColumn (name = "doctor", referencedColumnName = "id")
    Doctor doctor;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    
}
