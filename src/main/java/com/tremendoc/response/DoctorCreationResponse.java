/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;

/**
 *
 * @author prolific
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorCreationResponse extends ServiceResponse{
    
    public DoctorCreationResponse(int code) {
        super(code);
    }
    
    @JsonProperty("firstName")
    private String firstname;

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

    
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
            
    @JsonProperty("lastName")
    private String lastName;
            
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("sessionId")
    private String sessionId;
    
    @JsonProperty("age")
    private String age;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("image")
    private String image;
    
    @JsonProperty("doctorId")
    private Long doctorId;
    
    @JsonProperty("inviteCode")
    private String inviteCode;
    
    private boolean profileComplete;
    private boolean hasUploadedDocuments;
    private boolean hasUploadedAcademicInfo;
    private boolean hasUploadedReferes;

    public boolean isProfileComplete() {
        return profileComplete;
    }

    public void setProfileComplete(boolean profileComplete) {
        this.profileComplete = profileComplete;
    }
    
    public boolean isHasUploadedDocuments() {
        return hasUploadedDocuments;
    }

    public void setHasUploadedDocuments(boolean hasUploadedDocuments) {
        this.hasUploadedDocuments = hasUploadedDocuments;
    }

    public boolean isHasUploadedAcademicInfo() {
        return hasUploadedAcademicInfo;
    }

    public void setHasUploadedAcademicInfo(boolean hasUploadedAcademicInfo) {
        this.hasUploadedAcademicInfo = hasUploadedAcademicInfo;
    }

    public boolean isHasUploadedReferes() {
        return hasUploadedReferes;
    }

    public void setHasUploadedReferes(boolean hasUploadedReferes) {
        this.hasUploadedReferes = hasUploadedReferes;
    }
    
    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long customerId) {
        this.doctorId = customerId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
 
    
}
