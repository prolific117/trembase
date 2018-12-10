/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorQualificationsController;
import com.tremendoc.Controllers.Doctors.ProfileManager;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorQualifications;
import com.tremendoc.Entity.Repository.CategoryRepository;
import com.tremendoc.Entity.Repository.DoctorDocumentsRepository;
import com.tremendoc.Entity.Repository.DoctorQualificationsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.RefereeRepository;
import com.tremendoc.response.DoctorQualificationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class DoctorQualificationsControllerImpl implements DoctorQualificationsController{

    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired
    ProfileManager profileManager;
   
    @Autowired
    private DoctorQualificationsRepository qualificationsRepository;
    
    @Autowired
    private DoctorDocumentsRepository documentsRepository;
   
    @Override
    public ServiceResponse createDoctorQualifications(String university, String location, String degree, 
            String year, String folioNumber, String licenseExpiryDate, String housemanshipHospital, 
            String housemanshipLocation, String housemanshipYear, String hospitalOfPracticeName, 
            String hospitalOfPracticeLocation, String hospitalOfPracticePhone, Long doctorId) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //find doctor
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Cannot find doctor");
            return response;
        }
        
        //find qualifications
        DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);
        if(qualifications == null){
            qualifications = new DoctorQualifications();
        }
        
        qualifications.setDegree(degree);
        qualifications.setUniversity(university);
        qualifications.setLocation(location);
        qualifications.setDegree(degree);
        qualifications.setYear(year);
        qualifications.setFolioNumber(folioNumber);
        qualifications.setLicenseExpiryDate(licenseExpiryDate);
        qualifications.setHousemanshipHospital(housemanshipHospital);
        qualifications.setHousemanshipLocation(housemanshipLocation);
        qualifications.setHousemanshipYear(housemanshipYear);
        qualifications.setHospitalOfPracticeName(hospitalOfPracticeName);
        qualifications.setHousemanshipLocation(housemanshipLocation);
        qualifications.setHospitalOfPracticePhone(hospitalOfPracticePhone);
        qualifications.setHospitalOfPracticeLocation(hospitalOfPracticeLocation);
        qualifications.setDoctor(doctor);
        
        qualificationsRepository.save(qualifications);
        
        boolean complete = profileManager.checkCompletion(doctor);
        if(complete == true){
            response.setDescription("Profile Complete");
        }
        else{
            response.setDescription("Academic info successfully uploaded, profile still incomplete. Make sure to upload documents and add referees");
        }
        
        response.setCode(ServiceResponse.SUCCESS);
        return response;
    }

    @Override
    public DoctorQualificationsResponse getDoctorDocuments(Long doctorId) {
        DoctorQualificationsResponse response = new DoctorQualificationsResponse(ServiceResponse.ERROR);
        
        //find doctor
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Cannot find doctor");
            return response;
        }
        
        //find qualifications
        DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);
        if(qualifications == null){
            response.setDescription("Qualifications Data are yet to be provided");
            return response;
        }
        
        response.setDegree(qualifications.getDegree());
        response.setFolioNumber(qualifications.getFolioNumber());
        response.setHospitalOfPracticeLocation(qualifications.getHospitalOfPracticeLocation());
        response.setHospitalOfPracticeName(qualifications.getHospitalOfPracticeName());
        response.setHospitalOfPracticePhone(qualifications.getHospitalOfPracticePhone());
        response.setHousemanshipHospital(qualifications.getHousemanshipHospital());
        response.setHousemanshipLocation(qualifications.getHousemanshipLocation());
        response.setHousemanshipYear(qualifications.getHousemanshipYear());
        response.setLicenseExpiryDate(qualifications.getLicenseExpiryDate());
        response.setLocation(qualifications.getLocation());
        response.setUniversity(qualifications.getUniversity());
        response.setYear(qualifications.getYear());
        
        response.setCode(ServiceResponse.SUCCESS);
        
        return response;
        
    }
}
