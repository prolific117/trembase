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
public class CustomerCreationResponse extends ServiceResponse{
    
    public CustomerCreationResponse(int code) {
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
    
    @JsonProperty("customerId")
    private Long customerId;
    
    @JsonProperty("inviteCode")
    private String inviteCode;

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

    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
}
