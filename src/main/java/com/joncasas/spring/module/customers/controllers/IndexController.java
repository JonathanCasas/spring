/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.customers.controllers;

import com.joncasas.spring.module.customers.entities.Address;
import com.joncasas.spring.module.customers.entities.Customer;
import com.joncasas.spring.module.customers.events.CustomersIndexEvent;
import com.joncasas.spring.module.customers.forms.CreateForm;
import com.joncasas.spring.module.customers.forms.UploadFile;
import com.joncasas.spring.module.customers.repositories.CustomersRepository;
import com.joncasas.spring.module.customers.repositories.NewRepository;

import java.io.IOException;
import java.util.List;

import com.joncasas.spring.module.customers.services.UploadFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * @author jonathan
 */
@Controller
@RequestMapping("customers")
public class IndexController {

    @Autowired
    ApplicationEventPublisher ApplicationEventPublisher;
    @Autowired
    NewRepository newRepository;

    @Autowired
    CustomersRepository customersRepository;
    @Autowired
    ModelMapper mapper;

    @Autowired
    UploadFileService uploadFileService;


    @GetMapping({"/", ""})
    public ModelAndView index(Model model) {
        CustomersIndexEvent event = new CustomersIndexEvent(this, "hola");
        ApplicationEventPublisher.publishEvent(event);
        model.addAttribute("message", event.getMessage());
        model.addAttribute("customers", (List<Customer>) customersRepository.findAll());
        Pageable paging = PageRequest.of(10, 20, Sort.by("id").ascending());
        Page<Address> addresses = newRepository.findAll(paging);
        System.out.println(addresses.getTotalElements());
        return new ModelAndView("customers/index");
    }

    @GetMapping({"create", "/create", "/create/"})
    public ModelAndView create(Model model) {
        model.addAttribute("customerCreate", new CreateForm());
        return new ModelAndView("customers/create");
    }

    @PostMapping({"create", "/create", "/create/"})
    public ModelAndView store(@ModelAttribute("customerCreate") CreateForm customerCreate) {
        Customer customer = mapper.map(customerCreate, Customer.class);
        try {
            customersRepository.save(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ModelAndView("customers/create");
    }

    @GetMapping({"{customer}", "/{customer}", "/{customer}/"})
    public ModelAndView uploadDocument(@PathVariable("customer") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        model.addAttribute("uploadFile", new UploadFile());
        return new ModelAndView("customers/show");
    }

    @PostMapping({"{customer}", "/{customer}", "/{customer}/"})
    public RedirectView upload(@Valid @ModelAttribute("uploadFile") UploadFile uploadFile, @PathVariable("customer") Customer customer) throws IOException {
        uploadFileService.upload(uploadFile.getFile(), customer.getDocument());
        return new RedirectView("/customers");
    }

}
