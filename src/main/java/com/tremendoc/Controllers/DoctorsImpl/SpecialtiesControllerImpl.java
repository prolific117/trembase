/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.SpecialtiesController;
import com.tremendoc.Entity.Category;
import com.tremendoc.Entity.Repository.CategoryRepository;
import com.tremendoc.Entity.Repository.SpecialtyRepository;
import com.tremendoc.Entity.Specialty;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.Categories;
import com.tremendoc.response.CategoriesResponse;
import com.tremendoc.response.SpecialtiesResponse;
import com.tremendoc.response.SpecialtyData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class SpecialtiesControllerImpl implements SpecialtiesController{
 
   @Autowired
   private CategoryRepository categoryRepository;
   
   @Autowired
   private SpecialtyRepository specialtyRepository;
   
    @Override
    public CategoriesResponse getCategories() {
        CategoriesResponse response = new CategoriesResponse(ServiceResponse.SUCCESS);
        
        List<Category> allCategories = categoryRepository.findAll();
        List<Categories> categories = new ArrayList<>();
        
        for(Category category : allCategories){
            Categories cat = new Categories();
            cat.setName(category.getName());
            cat.setId(category.getId());
            cat.setImagePath(category.getImagePath());
            categories.add(cat);
        }
        
        response.setCategories(categories);
        return response;
    }

    @Override
    public ServiceResponse addCategory(String category, String imagePath, String categoryCode, SessionDetail sessionDetail) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        Category cat = categoryRepository.findByName("category");
        
        if(cat == null){
            cat = new Category();
        }
        else{
            response.setDescription("A category with this name exist, will proceed to update");
        }
        
        cat.setCategoryCode(categoryCode);
        cat.setName(category);
        cat.setImagePath(imagePath);
        
        categoryRepository.save(cat);
        
        response.setCode(ServiceResponse.SUCCESS);
        
        return response;
    }

    @Override
    public ServiceResponse addSpecialty(String specialty, String imagePath, int discount, Long categoryId, SessionDetail sessionDetail) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);

               Category cat = categoryRepository.getById(categoryId);

               if(cat == null){
                   response.setDescription("Invalid category");
                   return response;
               }
               
               Specialty existing = specialtyRepository.findByName(specialty);
               if(existing != null){
                   response.setDescription("Specialty already exists");
                   return response;
               }

               Specialty specialty_ = new Specialty();
               specialty_.setCategory(cat);
               specialty_.setDiscount(discount);
               specialty_.setName(specialty);
               specialty_.setImagePath(imagePath);
               //specialty_.setId();
               
               specialtyRepository.save(specialty_);
              
               response.setCode(ServiceResponse.SUCCESS);

               return response;  
     }

    
    @Override
    public SpecialtiesResponse getSpecialties(Long categoryId) {
            SpecialtiesResponse response = new SpecialtiesResponse(ServiceResponse.SUCCESS);

            Category cat = categoryRepository.getById(categoryId);
        
            if(cat == null){
                response.setDescription("Invalid category");
                return response;
            }
        
            List<Specialty> allspecialties = specialtyRepository.findByCategory(cat);
            List<SpecialtyData> specs = new ArrayList<>();

            for(Specialty specialty : allspecialties){
                SpecialtyData data = new SpecialtyData();
                
                data.setSpecialtyCode(specialty.getSpecialtyCode());
                data.setSpecialtyName(specialty.getName());
                data.setAvailableDiscount(specialty.getDiscount());
                data.setId(specialty.getId());
                data.setImagePath(specialty.getImagePath());
                
                specs.add(data);
            }

            response.setSpecialtyData(specs);
            return response;
    }
}
