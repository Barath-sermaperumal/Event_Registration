package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String name;
    private String username;
    private String gender;
    private String email;
    private String phone;
    private String password;
    private String address;
}
