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
public class CustomerNotesResponse extends ServiceResponse{
     
    public CustomerNotesResponse(int code) {
        super(code);
    }
     
    List<DoctorNotesData> notes;

    public List<DoctorNotesData> getNotes() {
        return notes;
    }

    public void setNotes(List<DoctorNotesData> notes) {
        this.notes = notes;
    }
}
