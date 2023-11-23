package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserDto {
    public UserResponse mapToUserResponse(AppUser user) {
        UserResponse userResponse=new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setUsername(user.getUsername());
        userResponse.setGender(user.getGender());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setPassword(user.getPassword());
        userResponse.setPhone(user.getPhone());

        return userResponse;
    }
}
