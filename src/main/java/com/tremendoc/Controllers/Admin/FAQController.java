/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Admin;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.response.FAQResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface FAQController {
    public ServiceResponse createorEditFAQ(Long id, String question, String answer);
    public ServiceResponse removeFAQ(Long id);
    public FAQResponse getFAQ();
}
