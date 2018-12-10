/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorDocumentsController;
import com.tremendoc.Controllers.Doctors.ProfileManager;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorDocuments;
import com.tremendoc.Entity.Repository.DoctorDocumentsRepository;
import com.tremendoc.Entity.Repository.DoctorQualificationsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.RefereeRepository;
import com.tremendoc.response.DoctorDocumentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class DoctorDocumentsControllerImpl implements DoctorDocumentsController {

    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired
    ProfileManager profileManager;
    
    @Autowired
    private DoctorDocumentsRepository documentsRepository;
    
    @Override
    public ServiceResponse createDoctorDocuments(String degree, String practicingLicense, String proffessionalProfilePhoto, String validId, String nyscCertificate, Long doctorId) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //find doctor
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Cannot find doctor");
            return response;
        }
        
        //find qualifications
        DoctorDocuments documents = documentsRepository.findByDoctor(doctor);
        if(documents == null){
            documents = new DoctorDocuments();
        }
        
        documents.setDegree(degree);
        documents.setDoctor(doctor);
        documents.setNyscCertificate(nyscCertificate);
        documents.setPracticingLicense(practicingLicense);
        documents.setProffessionalProfilePhoto(proffessionalProfilePhoto);
        documents.setValidId(validId);
        
        documentsRepository.save(documents);
        
        boolean complete = profileManager.checkCompletion(doctor);
        if(complete == true){
            response.setDescription("Profile Complete");
        }
        else{
            response.setDescription("Documents successfully uploaded, profile still incomplete. Make sure to add referees and upload qualification information");
        }
        
        response.setCode(ServiceResponse.SUCCESS);
        return response; 
    
    }

    @Override
    public DoctorDocumentsResponse getDoctorDocuments(Long doctorId) {
        DoctorDocumentsResponse response = new DoctorDocumentsResponse(ServiceResponse.ERROR);
   
        //find doctor
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Cannot find doctor");
            return response;
        }
        
        //find qualifications
        DoctorDocuments documents = documentsRepository.findByDoctor(doctor);
        if(documents == null){
            response.setDescription("No Documents Uploaded Yet");
            return response;
        }
        
        response.setDegree(documents.getDegree());
        response.setPracticingLicense(documents.getPracticingLicense());
        response.setProfessionalProfilePhoto(documents.getProffessionalProfilePhoto());
        response.setValidId(documents.getValidId());
        
        
        response.setCode(ServiceResponse.SUCCESS);
        return response; 
    
    }
    
}
