/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.AdminImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.CountriesController;
import com.tremendoc.Entity.Repository.CountriesRepository;
import com.tremendoc.response.CountriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class CountriesControllerImpl implements CountriesController{
    
    @Autowired
    CountriesRepository countriesRepository;

    @Override
    public CountriesResponse getCountries() {
        CountriesResponse response = new CountriesResponse(ServiceResponse.SUCCESS);
        
        response.setCountries(countriesRepository.findAll());
        
        return response;
    }
    
}
