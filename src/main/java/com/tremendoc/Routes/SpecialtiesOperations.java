/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.SpecialtiesController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CategoriesResponse;
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
@Api(description = "Set of endpoints for managing specialties.")
@RequestMapping(path="") // This means URL's start with /demo (after Application path)
public class SpecialtiesOperations {
    
    @Autowired
    SpecialtiesController specialtiesController;
    
    @ApiOperation("Retrieve all categories")
        @GetMapping(path="/categories/get")
	public CategoriesResponse getCategories() {
           // This returns a JSON or XML with the users
           return specialtiesController.getCategories();
     }
        
    @ApiOperation("Retrieve all specialties")
    @GetMapping(path="/specialties/get")
        public SpecialtiesResponse getSpecialties(@PathVariable("categoryId") String categoryId) {
            // This returns a JSON or XML with the users
            return specialtiesController.getSpecialties(Long.valueOf(categoryId));
     }
    
    @ApiOperation("Add a category")
    @RequestMapping(value = "/category/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addCat (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "category", required = true, value = "name of category", defaultValue = "") @RequestParam("category") String category,
		@ApiParam(name = "imagePath", required = true, value = "image path", defaultValue = "") @RequestParam("imagePath") String imagePath,
		@ApiParam(name = "categoryCode", required = true, value = "3 letter code for category", defaultValue = "") @RequestParam("categoryCode") String categoryCode,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
                ServiceResponse response =  specialtiesController.addCategory(category, imagePath, categoryCode, sessionDetail);
		return response; 
     }
        
    @ApiOperation("Add or edit a specialty")
    @RequestMapping(value = "/specialty/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addSpec (
              @RequestHeader(name = "sessionid", required = true) final String sessionId,
              @ApiParam(name = "categoryId", required = true, value = "category id", defaultValue = "") @RequestParam("categoryId") String categoryId,
	      @ApiParam(name = "specialty", required = true, value = "specialty name", defaultValue = "") @RequestParam("specialty") String specialty,
              @ApiParam(name = "imagePath", required = true, value = "image path", defaultValue = "") @RequestParam("imagePath") String imagePath,
              @ApiParam(name = "discount", required = false, value = "discount (percentage) e.g 10, 20, 20", defaultValue = "") @RequestParam("discount") Integer discount,
              @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                   
              ServiceResponse response =  specialtiesController.addSpecialty(specialty, imagePath, discount, Long.valueOf(categoryId), sessionDetail);
              return response; 
     }
}
