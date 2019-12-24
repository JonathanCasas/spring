/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.joncasas.spring;

import com.joncasas.spring.module.customers.interceptors.CustomWebRequestInterceptor;
import com.joncasas.spring.module.customers.interceptors.RequestProcessingTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author jonathan
 */
@Configuration
public class CustomWebMvcConfigurerAdapter implements WebMvcConfigurer {

    RequestProcessingTimeInterceptor requestProcessingTimeInterceptor;
    CustomWebRequestInterceptor customWebRequestInterceptor;

    public CustomWebMvcConfigurerAdapter(RequestProcessingTimeInterceptor requestProcessingTimeInterceptor, CustomWebRequestInterceptor customWebRequestInterceptor) {
        this.requestProcessingTimeInterceptor = requestProcessingTimeInterceptor;
        this.customWebRequestInterceptor = customWebRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        WebMvcConfigurer.super.addInterceptors(registry); //To change body of generated methods, choose Tools | Templates.
        registry.addInterceptor(requestProcessingTimeInterceptor);
        registry.addWebRequestInterceptor(customWebRequestInterceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry); //To change body of generated methods, choose Tools | Templates.
    }

}
