/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.AdminImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.FAQController;
import com.tremendoc.Entity.FAQ;
import com.tremendoc.Entity.HealthTip;
import com.tremendoc.Entity.Repository.FAQRepository;
import com.tremendoc.Entity.Repository.HealthtipRepository;
import com.tremendoc.response.FAQData;
import com.tremendoc.response.FAQResponse;
import com.tremendoc.response.HealthTips;
import com.tremendoc.response.Tip;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class FAQControllerImpl implements FAQController{
    
    @Autowired
    FAQRepository faqRepository;
    
    @Override
    public ServiceResponse createorEditFAQ(Long id, String question, String answer) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.SUCCESS);
        
        FAQ faq = null;
        
        if(id == null || Objects.equals(id, Long.valueOf("0"))){
            faq = new FAQ();
        }
        else{
            faq = faqRepository.getById(id);
        }
        
        if(faq == null){
            response.setCode(ServiceResponse.ERROR);
            response.setDescription("Cannot find existing faq");
            return response;
        }
        
        faq.setAnswer(answer);
        faq.setQuestion(question);
        
        
        faqRepository.save(faq);
        
        return response;
    }

    @Override
    public FAQResponse getFAQ() {
       FAQResponse response = new FAQResponse(ServiceResponse.SUCCESS);
        
       List<FAQ> faqs = faqRepository.getFaq();
       
       List<FAQData> questions = new ArrayList<>();
        
       for(FAQ faq : faqs){
           FAQData  newFaq = new FAQData();
           
           newFaq.setAnswer(faq.getAnswer());
           newFaq.setQuestion(faq.getQuestion());
           
           questions.add(newFaq);
       }
       
       response.setSizeOfCurrentList(questions.size());
       response.setFaq(questions);
       
       if(faqRepository.countActive() != BigInteger.valueOf(0)){
         response.setNoOfTotalPages((faqRepository.countActive().divide(BigInteger.valueOf(20))).intValue() +1 );
        }
       else{
          response.setNoOfTotalPages(0);
       }
        
       return response; 
    }

    @Override
    public ServiceResponse removeFAQ(Long id) {
       ServiceResponse response = new ServiceResponse(ServiceResponse.SUCCESS);
        
        FAQ faq = faqRepository.getById(id);
        
        
        if(faq == null){
            response.setCode(ServiceResponse.ERROR);
            response.setDescription("Cannot find faq");
            return response;
        }
        
        faq.setIsActive(Boolean.FALSE);
        
        faqRepository.save(faq);
        
        return response; }
    
}
