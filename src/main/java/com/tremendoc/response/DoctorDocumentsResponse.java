/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDocumentsResponse extends ServiceResponse{
    
    public DoctorDocumentsResponse(int code) {
        super(code);
    }
   
    private String degree;
    private String practicingLicense;   
    private String professionalProfilePhoto;
    private String validId;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPracticingLicense() {
        return practicingLicense;
    }

    public void setPracticingLicense(String practicingLicense) {
        this.practicingLicense = practicingLicense;
    }

    public String getProfessionalProfilePhoto() {
        return professionalProfilePhoto;
    }

    public void setProfessionalProfilePhoto(String proffessionalProfilePhoto) {
        this.professionalProfilePhoto = proffessionalProfilePhoto;
    }

    public String getValidId() {
        return validId;
    }

    public void setValidId(String validId) {
        this.validId = validId;
    }
    
    
}
