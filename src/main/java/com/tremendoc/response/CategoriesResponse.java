/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriesResponse extends ServiceResponse {
    
    public CategoriesResponse(int code) {
        super(code);
     }
    
    List<Categories> categories;

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
