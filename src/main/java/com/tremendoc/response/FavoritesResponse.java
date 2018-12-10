/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
public class FavoritesResponse extends ServiceResponse {
    
    List<DoctorData> doctors;

    public FavoritesResponse(int code) {
        super(code);
    }

    public List<DoctorData> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorData> doctors) {
        this.doctors = doctors;
    }
    
    
}
