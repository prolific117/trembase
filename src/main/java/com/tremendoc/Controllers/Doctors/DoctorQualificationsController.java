/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.response.DoctorQualificationsResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorQualificationsController {
    public ServiceResponse createDoctorQualifications(String university, String location, String degree,
            String year, String folioNumber, String licenseExpiryDate, String housemanshipHospital,
            String housemanshipLocation,
            String housemanshipYear, String hospitalOfPracticeName, String hospitalOfPracticeLocation,  
            String hospitalOfPracticePhone, Long doctorId);
    
    public DoctorQualificationsResponse getDoctorDocuments(Long doctorId);
}
