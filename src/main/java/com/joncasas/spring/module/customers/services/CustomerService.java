package com.joncasas.spring.module.customers.services;

import com.joncasas.spring.module.customers.entities.Customer;
import com.joncasas.spring.module.customers.repositories.CustomerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepositoryInterface customerRepositoryInterface;

    public CustomerService() {
    }

    public List<Customer> customers() {
        return (List<Customer>) customerRepositoryInterface.findAll();
    }
}
