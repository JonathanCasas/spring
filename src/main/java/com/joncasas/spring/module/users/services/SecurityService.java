/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.users.services;

/**
 *
 * @author jonathan
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
