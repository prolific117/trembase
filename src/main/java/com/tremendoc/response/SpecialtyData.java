/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

/**
 *
 * @author olatunji.oduro
 */
public class SpecialtyData {
    Long id;
    String specialtyName;
    String specialtyCode;
    int availableDiscount;
    String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public int getAvailableDiscount() {
        return availableDiscount;
    }

    public void setAvailableDiscount(int availableDiscount) {
        this.availableDiscount = availableDiscount;
    }
    
    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }
    
    
}
