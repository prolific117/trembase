/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.tremendoc.Entity.Doctor;
import com.tremendoc.response.RefereeResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface ProfileManager {
    public boolean checkCompletion(Doctor doctor); 
    public boolean checkDocouments(Doctor doctor); 
    public boolean checkReferees(Doctor doctor); 
    public boolean checkAcademicProfile(Doctor doctor); 
}
