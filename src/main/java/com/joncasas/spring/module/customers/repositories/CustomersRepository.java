/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.customers.repositories;

import com.joncasas.spring.module.customers.entities.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jonathan
 */
public interface CustomersRepository extends CrudRepository<Customer, Integer> {
    
}
