/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.CustomerLanguagesController;
import com.tremendoc.Controllers.Customers.FavoritesController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.FavoritesResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@Api(description = "Set of endpoints for Managing Customer Favorites.")
@RequestMapping(path="/customer") // This means URL's start with /demo (after Application path)
public class FavoritesRoutes {
    
       @Autowired
      private FavoritesController favoritesController;
    
      @ApiOperation("Add favorite")
      @RequestMapping(value = "/favorite/{doctorId}", method = RequestMethod.POST, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addFav (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("doctorId") String doctorId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = favoritesController.addFavorite(sessionDetail, Long.valueOf(doctorId));
                       
                       
		return response;
	}
        
      @ApiOperation("Remove favorite")
      @RequestMapping(value = "/favorite/{doctorId}", method = RequestMethod.DELETE, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse removeFav (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("doctorId") String doctorId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = favoritesController.removeFavorite(sessionDetail, Long.valueOf(doctorId));
                       
                       
		return response;
	}
     
        
      @ApiOperation("Get Favorites")
      @RequestMapping(value = "/favorites", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse getLanguages (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    FavoritesResponse response = favoritesController.getFavorites(sessionDetail);
                       
		return response;
	}
}
