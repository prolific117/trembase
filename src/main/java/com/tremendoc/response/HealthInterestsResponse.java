/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.subsresponse.CustomerHealthInterestResponse;
import java.util.List;

/**
 *
 * @author prolific
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthInterestsResponse extends ServiceResponse {
    
     public HealthInterestsResponse(int code) {
        super(code);
    }
     
    List<CustomerHealthInterestResponse> interests;

    public List<CustomerHealthInterestResponse> getInterests() {
        return interests;
    }

    public void setInterests(List<CustomerHealthInterestResponse> interests) {
        this.interests = interests;
    }
}
