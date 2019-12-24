/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.users.controllers;

import com.joncasas.spring.module.users.entities.User;
import com.joncasas.spring.module.users.services.SecurityService;
import com.joncasas.spring.module.users.services.UserService;
import com.joncasas.spring.module.users.services.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jonathan
 */
@Controller
@RequestMapping({"users", "/users", "/users/"})
public class UserController {

    private UserService userService;
    private SecurityService securityService;
    private UserValidator userValidator;

    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        model.addAttribute("userForm", new User());
        return new ModelAndView("users/registration");
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("users/registration");
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return new ModelAndView("redirect:/welcome");
    }

    @GetMapping("/login")
    public ModelAndView login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return new ModelAndView("users/login");
    }

}
