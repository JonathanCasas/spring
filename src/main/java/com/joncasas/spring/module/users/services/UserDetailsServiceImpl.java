/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joncasas.spring.module.users.services;

import com.joncasas.spring.module.users.entities.Role;
import com.joncasas.spring.module.users.entities.User;
import com.joncasas.spring.module.users.repositories.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author jonathan
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        try {
            if (user.getRoles().size() > 0) {
                for (Role role : user.getRoles()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
                }
            }
        } catch (Exception e) {
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
