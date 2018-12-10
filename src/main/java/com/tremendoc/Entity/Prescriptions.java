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
@Table(name = "prescriptions")
public class Prescriptions extends AbstractRepositoryModel implements Serializable {
    
    @Column(name="medication")
    private String medication;
    
    @Column(name="dosage")
    private String dosage;
    
    @Column(name="startDate")
    private String startDate;
    
    @Column(name="endDate")
    private String endDate;
    
    @Column(name="specialInstruction")
    private String specialInstruction;
    
    @Column(name="doctorReason")
    private String doctorReason;
    
    @ManyToOne
    @JoinColumn (name = "doctor", referencedColumnName = "id")
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn (name = "patient", referencedColumnName = "id")
    private Customer patient;
    
    @ManyToOne
    @JoinColumn (name = "consultation", referencedColumnName = "id")
    private Consultation consultation;

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
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
    
    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public String getDoctorReason() {
        return doctorReason;
    }

    public void setDoctorReason(String doctorReason) {
        this.doctorReason = doctorReason;
    }
    
    
    
}
