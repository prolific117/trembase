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
public class DoctorQualificationsResponse extends ServiceResponse{
    
    public DoctorQualificationsResponse(int code) {
        super(code);
    }
    
    private String university;
    private String location;   
    private String degree;
    private String year;
    private String folioNumber;
    private String licenseExpiryDate;
    private String housemanshipHospital;
    private String housemanshipLocation;
    private String housemanshipYear;
    private String hospitalOfPracticeName;
    private String hospitalOfPracticeLocation;
    private String hospitalOfPracticePhone;

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
