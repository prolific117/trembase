/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.tremendoc.Controllers.Doctors.ProfileManager;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorDocuments;
import com.tremendoc.Entity.DoctorQualifications;
import com.tremendoc.Entity.Referee;
import com.tremendoc.Entity.Repository.DoctorDocumentsRepository;
import com.tremendoc.Entity.Repository.DoctorQualificationsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.RefereeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class ProfileManagerImpl implements ProfileManager{
    
    @Autowired
    private DoctorDocumentsRepository documentsRepository;
    
    @Autowired
    private DoctorQualificationsRepository qualificationsRepository;
   
    @Autowired
    private RefereeRepository refereeRepository;
    
    @Autowired 
    private DoctorRepository doctorRepository;

    @Override
    public boolean checkCompletion(Doctor doctor) {
       DoctorDocuments documents = documentsRepository.findByDoctor(doctor);
        DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);
        List<Referee> referees = refereeRepository.findByDoctor(doctor);
        
        if(documents == null || qualifications == null || referees.isEmpty()){
            return false;
        }
        else{
             if(!doctor.getIsActive() && !doctor.isAdminDeactivated()){
                 doctor.setIsActive(Boolean.TRUE);
                 doctorRepository.save(doctor);
                 
                 //send mail
             }
             
            return true;
        }
        }

    @Override
    public boolean checkDocouments(Doctor doctor) {
        DoctorDocuments documents = documentsRepository.findByDoctor(doctor);
        
        if(documents == null){
            return false;
        }
        
        return true;
     }

    @Override
    public boolean checkReferees(Doctor doctor) {
        List<Referee> referees = refereeRepository.findByDoctor(doctor);
        
        if(referees.isEmpty()){
            return false;
        }
        
        return true;
      }

    @Override
    public boolean checkAcademicProfile(Doctor doctor) {
        DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);
        
        if(qualifications == null){
            return false;
        }
        
        return true;
      }
    
}
