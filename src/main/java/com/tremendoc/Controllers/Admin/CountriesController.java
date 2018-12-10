/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Admin;

import com.tremendoc.Entity.Countries;
import com.tremendoc.response.CountriesResponse;
import com.tremendoc.response.FAQResponse;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
public interface CountriesController {
    public CountriesResponse getCountries();
}
