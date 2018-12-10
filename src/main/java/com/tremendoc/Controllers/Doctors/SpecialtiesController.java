/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CategoriesResponse;
import com.tremendoc.response.SpecialtiesResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface SpecialtiesController {
  public ServiceResponse addCategory(String category, String imagePath, String categoryCode, SessionDetail sessionDetail); 
  public ServiceResponse addSpecialty(String specialty, String imagePath, int discount, Long categoryId, SessionDetail sessionDetail); 
  public CategoriesResponse getCategories();
  public SpecialtiesResponse getSpecialties(Long categoryId);
}
