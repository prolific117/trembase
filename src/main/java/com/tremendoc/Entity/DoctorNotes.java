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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author olatunji.oduro
 */
@Entity
@Table(name = "notes")
public class DoctorNotes extends AbstractRepositoryModel implements Serializable {
    
    @Column(name="symptoms", length=2000)
    private String symptoms;
    
    @Column(name="diagnosis", length=2000)
    private String diagnosis;
    
    @Column(name="treatment", length=2000)
    private String treatment;
    
    @ManyToOne
    @JoinColumn (name = "doctor", referencedColumnName = "id")
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn (name = "patient", referencedColumnName = "id")
    private Customer patient;
    
    @ManyToOne
    @JoinColumn (name = "consultation", referencedColumnName = "id")
    private Consultation consultation;

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Customer getPatient() {
        return patient;
    }

    public void setPatient(Customer patient) {
        this.patient = patient;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    
    
}
