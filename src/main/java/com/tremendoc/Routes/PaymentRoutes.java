/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Routes;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Customers.CustomerLanguagesController;
import com.tremendoc.Controllers.Customers.PaymentsController;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CardList;
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
@Api(description = "Set of endpoints for Managing Payments.")
@RequestMapping(path="/payment") // This means URL's start with /demo (after Application path)
public class PaymentRoutes {
    
    @Autowired
    private PaymentsController paymentsController;
    
    @ApiOperation("Do Refund")
    @RequestMapping(value = "/refund", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse refund (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "amount", required = true, value = "amount", defaultValue = "") @RequestParam("amount") int amount,
	        @ApiParam(name = "transref", required = true, value = "transref", defaultValue = "") @RequestParam("transref") String transref,
	        @ApiParam(name = "currency", required = true, value = "currency", defaultValue = "NGN") @RequestParam("currency") String currency,
	        @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       paymentsController.doRefund(amount, transref, currency);
                       
		return response;
	}
        
    @ApiOperation("Add Card")
    @RequestMapping(value = "/card/add", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addCard (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @ApiParam(name = "brand", required = true, value = "brand", defaultValue = "") @RequestParam("brand") String brand,
	        @ApiParam(name = "bin", required = true, value = "bin", defaultValue = "") @RequestParam("bin") String bin,
	        @ApiParam(name = "lastFour", required = true, value = "lastFour", defaultValue = "") @RequestParam("lastFour") String lastFour,
	        @ApiParam(name = "expMonth", required = true, value = "expMonth", defaultValue = "") @RequestParam("expMonth") String expMonth,
	        @ApiParam(name = "expYear", required = true, value = "expYear", defaultValue = "") @RequestParam("expYear") String expYear,
	        @ApiParam(name = "authCode", required = true, value = "authCode", defaultValue = "") @RequestParam("authCode") String authCode,
	        @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       paymentsController.addCard(brand, bin, lastFour, expMonth, expYear, authCode, sessionDetail);
                       
		return response;
	}
        
    @ApiOperation("Get Cards")
    @RequestMapping(value = "/cards", method = RequestMethod.GET, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public CardList getCard (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                 @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    CardList response = 
                       paymentsController.getCards(sessionDetail);
                       
		return response;
	}
        
    @ApiOperation("Remove Card")
    @RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE, 
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addCard (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("id") String cardId,
                 @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       paymentsController.removeCard(Long.valueOf(cardId), sessionDetail);
                    
		return response;
     }
        
     @ApiOperation("Charge Card")
     @RequestMapping(value = "/card/{id}", method = RequestMethod.POST, 
                   consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                   produces = {MediaType.APPLICATION_JSON_VALUE})
	public ServiceResponse addCard (
                @RequestHeader(name = "sessionid", required = true) final String sessionId,
                @PathVariable("id") String cardId,
                @ApiParam(name = "amount", required = true, value = "amount", defaultValue = "") @RequestParam("amount") int amount,
	        @ApiIgnore @RequestAttribute(name = "session_detail") SessionDetail sessionDetail){
                    ServiceResponse response = 
                       paymentsController.chargeCard(amount,Long.valueOf(cardId));
                    
		return response;
     }
}
