/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author olatunji.oduro
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarResponse extends ServiceResponse {
    
     public CalendarResponse(int code) {
        super(code);
    }
    
    List<DayPlan> calendar = new ArrayList<DayPlan>();

    public List<DayPlan> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<DayPlan> calendar) {
        this.calendar = calendar;
    }
     
     
}
