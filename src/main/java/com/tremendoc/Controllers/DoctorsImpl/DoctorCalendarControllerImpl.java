/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Doctors.DoctorCalendarController;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorAppointments;
import com.tremendoc.Entity.Repository.DoctorAppointmentsRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.CalendarResponse;
import com.tremendoc.response.DayPlan;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class DoctorCalendarControllerImpl implements DoctorCalendarController{
    
    @Autowired
    DoctorAppointmentsRepository repository;
    
    @Autowired  
    private DoctorRepository doctorRepository;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RequestUtils.class.getName());
  
    @Override
    public ServiceResponse createDoctorCalendar( String commaSeparatedDays,
            String commaSeparatedTime, SessionDetail sessionDetail) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        //convert commaSeparatedDays to array
        commaSeparatedDays = commaSeparatedDays.toLowerCase();
        List<String> list = new ArrayList<String>(Arrays.asList(commaSeparatedDays.split(",")));
        List<String> time = new ArrayList<String>(Arrays.asList(commaSeparatedTime.split(",")));
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Doctor doctor = doctorRepository.findByDoctorId(user.getId());
        
        if(doctor == null){
            response.setDescription("Doctor does not exist, Cannot set up Calendar");
            return response;
        }
        
        DoctorAppointments doctorApp = repository.findByDoctor(doctor);
        
        if(doctorApp == null){
            doctorApp = new DoctorAppointments();
        }
        
        doctorApp.setMonday(false);
        doctorApp.setTuesday(false);
        doctorApp.setWednesday(false);
        doctorApp.setThursday(false);
        doctorApp.setFriday(false);
        
        try{
           for(int i = 0; i < list.size(); i++){
            String day = list.get(i);
            String timeRange = time.get(i);
            
            List<String> range = new ArrayList<String>(Arrays.asList(timeRange.split("-")));
            int startTime = Integer.parseInt(range.get(0));
            int endTime = Integer.parseInt(range.get(1));
            
            if("monday".equals(day)){
                doctorApp.setMonday(true);
                doctorApp.setMondayStart(startTime);
                doctorApp.setMondayEnd(endTime);
            }
            
            if("tuesday".equals(day)){
                doctorApp.setTuesday(true);
                doctorApp.setTuesdayStart(startTime);
                doctorApp.setTuesdayEnd(endTime);
            }
            
            if("wednesday".equals(day)){
                doctorApp.setWednesday(true);
                doctorApp.setWednesdayStart(startTime);
                doctorApp.setWednesdayEnd(endTime);
            }
            
            if("thursday".equals(day)){
                doctorApp.setThursday(true);
                doctorApp.setThursdayStart(startTime);
                doctorApp.setThursdayEnd(endTime);
            }
            
            if("friday".equals(day)){
                doctorApp.setMonday(true);
                doctorApp.setFridayStart(startTime);
                doctorApp.setFridayEnd(endTime);
            }
            
            if("saturday".equals(day)){
                doctorApp.setSaturday(true);
                doctorApp.setSaturdayStart(startTime);
                doctorApp.setSaturdayEnd(endTime);
            }
            
            if("sunday".equals(day)){
                doctorApp.setSunday(true);
                doctorApp.setSundayStart(startTime);
                doctorApp.setSundayEnd(endTime);
            }
          }
        }
        catch(Exception ex){
             logger.log(Level.INFO, commaSeparatedDays);
             logger.log(Level.INFO, commaSeparatedTime);
             logger.log(Level.SEVERE, ex.getMessage());
             response.setDescription("An error occured, please check content");
        }
       
        doctorApp.setDoctor(doctor);
        repository.save(doctorApp);
        response.setCode(ServiceResponse.SUCCESS);
        return response;
    }

    @Override
    public CalendarResponse getDoctorCalendar(SessionDetail sessionDetail) {
         CalendarResponse response = new CalendarResponse(ServiceResponse.ERROR);
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Doctor doctor = doctorRepository.findByDoctorId(user.getId());
        
        if(doctor == null){
            response.setDescription("Doctor does not exist, Cannot retrieve Calendar");
            return response;
        }
        
        DoctorAppointments doctorApp = repository.findByDoctor(doctor);
        
        if(doctorApp == null){
           response.setCode(ServiceResponse.SUCCESS);
           response.setDescription("Calendar has not been set");
           return response;
        }
        
         List<DayPlan> calendar = new ArrayList<DayPlan>();
         
         String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
         
         for(int i = 0; i < days.length; i++){
             calendar.add(doctorApp.getDay(days[i]));
         }
         
         response.setCode(ServiceResponse.SUCCESS);
         response.setCalendar(calendar);
         
         return response;
      }
    
}
