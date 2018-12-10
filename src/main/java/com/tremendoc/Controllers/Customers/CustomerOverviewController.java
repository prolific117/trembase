/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.Controllers.Customers;

import com.tremendoc.Entity.Customer;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.CustomerOverviewResponse;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author prolific
 */
public interface CustomerOverviewController {
        public CustomerOverviewResponse getOverview(SessionDetail sessionDetail);
        public @ResponseBody Iterable<Customer> getAll(SessionDetail sessionDetail);
}
