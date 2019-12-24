package com.joncasas.spring.module.customers.controllers;

import com.joncasas.spring.module.customers.entities.Customer;
import com.joncasas.spring.module.customers.forms.UploadFile;
import com.joncasas.spring.module.customers.repositories.AddressRepositoryInterface;
import com.joncasas.spring.module.customers.repositories.CustomerRepositoryInterface;
import com.joncasas.spring.module.customers.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping({"/api/customers"})
public class CustomerController {

    private CustomerRepositoryInterface customerRepository;
    private AddressRepositoryInterface addressRepositoryInterface;
    private UploadFileService uploadFileService;

    public CustomerController(CustomerRepositoryInterface customerRepository, AddressRepositoryInterface addressRepositoryInterface, UploadFileService uploadFileService) {
        this.customerRepository = customerRepository;
        this.addressRepositoryInterface = addressRepositoryInterface;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping({"/", ""})
    public Page<Customer> index(
            @RequestParam(name = "page", defaultValue = "1") String page,
            @RequestParam(name = "size", defaultValue = "20") String size,
            @RequestParam(name = "sort", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        int currentPage = Math.max(Integer.parseInt(page), 0);
        Sort sort = direction.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable paging = PageRequest.of(currentPage, Integer.parseInt(size), sort);
        return customerRepository.findAll(paging);
    }

    @PostMapping({"/", ""})
    @Transactional
    public Customer create(@RequestBody Customer customer) throws Exception {
        customer = customerRepository.save(customer);
        if (customer.getAddressList() != null && !customer.getAddressList().isEmpty())
            addressRepositoryInterface.saveAll(customer.getAddressList());
        return customer;
    }

    @PostMapping({"upload", "/upload", "/upload/"})
    public ResponseEntity<?> upload(@ModelAttribute UploadFile uploadFile) throws IOException {
        System.out.println("upload");
        uploadFileService.upload(uploadFile.getFile());
        System.out.println("uploaded");
        return ResponseEntity.ok("success");
    }
}
