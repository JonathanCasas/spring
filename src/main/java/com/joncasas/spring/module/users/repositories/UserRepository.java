/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.users.repositories;

import com.joncasas.spring.module.users.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author jonathan
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByUsername(String username);

}
