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
public class DoctorQualifications extends AbstractRepositoryModel implements Serializable {
    @Column(name="university")
    private String university;
  
    @Column(name="location")
    private String location;   
  
    @Column(name="degree")
    private String degree;
    
    @Column(name="year")
    private String year;
    
    @Column(name="folioNumber")
    private String folioNumber;
    
    @Column(name="licenseExpiryDate")
    private String licenseExpiryDate;
    
    @Column(name="housemanshipHospital")
    private String housemanshipHospital;
    
    @Column(name="housemanship_location")
    private String housemanshipLocation;
    
    @Column(name="housemanshipYear")
    private String housemanshipYear;
    
    @Column(name="hospitalOfPracticeName")
    private String hospitalOfPracticeName;
     
    @Column(name="hospitalOfPracticeLocation")
    private String hospitalOfPracticeLocation;
    
    @Column(name="hospitalOfPracticePhone")
    private String hospitalOfPracticePhone;
    
    @OneToOne
    @JoinColumn (name = "doctor", referencedColumnName = "id")
    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFolioNumber() {
        return folioNumber;
    }

    public void setFolioNumber(String folioNumber) {
        this.folioNumber = folioNumber;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getHousemanshipHospital() {
        return housemanshipHospital;
    }

    public void setHousemanshipHospital(String housemanshipHospital) {
        this.housemanshipHospital = housemanshipHospital;
    }

    public String getHousemanshipLocation() {
        return housemanshipLocation;
    }

    public void setHousemanshipLocation(String housemanshipLocation) {
        this.housemanshipLocation = housemanshipLocation;
    }

    public String getHousemanshipYear() {
        return housemanshipYear;
    }

    public void setHousemanshipYear(String housemanshipYear) {
        this.housemanshipYear = housemanshipYear;
    }

    public String getHospitalOfPracticeName() {
        return hospitalOfPracticeName;
    }

    public void setHospitalOfPracticeName(String hospitalOfPracticeName) {
        this.hospitalOfPracticeName = hospitalOfPracticeName;
    }

    public String getHospitalOfPracticeLocation() {
        return hospitalOfPracticeLocation;
    }

    public void setHospitalOfPracticeLocation(String hospitalOfPracticeLocation) {
        this.hospitalOfPracticeLocation = hospitalOfPracticeLocation;
    }

    public String getHospitalOfPracticePhone() {
        return hospitalOfPracticePhone;
    }

    public void setHospitalOfPracticePhone(String hospitalOfPracticePhone) {
        this.hospitalOfPracticePhone = hospitalOfPracticePhone;
    }
    
    
   
}
