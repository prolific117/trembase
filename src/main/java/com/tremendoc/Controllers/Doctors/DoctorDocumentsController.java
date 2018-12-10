/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.response.DoctorDocumentsResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorDocumentsController {
    public ServiceResponse createDoctorDocuments(String degree, String practicingLicense, 
            String proffessionalProfilePhoto,
            String validId, String nyscCertificate, Long doctorId);
    
    public DoctorDocumentsResponse getDoctorDocuments(Long doctorId);
}
