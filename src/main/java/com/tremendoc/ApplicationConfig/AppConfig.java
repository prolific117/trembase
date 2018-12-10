/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tremendoc.ApplicationConfig;

import com.tremendoc.Filters.WebFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author prolific
 */
@Configuration
public class AppConfig {

 @Bean
 public FilterRegistrationBean < WebFilter > filterRegistrationBean() {
  FilterRegistrationBean < WebFilter > registrationBean = new FilterRegistrationBean();
  WebFilter customURLFilter = new WebFilter();

  registrationBean.setFilter(customURLFilter);
  registrationBean.addUrlPatterns("/customers/*");
  registrationBean.setOrder(1); //set precedence
  return registrationBean;
 }
}
