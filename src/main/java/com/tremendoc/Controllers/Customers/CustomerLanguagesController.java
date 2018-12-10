/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.LanguagesResponse;

/**
 *
 * @author prolific
 */
public interface CustomerLanguagesController {
    public ServiceResponse setCustomerLanguages(boolean english, boolean yoruba, 
            boolean ibo, boolean hausa, boolean french,
            boolean pidgin, boolean spanish, boolean portuguese, SessionDetail sessionDetail);
    public LanguagesResponse getCustomerLanguages(SessionDetail sessionDetail);
}
