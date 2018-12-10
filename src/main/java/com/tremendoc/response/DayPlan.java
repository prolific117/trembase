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
public class DayPlan {
    
    String day;
    int periodStarts;
    int periodEnds;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPeriodStarts() {
        return periodStarts;
    }

    public void setPeriodStarts(int periodStarts) {
        this.periodStarts = periodStarts;
    }

    public int getPeriodEnds() {
        return periodEnds;
    }

    public void setPeriodEnds(int periodEnds) {
        this.periodEnds = periodEnds;
    }
    
    
}
