/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorResponse extends ServiceResponse{
    public DoctorResponse(int code) {
        super(code);
    }


    public DoctorData getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorData doctor) {
        this.doctor = doctor;
    }

    DoctorData doctor;
}
