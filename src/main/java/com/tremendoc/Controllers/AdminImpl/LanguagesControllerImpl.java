/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.AdminImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Admin.LanguagesController;
import com.tremendoc.response.Languages;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class LanguagesControllerImpl implements LanguagesController{

    @Override
    public Languages getLanguages() {
        Languages language = new Languages(ServiceResponse.SUCCESS);
        
        List<String> languages = new ArrayList<>();
        languages.add("english");
        languages.add("french");
        languages.add("yoruba");
        languages.add("ibo");
        languages.add("hausa");
        languages.add("portuguese");
        languages.add("spanish");
        
        language.setLanguages(languages);
          
        return language;
    }
    
}
