/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Entity.Countries;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
public class CountriesResponse extends ServiceResponse{
    
     public CountriesResponse(int code) {
        super(code);
     }
     
     List<Countries> countries;

    public List<Countries> getCountries() {
        return countries;
    }

    public void setCountries(List<Countries> countries) {
        this.countries = countries;
    }    
}
