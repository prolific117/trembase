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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author prolific
 */
@Entity
@Table(name = "customerLanguages")
@NamedQueries({
    //@NamedQuery(name = "Customer.getLastFour", query = "SELECT a FROM Customer a WHERE a.isActive=true ORDER BY a.createDate DESC")
 }     
)
public class CustomerLanguages extends AbstractRepositoryModel implements Serializable {
    
    @Column(name="english")
    private boolean english;
    
    @Column(name="yoruba")
    private boolean yoruba;
     
    @Column(name="hausa")
    private boolean hausa;
      
    @Column(name="ibo")
    private boolean ibo;
       
    @Column(name="french")
    private boolean french;
    
    @Column(name="pidgin")
    private boolean pidgin;
     
    @Column(name="spanish")
    private boolean spanish;
      
    @Column(name="portuguese")
    private boolean portuguese;
    
    @OneToOne
    @JoinColumn (name = "customer", referencedColumnName = "id")
    private Customer customer;

    public boolean isPidgin() {
        return pidgin;
    }

    public void setPidgin(boolean pidgin) {
        this.pidgin = pidgin;
    }

    public boolean isSpanish() {
        return spanish;
    }

    public void setSpanish(boolean spanish) {
        this.spanish = spanish;
    }

    public boolean isPortuguese() {
        return portuguese;
    }

    public void setPortuguese(boolean portuguese) {
        this.portuguese = portuguese;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isEnglish() {
        return english;
    }

    public void setEnglish(boolean english) {
        this.english = english;
    }

    public boolean isYoruba() {
        return yoruba;
    }

    public void setYoruba(boolean yoruba) {
        this.yoruba = yoruba;
    }

    public boolean isHausa() {
        return hausa;
    }

    public void setHausa(boolean hausa) {
        this.hausa = hausa;
    }

    public boolean isIbo() {
        return ibo;
    }

    public void setIbo(boolean ibo) {
        this.ibo = ibo;
    }

    public boolean isFrench() {
        return french;
    }

    public void setFrench(boolean french) {
        this.french = french;
    }
    
    
}
