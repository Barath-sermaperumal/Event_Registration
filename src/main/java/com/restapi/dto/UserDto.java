package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.response.ProfileResponse;
import com.restapi.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDto {

    public UserResponse mapToUserResponse(AppUser user) {
        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setUsername(user.getUsername());
        userResponse.setGender(user.getGender());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setPassword(user.getPassword());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRoles().getName());
        return userResponse;
    }

    public List<ProfileResponse> mapToProfileResponse(List<AppUser> users) {
        List<ProfileResponse> rs=new ArrayList<>();
        for (AppUser user : users) {
            ProfileResponse profileResponse = new ProfileResponse();
            profileResponse.setName(user.getName());
            profileResponse.setId(user.getId());
            profileResponse.setRole(user.getRoles().getName());
            profileResponse.setGender(user.getGender());
            profileResponse.setEmail(user.getEmail());
            profileResponse.setPhone(user.getPhone());
            profileResponse.setUsername(user.getUsername());
            rs.add(profileResponse);
        }
        return rs;
    }
}
