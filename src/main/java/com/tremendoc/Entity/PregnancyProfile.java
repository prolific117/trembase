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
@Table(name = "pregnancyProfile")
public class PregnancyProfile extends AbstractRepositoryModel implements Serializable{
    
     @Column(name="currentlyPregnant")
     private boolean currentlyPregnant;
     
     @Column(name="noOfTimesPregnant")
     private int noOfTimesPregnant;
     
     @Column(name="noOfFullTermPregnancies")
     private int noOfFullTermPregnancies;
     
     @Column(name="noOfPrematureBirths")
     private int noOfPrematureBirths;
     
     @Column(name="noOfInducedAbortions")
     private int noOfInducedAbortions;
     
     @Column(name="noOfMiscarriages")
     private int noOfMiscarriages;
      
     @Column(name="noOfChildren")
     private int noOfChidren;
     
     @OneToOne
     @JoinColumn (name = "customer", referencedColumnName = "id")
     private Customer customer;

     public Customer getCustomer() {
        return customer;
     }

     public void setCustomer(Customer customer) {
        this.customer = customer;
     }

     public boolean isCurrentlyPregnant() {
         return currentlyPregnant;
     }

     public void setCurrentlyPregnant(boolean currentlyPregnant) {
         this.currentlyPregnant = currentlyPregnant;
     }

     public int getNoOfTimesPregnant() {
        return noOfTimesPregnant;
     }

     public void setNoOfTimesPregnant(int noOfTimesPregnant) {
        this.noOfTimesPregnant = noOfTimesPregnant;
     }

    public int getNoOfFullTermPregnancies() {
        return noOfFullTermPregnancies;
    }

    public void setNoOfFullTermPregnancies(int noOfFullTermPregnancies) {
        this.noOfFullTermPregnancies = noOfFullTermPregnancies;
    }

    public int getNoOfPrematureBirths() {
        return noOfPrematureBirths;
    }

    public void setNoOfPrematureBirths(int noOfPrematureBirths) {
        this.noOfPrematureBirths = noOfPrematureBirths;
    }

    public int getNoOfInducedAbortions() {
        return noOfInducedAbortions;
    }

    public void setNoOfInducedAbortions(int noOfInducedAbortions) {
        this.noOfInducedAbortions = noOfInducedAbortions;
    }

    public int getNoOfMiscarriages() {
        return noOfMiscarriages;
    }

    public void setNoOfMiscarriages(int noOfMiscarriages) {
        this.noOfMiscarriages = noOfMiscarriages;
    }

    public int getNoOfChidren() {
        return noOfChidren;
    }

    public void setNoOfChidren(int noOfChidren) {
        this.noOfChidren = noOfChidren;
    }

    
}
