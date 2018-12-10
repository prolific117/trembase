/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.CustomersImpl;

import com.tremendoc.Controllers.Customers.CustomerOverviewController;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CustomerOverviewResponse;
import com.tremendoc.security.impl.SessionDetailProvider;
import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author prolific
 */
@Service
public class CustomerOverviewControllerImpl implements CustomerOverviewController {
    
    @Autowired 
    private CustomerRepository customerRepository;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RequestUtils.class.getName());

     @Override
    public Iterable<Customer> getAll(SessionDetail sessionDetail) {
        logger.log(Level.INFO,"Session ID from session Detail: " +sessionDetail.getSessionId());
        return customerRepository.findAll();
    }


    @Override
    public CustomerOverviewResponse getOverview(SessionDetail sessionDetail) {
         CustomerOverviewResponse response = new CustomerOverviewResponse(ServiceResponse.ERROR);
         
         BigInteger e = null;
        
         //get beginning of week
         Calendar cal = Calendar.getInstance();
         cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
         Date firstDayOfTheWeek = cal.getTime();

         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
         try{
             //get customer count first
            BigInteger currentCount = customerRepository.countCurrentCustomers();
            BigInteger lastWeekCount = customerRepository.countLastWeekCustomers(firstDayOfTheWeek);

            List<Customer> customers = customerRepository.getLastFour();
            
            BigInteger difference = currentCount.subtract(lastWeekCount);
            BigInteger increase = BigInteger.ZERO;
            
            if(!difference.equals(BigInteger.ZERO)){
                increase = (difference.multiply(BigInteger.valueOf(100))).divide(lastWeekCount);
            }
            
            response.setCustomersCount(currentCount);
            response.setCustomersLastCount(lastWeekCount);
            response.setRecentCustomers(customers);
            response.setChange(increase);
            
            response.setCode(ServiceResponse.SUCCESS);
         }
         catch(Exception ex){
             response.setDescription(ServiceResponse.GENERAL_ERROR_MESSAGE);
             logger.log(Level.SEVERE, "Exception found in controller get overview", ex);
         }
        
         return response;
    }
    
}
