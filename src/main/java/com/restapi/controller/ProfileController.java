package com.restapi.controller;

import com.restapi.dto.UserDto;
import com.restapi.model.*;
import com.restapi.request.ProfileRequest;
import com.restapi.response.DpResponse;
import com.restapi.response.UserResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.FileDownloadingService;
import com.restapi.service.ProfileService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FileDownloadingService imageService;

    @Autowired
    private StorageService storageService;


    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUser(@PathVariable Long id){
        AppUser user=profileService.findUser(id);
        UserResponse userResponse=userDto.mapToUserResponse(user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateUser(
//            @RequestParam MultipartFile image,
//            @RequestParam long id,
//            @RequestParam String name,
//            @RequestParam String username,
//            @RequestParam String email,
//            @RequestParam long phone,
//            @RequestParam String gender,
//            @RequestParam String address,
//            @RequestParam String password,
//            @RequestParam String confirmPassword
            @RequestBody ProfileRequest profileRequest
    ){
//        String file= storageService.storeFile(image);
//        AppUser user=new AppUser();
//        user.setId(id);
//        user.setName(name);
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPhone(phone);
//        user.setGender(gender);
//        user.setAddress(address);
//        user.setPassword(password);
//        user.setConfirmPassword(confirmPassword);
//        user.setImage(file);
        AppUser user=profileService.updateUser(profileRequest);
        UserResponse userResponse=userDto.mapToUserResponse(user);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/dp")
    public ResponseEntity<APIResponse> updateDp(@RequestParam("image") MultipartFile image, @RequestParam("id") long id){
        UserDP dp=new UserDP();
        String file=storageService.storeFile(image);
        AppUser user=profileService.findUser(id);
        dp.setDp(file);
        dp.setUser(user);
        profileService.addDp(dp);
        DpResponse dpResponse=userDto.mapToDpResponse(dp);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(dpResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/DP/{id}")
    public ResponseEntity<APIResponse> getUserDP(@PathVariable Long id){
        UserDP userDp=profileService.findDp(id);
        DpResponse userResponse=userDto.mapToDpResponse(userDp);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
