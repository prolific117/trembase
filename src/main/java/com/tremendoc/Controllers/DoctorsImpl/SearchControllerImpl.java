/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.SearchController;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorDocuments;
import com.tremendoc.Entity.DoctorQualifications;
import com.tremendoc.Entity.Repository.DoctorDocumentsRepository;
import com.tremendoc.Entity.Repository.DoctorQualificationsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.SpecialtyRepository;
import com.tremendoc.Entity.Specialty;
import com.tremendoc.response.DoctorData;
import com.tremendoc.response.DoctorResponse;
import com.tremendoc.response.DoctorsResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class SearchControllerImpl implements SearchController{
    
    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private DoctorDocumentsRepository documentsRepository;
    
    @Autowired
    private DoctorQualificationsRepository qualificationsRepository;
     
    @Override
    public DoctorsResponse searchSpecialty(Long specialtyId, int page) {
        
      DoctorsResponse response = new DoctorsResponse(ServiceResponse.ERROR);
      
      Specialty spec = specialtyRepository.getById(specialtyId);
      if(spec == null){
          response.setDescription("Specialty does not exist");
          return response;
      }
      
      page = (page - 1) * 50;
      List<Doctor> doctors = doctorRepository.getDoctorBySpecialty(page, spec);
      List<DoctorData> refinedData = new ArrayList<>();
      
      int count = doctorRepository.countDoctorsBySpecialty(spec).intValue();
      
      if(count == 0){
          response.setCode(ServiceResponse.SUCCESS);
          response.setTotalCount(0);
          response.setNoOfTotalPages(0);
          response.setSizeOfCurrentList(0);
          return response;
      }
      
      response.setSizeOfCurrentList(doctors.size());
      response.setTotalCount(count);
      
      Double pages_ = Math.ceil(Double.parseDouble(count+"")/ Double.parseDouble(50.0+""));
      
      response.setNoOfTotalPages(pages_.intValue());
      
      for(Doctor doctor : doctors){
          
          DoctorDocuments docs = documentsRepository.findByDoctor(doctor);
          DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);
          
          DoctorData data = new DoctorData();
          data.setEmail(doctor.getEmail());
          data.setFirstname(doctor.getFirstname());
          data.setGender(doctor.getGender());
          data.setId(doctor.getId());
          data.setHospital(qualifications.getHospitalOfPracticeName());
          data.setImage(docs.getProffessionalProfilePhoto());
          data.setLastName(doctor.getLastName());
          data.setPhone(doctor.getPhone());
          data.setOnlineStatus(doctor.getOnlineStatus());
          data.setSpecialty(doctor.getSpecialty().getName());
          
          refinedData.add(data);
      }
      
      response.setDoctors(refinedData);
      return response;
    }

    @Override
    public DoctorsResponse searchByName(String name, int page) {
      DoctorsResponse response = new DoctorsResponse(ServiceResponse.ERROR);
      
      page = (page - 1) * 50;
      List<Doctor> doctors = doctorRepository.getDoctorByName(name, page);
      List<DoctorData> refinedData = new ArrayList<>();
      
      int count = doctorRepository.countDoctorsByName(name).intValue();
      
      if(count == 0){
          response.setCode(ServiceResponse.SUCCESS);
          response.setTotalCount(0);
          response.setNoOfTotalPages(0);
          response.setSizeOfCurrentList(0);
          return response;
      }
      
      response.setSizeOfCurrentList(doctors.size());
      response.setTotalCount(count);
      
      Double pages_ = Math.ceil(Double.parseDouble(count+"")/ Double.parseDouble(50.0+""));
      
      response.setNoOfTotalPages(pages_.intValue());
      
      for(Doctor doctor : doctors){
          
          DoctorDocuments docs = documentsRepository.findByDoctor(doctor);
          DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);
          
          DoctorData data = new DoctorData();
          data.setEmail(doctor.getEmail());
          data.setFirstname(doctor.getFirstname());
          data.setGender(doctor.getGender());
          data.setId(doctor.getId());
          data.setHospital(qualifications.getHospitalOfPracticeName());
          data.setImage(docs.getProffessionalProfilePhoto());
          data.setLastName(doctor.getLastName());
          data.setPhone(doctor.getPhone());
          data.setOnlineStatus(doctor.getOnlineStatus());
          data.setSpecialty(doctor.getSpecialty().getName());
          
          refinedData.add(data);
      }
      
      response.setDoctors(refinedData);
      return response; 
    
    }

    @Override
    public DoctorResponse searchById(Long id) {
      DoctorResponse response = new DoctorResponse(ServiceResponse.ERROR);
      
      Doctor doctor = doctorRepository.findByDoctorId(id);
      
      if(doctor == null){
          response.setDescription("Doctor does not exist");
          return response;
      }
      
        DoctorDocuments docs = documentsRepository.findByDoctor(doctor);
        DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);

        DoctorData data = new DoctorData();
        data.setEmail(doctor.getEmail());
        data.setFirstname(doctor.getFirstname());
        data.setGender(doctor.getGender());
        data.setId(doctor.getId());
        data.setHospital(qualifications.getHospitalOfPracticeName());
        data.setImage(docs.getProffessionalProfilePhoto());
        data.setLastName(doctor.getLastName());
        data.setPhone(doctor.getPhone());
        data.setOnlineStatus(doctor.getOnlineStatus());
        data.setSpecialty(doctor.getSpecialty().getName());
          
        response.setCode(ServiceResponse.SUCCESS);
        response.setDoctor(data);
        return response; 
       }

    @Override
    public DoctorsResponse getOnline() {
       DoctorsResponse response = new DoctorsResponse(ServiceResponse.ERROR);
      
       List<Doctor> doctors = doctorRepository.getDoctorByStatus("ONLINE");
       List<DoctorData> refinedData = new ArrayList<>();
      
       int count = doctorRepository.countByStatus("ONLINE").intValue();
      
       if(count == 0){
          response.setCode(ServiceResponse.SUCCESS);
          response.setTotalCount(0);
          response.setNoOfTotalPages(0);
          response.setSizeOfCurrentList(0);
          return response;
      }
      
      response.setSizeOfCurrentList(doctors.size());
      response.setTotalCount(count);
      
      Double pages_ = Math.ceil(Double.parseDouble(count+"")/ Double.parseDouble(50.0+""));
      
      response.setNoOfTotalPages(pages_.intValue());
      
      for(Doctor doctor : doctors){
          
          DoctorDocuments docs = documentsRepository.findByDoctor(doctor);
          DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);
          
          DoctorData data = new DoctorData();
          data.setEmail(doctor.getEmail());
          data.setFirstname(doctor.getFirstname());
          data.setGender(doctor.getGender());
          data.setId(doctor.getId());
          data.setHospital(qualifications.getHospitalOfPracticeName());
          data.setImage(docs.getProffessionalProfilePhoto());
          data.setLastName(doctor.getLastName());
          data.setPhone(doctor.getPhone());
          data.setOnlineStatus(doctor.getOnlineStatus());
          data.setSpecialty(doctor.getSpecialty().getName());
          
          refinedData.add(data);
      }
      
      response.setDoctors(refinedData);
      return response; 
    }

    @Override
    public DoctorResponse getAvailableDoctor(Long specialtyId) {
       DoctorResponse response = new DoctorResponse(ServiceResponse.ERROR);
       
        Specialty spec = specialtyRepository.getById(specialtyId);
        if(spec == null){
            response.setDescription("Specialty does not exist");
            return response;
        }
      
        Doctor doctor = doctorRepository.getOnlineBySpecialty("ONLINE", spec);

        if(doctor == null){
            response.setDescription("No doctors found online for provided specialty");
            return response;
        }
      
        DoctorDocuments docs = documentsRepository.findByDoctor(doctor);
        DoctorQualifications qualifications = qualificationsRepository.findByDoctor(doctor);

        DoctorData data = new DoctorData();
        data.setEmail(doctor.getEmail());
        data.setFirstname(doctor.getFirstname());
        data.setGender(doctor.getGender());
        data.setId(doctor.getId());
        data.setHospital(qualifications.getHospitalOfPracticeName());
        data.setImage(docs.getProffessionalProfilePhoto());
        data.setLastName(doctor.getLastName());
        data.setPhone(doctor.getPhone());
        data.setOnlineStatus(doctor.getOnlineStatus());
        data.setSpecialty(doctor.getSpecialty().getName());
        
        response.setCode(ServiceResponse.SUCCESS);
        response.setDoctor(data);
        return response;  
    
    }

    
}
