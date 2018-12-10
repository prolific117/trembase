/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.FavoritesController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorDocuments;
import com.tremendoc.Entity.DoctorQualifications;
import com.tremendoc.Entity.Favorites;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.DoctorDocumentsRepository;
import com.tremendoc.Entity.Repository.DoctorQualificationsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.FavoritesRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.DoctorData;
import com.tremendoc.response.FavoritesResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class FavoritesControllerImpl implements FavoritesController{
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    private DoctorDocumentsRepository documentsRepository;
    
    @Autowired
    private DoctorQualificationsRepository qualificationsRepository;
    
    @Autowired
    FavoritesRepository favoritesRepository;
    
    @Autowired 
    private DoctorRepository doctorRepository;

    @Override
    public ServiceResponse addFavorite(SessionDetail sessionDetail, Long doctorId) {
       ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Customer customer = customerRepository.getOne(user.getId());
        
        if(customer == null){
            response.setDescription("User does not exist, Cannot add favorite");
            return response;
        }
        
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        
        if(doctor == null){
            response.setDescription("Doctor does not exist, Cannot add favorite");
            return response;
        } 
        
        Favorites favorite = favoritesRepository.retrieve(doctor, customer);
        
        if(favorite == null)
          favorite = new Favorites();
        
        favorite.setCustomer(customer);
        favorite.setDoctor(doctor);
        
        favoritesRepository.save(favorite);
        
        response.setCode(ServiceResponse.SUCCESS);
        
        return response;
    }

    @Override
    public ServiceResponse removeFavorite(SessionDetail sessionDetail, Long doctorId) {
        
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Customer customer = customerRepository.getOne(user.getId());
        
        if(customer == null){
            response.setDescription("User does not exist, Cannot add favorite");
            return response;
        }
        
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        
        if(doctor == null){
            response.setDescription("Doctor does not exist, Cannot add favorite");
            return response;
        } 
        
        Favorites favorite = favoritesRepository.retrieve(doctor, customer);
        
        if(favorite == null){
            response.setDescription("Cannot update data");
            return response;
        } 
        
        
        favorite.setIsActive(Boolean.FALSE);
        
        favoritesRepository.save(favorite);
        
        response.setCode(ServiceResponse.SUCCESS);  
        
        return response;
    }

    @Override
    public FavoritesResponse getFavorites(SessionDetail sessionDetail) {
        
        FavoritesResponse response = new FavoritesResponse(ServiceResponse.ERROR);
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Customer customer = customerRepository.getOne(user.getId());
        
        if(customer == null){
            response.setDescription("User does not exist, Cannot retrieve favorites");
            return response;
        }
        
        List<Favorites> favorites = favoritesRepository.retrieve(customer);
        List<DoctorData> doctors = new ArrayList<>();
         
        for(Favorites fav :  favorites){
            
            DoctorData data = new DoctorData();
            data.setEmail(fav.getDoctor().getEmail());
            data.setFirstname(fav.getDoctor().getFirstname());
            data.setLastName(fav.getDoctor().getLastName());
            data.setGender(fav.getDoctor().getGender());
            data.setSpecialty(fav.getDoctor().getSpecialty().getName());
            
            DoctorQualifications qualifications = qualificationsRepository.findByDoctor(fav.getDoctor());
            DoctorDocuments documents = documentsRepository.findByDoctor(fav.getDoctor());
            
            if(qualifications != null){
                data.setHospital(qualifications.getHospitalOfPracticeName());
            }
            
            if(documents != null){
                data.setImage(documents.getProffessionalProfilePhoto());
            }
            
            doctors.add(data);
        }
        
        response.setDoctors(doctors);
        response.setCode(ServiceResponse.SUCCESS);
      
        return response;
    }
    
}
