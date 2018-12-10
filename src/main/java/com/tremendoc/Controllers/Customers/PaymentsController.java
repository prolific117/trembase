/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Customers;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CardList;

/**
 *
 * @author olatunji.oduro
 */
public interface PaymentsController {
    public ServiceResponse doRefund(int amount, String transRef, String currency);
    public ServiceResponse chargeCard(int amount, Long cardId);
    public ServiceResponse addCard(String brand, String bin, String lastFour, String expMonth, String expYear, String authCode, SessionDetail detail);
    public ServiceResponse removeCard(Long id, SessionDetail detail);
    public CardList getCards(SessionDetail sessionDetail);
}
