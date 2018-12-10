/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.CustomersImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.PaymentsController;
import com.tremendoc.Entity.Card;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Repository.CardRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.Requests.Charge;
import com.tremendoc.Requests.RefundRequest;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.paystack.response.ChargeResponse;
import com.tremendoc.paystack.response.RefundResponse;
import com.tremendoc.response.CardData;
import com.tremendoc.response.CardList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class PaymentsControllerImpl implements PaymentsController{
    
    @Value("${spring.paystack_url}")
    private String paystack_url;
    
    @Value("${spring.paystack_key}")
    private String paystack_key;
    
    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    
    private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());

    @Override
    public ServiceResponse doRefund(int amount, String transRef, String currency) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        RefundResponse ref = refund(amount,transRef,currency);
        logger.log(Level.INFO, "Message : "+ref.getMessage());
        logger.log(Level.INFO, "Status "+ref.isStatus());
        
        if(ref.isStatus()){
            response.setCode(ServiceResponse.SUCCESS);
        }
        else{
            response.setDescription(ref.getMessage());
        }
        
        return response;
    }
    
    private RefundResponse refund(int amount, String transRef, String currency){
        
      String url = paystack_url+"refund";
        
      OkHttpClient client = new OkHttpClient();
      ObjectMapper mapper = new ObjectMapper();
      amount = amount * 100;
      
      RefundRequest refundRequest = new RefundRequest();
      refundRequest.setAmount(amount);
      refundRequest.setCurrency(currency);
      refundRequest.setTransaction(transRef);
      
      logger.log(Level.INFO, url);
        //Request request = new Request.Builder().url(url).build();
        try {
            
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(refundRequest);
          
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            
             Request request = new Request.Builder()
              .url(url)
              .get()
              .addHeader("Authorization", "Bearer "+paystack_key)
              .addHeader("Content-Type", "application/json")
              .post(body)
              .build();

            Response response = client.newCall(request).execute();
            RefundResponse resp = mapper.readValue(response.body().string(), RefundResponse.class);
          
            return resp;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Could not refund transaction with transid "+transRef, ex);
            return null;
        }
    }

    @Override
    public ServiceResponse addCard(String brand, String bin, String lastFour, String expMonth, String expYear, String authCode, SessionDetail sessionDetail) {
          ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
          
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());

         if(customer == null){
            response.setDescription("User does not exist, Cannot add card");
            return response;
         }
           
          Card existingCard = cardRepository.findByParams(customer, bin, lastFour);
          
          if(existingCard != null){
              response.setDescription("Card Exists");
              return response;
          }
          
          Card card = new Card();
          card.setAuthCode(authCode);
          card.setBin(bin);
          card.setCardBrand(brand);
          card.setLast4(lastFour);
          card.setCustomer(customer);
          card.setExpMonth(expMonth);
          card.setExpYear(expYear);
          
          cardRepository.save(card);
          
          response.setCode(ServiceResponse.SUCCESS);
          
          return response;
            
    }

    @Override
    public ServiceResponse removeCard(Long id, SessionDetail sessionDetail) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
          
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());

         if(customer == null){
            response.setDescription("User does not exist, Cannot remove card");
            return response;
         }
           
          Card existingCard = cardRepository.getById(id);
          
          if(existingCard == null){
              response.setDescription("Card does not Exist");
              return response;
          }
          
          existingCard.setIsActive(Boolean.FALSE);
          cardRepository.save(existingCard);
          
          response.setCode(ServiceResponse.SUCCESS);
          
          return response; 
    }

    @Override
    public CardList getCards(SessionDetail sessionDetail) {
        CardList response = new CardList(ServiceResponse.SUCCESS);
        
         ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
         Customer customer = customerRepository.getOne(user.getId());

         if(customer == null){
            response.setDescription("User does not exist, Cannot retrieve cards");
            return response;
         }
         
        List<Card> cards = cardRepository.getByCustomer(customer);
        List<CardData> refinedCards = new ArrayList<CardData>();
        
        for(Card card : cards){
            CardData data = new CardData();
            
            data.setId(card.getId());
            data.setAuthCode(card.getAuthCode());
            data.setBin(card.getBin());
            data.setCardBrand(card.getCardBrand());
            data.setExpMonth(card.getExpMonth());
            data.setExpYear(card.getExpYear());
            data.setExpired(card.isExpired());
            data.setLast4(card.getLast4());
            
            refinedCards.add(data);
        }
        
        response.setCards(refinedCards);
        
        return response;
    }

    @Override
    public ServiceResponse chargeCard(int amount, Long cardId) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        ChargeResponse ref = charge(amount, cardId);
        logger.log(Level.INFO, "Message : "+ref.getMessage());
        logger.log(Level.INFO, "Status "+ref.isStatus());
        
        if("success".equals(ref.getData().getStatus())){
            response.setCode(ServiceResponse.SUCCESS);
        }
        else{
            response.setDescription(ref.getMessage());
        }
        
        return response;
    }
    
    private ChargeResponse charge(int amount, Long cardId){
       
     ChargeResponse response = new ChargeResponse();
      String url = paystack_url+"transaction/charge_authorization";
        
      OkHttpClient client = new OkHttpClient();
      ObjectMapper mapper = new ObjectMapper();
      amount = amount * 100;
      
       Card existingCard = cardRepository.getById(cardId);
          
          if(existingCard == null){
              response.setStatus(false);
              response.setMessage("Card does not Exist");
              return response;
        }
      
      Charge chargeRequest = new Charge();
      chargeRequest.setAmount(amount);
      chargeRequest.setEmail(existingCard.getCustomer().getEmail());
      chargeRequest.setAuthorization_code(existingCard.getAuthCode());
      
      logger.log(Level.INFO, url);
        //Request request = new Request.Builder().url(url).build();
        try {
            
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(chargeRequest);
          
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            
             Request request = new Request.Builder()
              .url(url)
              .get()
              .addHeader("Authorization", "Bearer "+paystack_key)
              .addHeader("Content-Type", "application/json")
              .post(body)
              .build();

            Response response_ = client.newCall(request).execute();
            ChargeResponse resp = mapper.readValue(response_.body().string(), ChargeResponse.class);
          
            return resp;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Could not charge card "+cardId, ex);
            response.setMessage("An error occured");
            response.setStatus(false);
            return null;
        }
    }

}
