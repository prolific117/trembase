/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultationsResponse extends ServiceResponse{

    int noOfTotalPages;
    int sizeOfCurrentList;
    Long totalCount;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public ConsultationsResponse(int code) {
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

    public List<ConsultationsData> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationsData> consultations) {
        this.consultations = consultations;
    }
    
    List<ConsultationsData> consultations;
}
