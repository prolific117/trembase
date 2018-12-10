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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author olatunji.oduro
 */
@Entity
@Table(name = "doctor")
@NamedQueries({
 }     
)
public class Doctor extends AbstractRepositoryModel implements Serializable {
    
    
    @Column(name="firstname")
    private String firstname;
  
    @Column(name="lastname")
    private String lastName;   
  
    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;
    
    @Column(name="password")
    private String password;
    
    @Column(name="gender")
    private String gender;
    
    @Column(name ="onlineStatus")
    private String onlineStatus;
    
    @Column(name="preferredUsername")
    private String preferredUsername;
    
    @Column(name="dob")
    private String dob;
  
    @Column(name="preferredLanguage")
    private String preferredLanguage;   
  
    @Column(name="country")
    private String country;

    @OneToOne
    @JoinColumn (name = "category", referencedColumnName = "id")
    private Category category;
    
    @OneToOne
    @JoinColumn (name = "specialty", referencedColumnName = "id")
    private Specialty specialty;
    
    @Column(name="homeAddress")
    private String homeAddress;
    
    @Column(name="state")
    private String state;
    
    @Column(name="adminDeactivated")
    private boolean adminDeactivated = false;
    
    @Column(name="practiceDescription")
    private String practiceDescription;
    
    @Column(name="currentProfessionalStatus")
    private String currentProfessionalStatus;
     
    @Column(name="goals")
    private String goals;
    
    @Column(name="inviteCode")
    private String inviteCode;

    public boolean isAdminDeactivated() {
        return adminDeactivated;
    }

    public void setAdminDeactivated(boolean adminDeactivated) {
        this.adminDeactivated = adminDeactivated;
    }
    
    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
    
    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
    
    public String getPracticeDescription() {
        return practiceDescription;
    }

    public void setPracticeDescription(String practiceDescription) {
        this.practiceDescription = practiceDescription;
    }

    public String getCurrentProfessionalStatus() {
        return currentProfessionalStatus;
    }

    public void setCurrentProfessionalStatus(String currentProfessionalStatus) {
        this.currentProfessionalStatus = currentProfessionalStatus;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
     
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
     
    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    
    
}
