package com.example.blogapplication.service;

import com.example.blogapplication.model.User;
import com.example.blogapplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityImpl implements UserDetailsService {

    final UserRepo userRepo;

    @Autowired
    public UserSecurityImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserEmail(username);
        if (user == null) throw new UsernameNotFoundException("user not found");
        User temp = user;
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + temp.getRole().toUpperCase());
        authorities.add(simpleGrantedAuthority);
        org.springframework.security.core.userdetails.User finalUser = new org.springframework.security.core.userdetails.User(temp.getUserEmail(), temp.getPassword(), authorities);
        return finalUser;
    }
}
