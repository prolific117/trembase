/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Admin;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.util.HashMap;

/**
 *
 * @author olatunji.oduro
 */
public interface Mailer {
 
    public String prepareMail(String file, HashMap<String, String> hmap);
    public void sendMail(String to, String subject, String content);
}
