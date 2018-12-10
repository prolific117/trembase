/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Customers;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.ConsultationsResponse;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author olatunji.oduro
 */
public interface ConsultationsController {
    
    public ServiceResponse createConsultation(Long patientId,
         Long doctorId, Long specialtyId, String consultationType, String paymentMode);
    
    public ConsultationsResponse getConsultations(Long patientId,
         Long doctorId, Long specialtyId, String consultationType, String paymentMode,
         String consultationStatus,  Date beginning, Calendar ending, int start);
}
