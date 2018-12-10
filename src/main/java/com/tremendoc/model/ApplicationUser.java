/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.model;

import com.tremendoc.Entity.Customer;
import com.proxy.leanstack.commons.security.AuthenticatedUser;

public class ApplicationUser extends AuthenticatedUser {
        
   //define other properties required to expose
   private Long id;
   private String userName;
   private String status;

    public ApplicationUser(Long id, String userName, String status){
        super(id, userName, status);
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
      
}
