/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Entity;

import com.proxy.leanstack.commons.repository.AbstractRepositoryModel;
import com.tremendoc.response.DayPlan;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author olatunji.oduro
 */
@Entity
@Table(name = "doctor_appointments")
@NamedQueries({
 }     
)
public class DoctorAppointments extends AbstractRepositoryModel implements Serializable {
    
    @Column(name="monday")
    private boolean monday;
    
    @Column(name="mondayStart")
    private int mondayStart;
    
    @Column(name="mondayEnd")
    private int mondayEnd;
    
    @Column(name="tuesday")
    private boolean tuesday;
    
    @Column(name="tuesdayStart")
    private int tuesdayStart;
    
    @Column(name="tuesdayEnd")
    private int tuesdayEnd;
    
    @Column(name="wednesday")
    private boolean wednesday;
    
    @Column(name="wednesdayStart")
    private int wednesdayStart;

    public int getMondayStart() {
        return mondayStart;
    }
    
    public DayPlan getDay(String day){
        DayPlan dayplan = new DayPlan();
        
        if("monday".equals(day)){
            dayplan.setDay("monday");
            dayplan.setPeriodStarts(getMondayStart());
            dayplan.setPeriodEnds(getMondayEnd());
        }
        
        if("tuesday".equals(day)){
            dayplan.setDay("tuesday");
            dayplan.setPeriodStarts(getTuesdayStart());
            dayplan.setPeriodEnds(getTuesdayEnd());
        }
        
        if("wednesday".equals(day)){
            dayplan.setDay("wednesday");
            dayplan.setPeriodStarts(getWednesdayStart());
            dayplan.setPeriodEnds(getWednesdayEnd());
        }
        
        if("monday".equals(day)){
            dayplan.setDay("monday");
            dayplan.setPeriodStarts(getMondayStart());
            dayplan.setPeriodEnds(getMondayEnd());
        }
        
        if("thursday".equals(day)){
            dayplan.setDay("thursday");
            dayplan.setPeriodStarts(getThursdayStart());
            dayplan.setPeriodEnds(getThursdayEnd());
        }
        
        if("friday".equals(day)){
            dayplan.setDay("friday");
            dayplan.setPeriodStarts(getFridayStart());
            dayplan.setPeriodEnds(getFridayEnd());
        }
        
        if("saturday".equals(day)){
            dayplan.setDay("saturday");
            dayplan.setPeriodStarts(getSaturdayStart());
            dayplan.setPeriodEnds(getSaturdayEnd());
        }
        
        if("sunday".equals(day)){
            dayplan.setDay("sunday");
            dayplan.setPeriodStarts(getSundayStart());
            dayplan.setPeriodEnds(getSundayEnd());
        }
        
        return dayplan;
    }

    public void setMondayStart(int mondayStart) {
        this.mondayStart = mondayStart;
    }

    public int getMondayEnd() {
        return mondayEnd;
    }

    public void setMondayEnd(int mondayEnd) {
        this.mondayEnd = mondayEnd;
    }

    public int getTuesdayStart() {
        return tuesdayStart;
    }

    public void setTuesdayStart(int tuesdayStart) {
        this.tuesdayStart = tuesdayStart;
    }

    public int getTuesdayEnd() {
        return tuesdayEnd;
    }

    public void setTuesdayEnd(int tuesdayEnd) {
        this.tuesdayEnd = tuesdayEnd;
    }

    public int getWednesdayStart() {
        return wednesdayStart;
    }

    public void setWednesdayStart(int wednesdayStart) {
        this.wednesdayStart = wednesdayStart;
    }

    public int getWednesdayEnd() {
        return wednesdayEnd;
    }

    public void setWednesdayEnd(int wednesdayEnd) {
        this.wednesdayEnd = wednesdayEnd;
    }

    public int getThursdayStart() {
        return thursdayStart;
    }

    public void setThursdayStart(int thursdayStart) {
        this.thursdayStart = thursdayStart;
    }

    public int getThursdayEnd() {
        return thursdayEnd;
    }

    public void setThursdayEnd(int thursdayEnd) {
        this.thursdayEnd = thursdayEnd;
    }

    public int getFridayStart() {
        return fridayStart;
    }

    public void setFridayStart(int fridayStart) {
        this.fridayStart = fridayStart;
    }

    public int getFridayEnd() {
        return fridayEnd;
    }

    public void setFridayEnd(int fridayEnd) {
        this.fridayEnd = fridayEnd;
    }

    public int getSaturdayStart() {
        return saturdayStart;
    }

    public void setSaturdayStart(int saturdayStart) {
        this.saturdayStart = saturdayStart;
    }

    public int getSaturdayEnd() {
        return saturdayEnd;
    }

    public void setSaturdayEnd(int saturdayEnd) {
        this.saturdayEnd = saturdayEnd;
    }

    public int getSundayStart() {
        return sundayStart;
    }

    public void setSundayStart(int sundayStart) {
        this.sundayStart = sundayStart;
    }

    public int getSundayEnd() {
        return sundayEnd;
    }

    public void setSundayEnd(int sundayEnd) {
        this.sundayEnd = sundayEnd;
    }
    
    @Column(name="wednesdayEnd")
    private int wednesdayEnd;
    
    @Column(name="thursday")
    private boolean thursday;
    
    @Column(name="thursdayStart")
    private int thursdayStart;
    
    @Column(name="thursdayEnd")
    private int thursdayEnd;
    
    @Column(name="friday")
    private boolean friday;
    
    @Column(name="fridayStart")
    private int fridayStart;
    
    @Column(name="fridayEnd")
    private int fridayEnd;
    
    @Column(name="saturday")
    private boolean saturday;
    
    @Column(name="saturdayStart")
    private int saturdayStart;
    
    @Column(name="saturdayEnd")
    private int saturdayEnd;
    
    @Column(name="sunday")
    private boolean sunday;
    
    @Column(name="sundayStart")
    private int sundayStart;
    
    @Column(name="sundayEnd")
    private int sundayEnd;
    
    @Column(name="timeRangeStart")
    private String timeRangeStart;   
  
    @Column(name="timeRangeEnd")
    private String timeRangeEnd;
    
    @OneToOne
    @JoinColumn (name = "doctor", referencedColumnName = "id")
    private Doctor doctor;
  
    
   
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public String getTimeRangeStart() {
        return timeRangeStart;
    }

    public void setTimeRangeStart(String timeRangeStart) {
        this.timeRangeStart = timeRangeStart;
    }

    public String getTimeRangeEnd() {
        return timeRangeEnd;
    }

    public void setTimeRangeEnd(String timeRangeEnd) {
        this.timeRangeEnd = timeRangeEnd;
    }

}
