/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorRefereeController;
import com.tremendoc.Controllers.Doctors.ProfileManager;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorDocuments;
import com.tremendoc.Entity.DoctorQualifications;
import com.tremendoc.Entity.Referee;
import com.tremendoc.Entity.Repository.DoctorDocumentsRepository;
import com.tremendoc.Entity.Repository.DoctorQualificationsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.RefereeRepository;
import com.tremendoc.response.RefereeResponse;
import com.tremendoc.response.SubReferee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class DoctorRefereeControllerImpl implements DoctorRefereeController{
    
    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired
    ProfileManager profileManager;
    
    @Autowired
    private RefereeRepository refereeRepository;

    @Override
    public ServiceResponse createDoctorReferee(String bio, String email, String fullname, String phone, String photo, String workplace, Long doctorId) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //find doctor
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Cannot find doctor");
            return response;
        }
        
        Referee existingReferee = refereeRepository.findByEmail(email, doctor);
        if(existingReferee != null){
            response.setDescription("This referee has already been added");
            return response;
        }
        
        //find qualifications
        Referee referee = new Referee();
        referee.setBio(bio);
        referee.setDoctor(doctor);
        referee.setEmail(email);
        referee.setFullname(fullname);
        referee.setPhone(phone);
        referee.setWorkplace(workplace);
        
        refereeRepository.save(referee);
        
        boolean complete = profileManager.checkCompletion(doctor);
        if(complete == true){
            response.setDescription("Profile Complete");
        }
        else{
            response.setDescription("Referees successfully uploaded, profile still incomplete. Make sure to upload documents and provide academic information");
        }
        
        response.setCode(ServiceResponse.SUCCESS);
        return response; 
    }

    @Override
    public RefereeResponse getDoctorReferees(Long doctorId) {
        RefereeResponse response = new RefereeResponse(ServiceResponse.SUCCESS);
        
        //find doctor
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if(doctor == null){
            response.setDescription("Cannot find doctor");
            return response;
        }
        
        List<Referee> referees = refereeRepository.findByDoctor(doctor);
        List<SubReferee> refereeData = new ArrayList<>();
        
        for(Referee ref : referees){
            SubReferee ref_ = new SubReferee();
            ref_.setBio(ref.getBio());
            ref_.setEmail(ref.getEmail());
            ref_.setFullname(ref.getFullname());
            ref_.setPhone(ref.getPhone());
            ref_.setWorkplace(ref.getWorkplace());
            
            refereeData.add(ref_);
        }
        
        response.setReferees(refereeData);
        
        return response;
    }
    
}
