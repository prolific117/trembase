/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialtiesResponse extends ServiceResponse{
    
    public SpecialtiesResponse(int code) {
        super(code);
     }
    
    List<SpecialtyData> specialtyData;

    public List<SpecialtyData> getSpecialtyData() {
        return specialtyData;
    }

    public void setSpecialtyData(List<SpecialtyData> specialtyData) {
        this.specialtyData = specialtyData;
    }
}
