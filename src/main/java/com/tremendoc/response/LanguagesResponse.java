/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.subsresponse.LanguagesProfile;

/**
 *
 * @author prolific
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguagesResponse extends ServiceResponse {
    
    public LanguagesResponse(int code) {
        super(code);
     }
    
    LanguagesProfile languages;

    public LanguagesProfile getLanguages() {
        return languages;
    }

    public void setLanguages(LanguagesProfile languages) {
        this.languages = languages;
    }
    
}
