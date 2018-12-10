/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.AdminImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.HealthTipsController;
import com.tremendoc.Entity.HealthTip;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.HealthtipRepository;
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
public class HealthTipControllerImpl implements HealthTipsController{
    
    @Autowired
    HealthtipRepository healthTipRepository;
    
    
    @Override
    public ServiceResponse createorEditHealthTip(Long id, String body, String summary, String title, String imagePath) {
        
        ServiceResponse response = new ServiceResponse(ServiceResponse.SUCCESS);
        
        HealthTip healthTip = null;
        
        if(id == null || Objects.equals(id, Long.valueOf("0"))){
            healthTip = new HealthTip();
        }
        else{
            healthTip = healthTipRepository.getById(id);
        }
        
        if(healthTip == null){
            response.setCode(ServiceResponse.ERROR);
            response.setDescription("Cannot find existing health tip");
            return response;
        }
        
        healthTip.setBody(body);
        healthTip.setImage(imagePath);
        healthTip.setLikes(0);
        healthTip.setSummary(summary);
        healthTip.setTitle(title);
        
        healthTipRepository.save(healthTip);
        
        return response;
    
    }

    @Override
    public ServiceResponse likeHealthTip(Long id) {
       ServiceResponse response = new ServiceResponse(ServiceResponse.SUCCESS);
        
       HealthTip healthTip = healthTipRepository.getById(id);
        
       if(healthTip == null){
            response.setCode(ServiceResponse.ERROR);
            response.setDescription("Cannot find health tip");
            return response;
        }
        
        healthTip.setLikes(healthTip.getLikes()+1);
        
        healthTipRepository.save(healthTip);
        
        return response;

    }

    @Override
    public HealthTips getHealthTips(int page) {
       HealthTips response = new HealthTips(ServiceResponse.SUCCESS);
        
       List<HealthTip> healthTips = healthTipRepository.getTips((page-1)*20);
       
       List<Tip> tips = new ArrayList<>();
        
       for(HealthTip tip : healthTips){
           Tip newTip = new Tip();
           newTip.setBody(tip.getBody());
           newTip.setImage(tip.getImage());
           newTip.setLikes(tip.getLikes());
           newTip.setSummary(tip.getSummary());
           newTip.setTitle(tip.getTitle());
           
           tips.add(newTip);
       }
       
       response.setSizeOfCurrentList(tips.size());
       response.setTips(tips);
       
       if(healthTipRepository.countActive() != BigInteger.valueOf(0)){
         response.setNoOfTotalPages((healthTipRepository.countActive().divide(BigInteger.valueOf(20))).intValue() +1 );
        }
       else{
          response.setNoOfTotalPages(0);
       }
        
       return response;
    }

    @Override
    public ServiceResponse removeHealthTip(Long id) {
       ServiceResponse response = new ServiceResponse(ServiceResponse.SUCCESS);
        
        HealthTip healthTip = healthTipRepository.getById(id);
        
        
        if(healthTip == null){
            response.setCode(ServiceResponse.ERROR);
            response.setDescription("Cannot find health tip");
            return response;
        }
        
        healthTip.setIsActive(Boolean.FALSE);
        
        healthTipRepository.save(healthTip);
        
        return response;
       }
}
