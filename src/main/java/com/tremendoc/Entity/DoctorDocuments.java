/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity;

import com.proxy.leanstack.commons.repository.AbstractRepositoryModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author olatunji.oduro
 */
@Entity
@Table(name = "doctor_qualifications")
@NamedQueries({
 }     
)
public class DoctorDocuments extends AbstractRepositoryModel implements Serializable {
    
    @Column(name="degree")
    private String degree;
  
    @Column(name="practicingLicense")
    private String practicingLicense;   
  
    @Column(name="proffessionalProfilePhoto")
    private String proffessionalProfilePhoto;
    
    @Column(name="validId")
    private String validId;
     
    @OneToOne
    @JoinColumn (name = "doctor", referencedColumnName = "id")
    private Doctor doctor;
  
    @Column(name="nyscCertificate")
    private String nyscCertificate;   

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

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

    public String getProffessionalProfilePhoto() {
        return proffessionalProfilePhoto;
    }

    public void setProffessionalProfilePhoto(String proffessionalProfilePhoto) {
        this.proffessionalProfilePhoto = proffessionalProfilePhoto;
    }

    public String getValidId() {
        return validId;
    }

    public void setValidId(String validId) {
        this.validId = validId;
    }

    public String getNyscCertificate() {
        return nyscCertificate;
    }

    public void setNyscCertificate(String nyscCertificate) {
        this.nyscCertificate = nyscCertificate;
    }
  
   
    
    
}
