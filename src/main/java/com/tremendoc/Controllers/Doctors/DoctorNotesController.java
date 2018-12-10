/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CustomerNotesResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorNotesController {
    
    public ServiceResponse addNote(String symptoms, String diagnosis,
            String treatment, Long patientId, Long consultationId, SessionDetail sessionDetail);
    
    public CustomerNotesResponse getNotes(Long patientId);
}
