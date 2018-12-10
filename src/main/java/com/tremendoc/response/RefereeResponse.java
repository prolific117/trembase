/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.subsresponse.SubNotification;
import java.util.List;

/**
 *
 * @author prolific
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefereeResponse extends ServiceResponse{
    
    public RefereeResponse(int code) {
        super(code);
     }
    
    
    List<SubReferee> referees;

    public List<SubReferee> getReferees() {
        return referees;
    }

    public void setReferees(List<SubReferee> referees) {
        this.referees = referees;
    }

    
   
}
