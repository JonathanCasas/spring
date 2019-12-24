/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.customers.ws;

import com.joncasas.spring.module.customers.repositories.CustomerRepositoryInterface;
import java.util.Optional;
import localhost._8080.GetCustomerRequest;
import localhost._8080.Customer;
import localhost._8080.GetCustomerResponse;
import org.modelmapper.ModelMapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author jonathan
 */
@Endpoint
public class CustomerEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/";

    private CustomerRepositoryInterface customerRepository;
    ModelMapper mapper;

    public CustomerEndpoint(CustomerRepositoryInterface customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
    @ResponsePayload
    public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
        GetCustomerResponse response = new GetCustomerResponse();
        Customer customer = new Customer();
        Optional<com.joncasas.spring.module.customers.entities.Customer> customerEntity = customerRepository.findById(request.getId());
        if (customerEntity.isPresent()) {
            customer = this.mapper.map(customerEntity.get(), Customer.class);
        }
        response.setCustomer(customer);
        return response;
    }

}
