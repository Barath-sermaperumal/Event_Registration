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
        return userResponse;
    }
}
