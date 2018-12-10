/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.paystack.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargeResponse {
    
    boolean status;
    String message;
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
