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
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 *
 * @author olatunji.oduro
 */
@Entity
@Table(name = "resetToken")
@NamedQueries({
 }     
)
public class ResetToken extends AbstractRepositoryModel implements Serializable{
    
     @Column(name="requestor_status")
    private String requestor_status;
  
    @Column(name="token")
    private String token; 

    public String getRequestor_status() {
        return requestor_status;
    }

    public void setRequestor_status(String requestor_status) {
        this.requestor_status = requestor_status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
