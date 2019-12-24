/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.customers.listeners;

import com.joncasas.spring.module.customers.events.CustomersIndexEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author jonathan
 */
@Component
public class CustomerIndexListener implements ApplicationListener<CustomersIndexEvent> {

    @Override
    public void onApplicationEvent(CustomersIndexEvent e) {
        e.concatenateMessage("Jonathan");
        System.out.println(e.getMessage());
    }

}
