/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.tremendoc.response.DoctorResponse;
import com.tremendoc.response.DoctorsResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface SearchController {
    public DoctorsResponse searchSpecialty(Long specialtyId, int page);
    public DoctorsResponse searchByName(String name, int page);
    public DoctorResponse searchById(Long id);
    public DoctorsResponse getOnline();
    public DoctorResponse getAvailableDoctor(Long specialtyId);
}
