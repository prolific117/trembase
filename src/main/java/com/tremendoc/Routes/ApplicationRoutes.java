/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.CountriesController;
import com.tremendoc.Controllers.Admin.FAQController;
import com.tremendoc.Controllers.Admin.HealthTipsController;
import com.tremendoc.Controllers.Doctors.SpecialtiesController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CountriesResponse;
import com.tremendoc.response.FAQResponse;
import com.tremendoc.response.HealthTips;
import com.tremendoc.response.SpecialtiesResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author olatunji.oduro
 */
@RestController
@Api(description = "Set of endpoints for general calls (get Countries etc).")
@RequestMapping(path="") // This means URL's start with /demo (after Application path)
public class ApplicationRoutes {
    
    @Autowired
    CountriesController countriesController;
        
    @ApiOperation("Get Countries")
    @GetMapping(path="/countries")
    public CountriesResponse getCountries() {
            // This returns a JSON or XML with the users
            return countriesController.getCountries();
     }
}