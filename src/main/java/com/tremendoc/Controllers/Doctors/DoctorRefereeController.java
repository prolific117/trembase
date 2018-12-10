/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.response.RefereeResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorRefereeController {
    
    public ServiceResponse createDoctorReferee(String bio, String email, String fullname,
            String phone, String photo, String workplace, Long doctorId);
    
    public RefereeResponse getDoctorReferees( Long doctorId);
}
