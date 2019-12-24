/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.customers.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author jonathan
 */
public class CustomersIndexEvent extends ApplicationEvent {

    private String message;

    public CustomersIndexEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void concatenateMessage(String message) {
        this.message = this.message.concat(" ").concat(message);
    }


}
