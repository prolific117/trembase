/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.LanguagesController;
import com.tremendoc.Request.SessionDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@Api(description = "Set of endpoints for Managing Languages.")
@RequestMapping(path="/languages") // This means URL's start with /demo (after Application path)
public class LanguagesOperations {
    
    @Autowired
    LanguagesController languageController;
    
    @ApiOperation("Get Languages")
      @RequestMapping(value = "", method = RequestMethod.POST, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse setLanguages (){
                ServiceResponse response = languageController.getLanguages();
                       
		return response;
	}
}
