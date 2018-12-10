/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
public class Languages extends ServiceResponse{

    public Languages(int code) {
        super(code);
    }
 
    List<String> languages;

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
