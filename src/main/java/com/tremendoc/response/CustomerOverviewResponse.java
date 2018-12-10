/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tremendoc.Entity.Customer;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author prolific
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerOverviewResponse extends ServiceResponse  {
    
    public CustomerOverviewResponse(int code) {
        super(code);
    }
    
    private BigInteger customersCount;
    private BigInteger customersLastCount;
    private BigInteger change;
    private List<Customer> recentCustomers;

    public BigInteger getCustomersCount() {
        return customersCount;
    }

    public void setCustomersCount(BigInteger customersCount) {
        this.customersCount = customersCount;
    }

    public BigInteger getCustomersLastCount() {
        return customersLastCount;
    }

    public void setCustomersLastCount(BigInteger customersLastCount) {
        this.customersLastCount = customersLastCount;
    }

    public BigInteger getChange() {
        return change;
    }

    public void setChange(BigInteger change) {
        this.change = change;
    }

    
    public List<Customer> getRecentCustomers() {
        return recentCustomers;
    }

    public void setRecentCustomers(List<Customer> recentCustomers) {
        this.recentCustomers = recentCustomers;
    }
    
}
