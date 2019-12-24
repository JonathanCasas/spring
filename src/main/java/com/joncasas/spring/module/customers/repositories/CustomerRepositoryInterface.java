package com.joncasas.spring.module.customers.repositories;

import com.joncasas.spring.module.customers.entities.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepositoryInterface extends PagingAndSortingRepository<Customer, Integer> {


}
