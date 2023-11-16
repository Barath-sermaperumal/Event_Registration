package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.repository.UserRepository;
import com.restapi.request.ProfileRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser findUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException(String.valueOf(id)));
    }

    public AppUser updateUser(RegisterRequest registerRequest, Long id) {
        AppUser appUser=new AppUser();
        appUser.setName(registerRequest.getName());
        appUser.setUsername(registerRequest.getUsername());
        appUser.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        appUser.setId(id);
        appUser.setRoles(userRepository.findById(id).get().getRoles());
        appUser.setOrder(userRepository.findById(id).get().getOrder());
        userRepository.save(appUser);
        return findUser(id);
    }
}
