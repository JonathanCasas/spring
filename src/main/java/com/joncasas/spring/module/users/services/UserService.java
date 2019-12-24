/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.users.services;

import com.joncasas.spring.module.users.entities.User;

/**
 *
 * @author jonathan
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
