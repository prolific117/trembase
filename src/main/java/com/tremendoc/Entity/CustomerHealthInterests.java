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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author prolific
 */
@Entity
@Table (name = "CustomerHealthInterests")
@NamedQueries({
 
})
public class CustomerHealthInterests extends AbstractRepositoryModel {
    
    @OneToOne
    @JoinColumn (name = "CUSTOMER_ID", referencedColumnName = "id")
    private Customer customer;
    
    @Column(name = "count")
    private int count;
    
    @ManyToOne
    @JoinColumn (name = "HEALTH_INTEREST_ONE", referencedColumnName = "id")
    private HealthInterest healthInterestOne;
    
    @ManyToOne
    @JoinColumn (name = "HEALTH_INTEREST_TWO", referencedColumnName = "id")
    private HealthInterest healthInterestTwo;
    
    @ManyToOne
    @JoinColumn (name = "HEALTH_INTEREST_THREE", referencedColumnName = "id")
    private HealthInterest healthInterestThree;
    
    @ManyToOne
    @JoinColumn (name = "HEALTH_INTEREST_FOUR", referencedColumnName = "id")
    private HealthInterest healthInterestFour;
    
    @ManyToOne
    @JoinColumn (name = "HEALTH_INTEREST_FIVE", referencedColumnName = "id")
    private HealthInterest healthInterestFive;
    
    @ManyToOne
    @JoinColumn (name = "HEALTH_INTEREST_SIX", referencedColumnName = "id")
    private HealthInterest healthInterestSix;
    
    @ManyToOne
    @JoinColumn (name = "HEALTH_INTEREST_SEVEN", referencedColumnName = "id")
    private HealthInterest healthInterestSeven;

    public void setInterest(int i, HealthInterest interest){
        if(i == 1)
            setHealthInterestOne(interest);
        
        if(i == 2)
            setHealthInterestTwo(interest);
        
        if(i == 3)
            setHealthInterestThree(interest);
        
        if(i == 4)
            setHealthInterestFour(interest);
        
        if(i == 5)
            setHealthInterestFive(interest);
        
        if(i == 6)
            setHealthInterestSix(interest);
        
        if(i == 7)
            setHealthInterestSeven(interest);
    }
    
    public HealthInterest getInterest(int i){
        
        HealthInterest healthInterest = null;
        
        if(i == 1)
            healthInterest = getHealthInterestOne();
        
        if(i == 2)
           healthInterest = getHealthInterestTwo();
        
        if(i == 3)
           healthInterest = getHealthInterestThree();
        
        if(i == 4)
            healthInterest = getHealthInterestFour();
        
        if(i == 5)
           healthInterest = getHealthInterestFive();
        
        if(i == 6)
           healthInterest = getHealthInterestSix();
        
        if(i == 7)
          healthInterest = getHealthInterestSeven();
        
        return healthInterest;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public HealthInterest getHealthInterestOne() {
        return healthInterestOne;
    }

    public void setHealthInterestOne(HealthInterest healthInterestOne) {
        this.healthInterestOne = healthInterestOne;
    }

    public HealthInterest getHealthInterestTwo() {
        return healthInterestTwo;
    }

    public void setHealthInterestTwo(HealthInterest healthInterestTwo) {
        this.healthInterestTwo = healthInterestTwo;
    }

    public HealthInterest getHealthInterestThree() {
        return healthInterestThree;
    }

    public void setHealthInterestThree(HealthInterest healthInterestThree) {
        this.healthInterestThree = healthInterestThree;
    }

    public HealthInterest getHealthInterestFour() {
        return healthInterestFour;
    }

    public void setHealthInterestFour(HealthInterest healthInterestFour) {
        this.healthInterestFour = healthInterestFour;
    }

    public HealthInterest getHealthInterestFive() {
        return healthInterestFive;
    }

    public void setHealthInterestFive(HealthInterest healthInterestFive) {
        this.healthInterestFive = healthInterestFive;
    }

    public HealthInterest getHealthInterestSix() {
        return healthInterestSix;
    }

    public void setHealthInterestSix(HealthInterest healthInterestSix) {
        this.healthInterestSix = healthInterestSix;
    }

    public HealthInterest getHealthInterestSeven() {
        return healthInterestSeven;
    }

    public void setHealthInterestSeven(HealthInterest healthInterestSeven) {
        this.healthInterestSeven = healthInterestSeven;
    }
    
    
    
}
