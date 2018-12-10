/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.subsresponse.LifestyleProfile;
import com.tremendoc.subsresponse.PregnancyProfile;

import java.util.List;

/**
 *
 * @author prolific
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthProfileResponse extends ServiceResponse  {
 
     public HealthProfileResponse(int code) {
        super(code);
     }
    
     List<String> allergiesProfile;
     LifestyleProfile lifestyleProfile;
     List<String> medicationProfile;
     List<String> symptomsProfile;
     List<String> treatmentsProfile;
     PregnancyProfile pregnancyProfile;

    

        public List<String> getAllergiesProfile() {
            return allergiesProfile;
        }

        public void setAllergiesProfile(List<String> allergiesProfile) {
            this.allergiesProfile = allergiesProfile;
        }

        public LifestyleProfile getLifestyleProfile() {
            return lifestyleProfile;
        }

        public void setLifestyleProfile(LifestyleProfile lifestyleProfile) {
            this.lifestyleProfile = lifestyleProfile;
        }

        public List<String> getMedicationProfile() {
            return medicationProfile;
        }

        public void setMedicationProfile(List<String> medicationProfile) {
            this.medicationProfile = medicationProfile;
        }

        public List<String> getSymptomsProfile() {
            return symptomsProfile;
        }

        public void setSymptomsProfile(List<String> symptomsProfile) {
            this.symptomsProfile = symptomsProfile;
        }

        public List<String> getTreatmentsProfile() {
            return treatmentsProfile;
        }

        public void setTreatmentsProfile(List<String> treatmentsProfile) {
            this.treatmentsProfile = treatmentsProfile;
        }

        public PregnancyProfile getPregnancyProfile() {
            return pregnancyProfile;
        }

        public void setPregnancyProfile(PregnancyProfile pregnancyProfile) {
            this.pregnancyProfile = pregnancyProfile;
        }

     
}
