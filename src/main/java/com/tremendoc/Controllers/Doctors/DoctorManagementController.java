/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.response.CategoriesResponse;
import com.tremendoc.response.CustomerCreationResponse;
import com.tremendoc.response.DoctorCreationResponse;
import com.tremendoc.response.DoctorProfileResponse;
import com.tremendoc.response.StatusResponse;
import com.tremendoc.response.VerifyResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorManagementController {
    public DoctorCreationResponse createDoctor(String firstName, String lastname, String gender,
            String age, String phone, String email, String password, String preferredUsername,
            String dob, String preferredLanguage, String country,  String specialty,
            String homeAddress, String state, String practiceDescription, String currentProfessionalStatus,
            String goals);
    
    public ServiceResponse updateProfile(String firstName, String lastname, String gender,
            String age, String phone, String dob, String preferredLanguage, String country,  String specialty,
            String homeAddress, String state, String practiceDescription, String currentProfessionalStatus,
            String goals, Long doctorId);
    
    public ServiceResponse resetPassword(String email);
     public ServiceResponse completeReset(String email, String token, String password);
    public ServiceResponse setOnlineStatus(String status, Long doctorId);
    public StatusResponse getOnlineStatus(Long doctorId);
    public DoctorProfileResponse getDoctorProfile(Long doctorId);
    public DoctorCreationResponse authenticateDoctor(String email, String passwword);
    public VerifyResponse verifySession(String sessionId);
}
