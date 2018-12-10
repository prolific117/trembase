/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Doctors;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CalendarResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface DoctorCalendarController {
    public ServiceResponse createDoctorCalendar(String commaSeparatedDays,
            String commaSeparatedTime, SessionDetail sessionDetail);
    
    public CalendarResponse getDoctorCalendar(SessionDetail sessionDetail);
}
