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
public class HealthTips extends ServiceResponse{
    
    int noOfTotalPages;
    int sizeOfCurrentList;
    List<Tip> tips;
    
     public HealthTips(int code) {
        super(code);
     }

    public int getNoOfTotalPages() {
        return noOfTotalPages;
    }

    public void setNoOfTotalPages(int noOfTotalPages) {
        this.noOfTotalPages = noOfTotalPages;
    }

    public int getSizeOfCurrentList() {
        return sizeOfCurrentList;
    }

    public void setSizeOfCurrentList(int sizeOfCurrentList) {
        this.sizeOfCurrentList = sizeOfCurrentList;
    }

    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
    
    
}
