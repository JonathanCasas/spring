package com.joncasas.spring.module.customers.repositories;

import com.joncasas.spring.module.customers.entities.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepositoryInterface extends PagingAndSortingRepository<Address, Integer> {
}
