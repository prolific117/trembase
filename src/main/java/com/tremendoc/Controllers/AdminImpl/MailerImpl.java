/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.AdminImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.tremendoc.Controllers.Admin.Mailer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class MailerImpl implements Mailer{
    
    @Value("${spring.fromEmail}")
    private String fromEmail;
    
    @Value("${spring.sendgridkey}")
    private String sendgridkey;
    
    @Value("${spring.emailFolder}")
    private String emailFolder;
    
    private static final Logger logger = Logger.getLogger(RequestUtils.class.getName());
    
    public static void main(String[] args) throws IOException {
        URL oracle = new URL("https://documentation.mailgun.com/en/latest/api-intro.html#base-url");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        String fulltext = "";
        while ((inputLine = in.readLine()) != null)
            fulltext += inputLine;
        in.close();
        
        System.out.print(fulltext);
    }

    @Override
    public void sendMail(String toEmail, String subject, String contentBody) {
     
       Email from = new Email(fromEmail);
       Email to = new Email(toEmail);
       Content content = new Content("text/html", contentBody);
       Mail mail = new Mail(from, subject, to, content);

       SendGrid sg = new SendGrid(sendgridkey);
       Request request = new Request();
       try {
         request.setMethod(Method.POST);
         request.setEndpoint("mail/send");
         request.setBody(mail.build());
         Response response = sg.api(request);
         System.out.println("Code:"+ response.getStatusCode());
         System.out.println(response.getBody());
         System.out.println(response.getHeaders());
       } catch (IOException ex) {
         logger.log(Level.SEVERE, "Failed to send mail", ex);
       }   
    }

    @Override
    public String prepareMail(String file, HashMap<String, String> hmap) {
        
        URL oracle;
        String fulltext = "";
        
        try {
            oracle = new URL("https://documentation.mailgun.com/en/latest/api-intro.html#base-url");
            BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                fulltext += inputLine;
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(MailerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (IOException ex) {
            Logger.getLogger(MailerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        
        
        
        System.out.print(fulltext);
        
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
           Map.Entry mentry = (Map.Entry)iterator.next();
           fulltext = fulltext.replace((String) mentry.getKey(), (String) mentry.getValue());
            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
           System.out.println(mentry.getValue());
        }
        
        
        return fulltext;
    }
    
}
