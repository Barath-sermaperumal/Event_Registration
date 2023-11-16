package com.restapi.controller;

import com.restapi.dto.UserDto;
import com.restapi.model.AppUser;
import com.restapi.request.ProfileRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.UserResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("EventRegistration/API/User/profile")
@PreAuthorize("hasRole('ROLE_USER')")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private UserDto userDto;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUser(@PathVariable Long id){
        AppUser user=profileService.findUser(id);
        UserResponse userResponse=userDto.mapToUserResponse(user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateUser(@RequestBody RegisterRequest registerRequest, @PathVariable Long id){
        AppUser user=profileService.updateUser(registerRequest,id);
        UserResponse userResponse=userDto.mapToUserResponse(user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
