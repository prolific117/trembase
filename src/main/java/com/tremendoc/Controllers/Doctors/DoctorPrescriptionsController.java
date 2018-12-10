/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CustomerPrescriptionsResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorPrescriptionsController {
    public ServiceResponse addPrescription(String medication, String dosage,
            String startDate, String endDate, String specialInstruction, String doctorReason,
            Long patientId, Long consultationId, SessionDetail sessionDetail);
    
    public CustomerPrescriptionsResponse getPrescriptions(Long patientId);
}
