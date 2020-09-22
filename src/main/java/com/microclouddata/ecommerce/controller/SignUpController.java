package com.microclouddata.ecommerce.controller;

import com.microclouddata.ecommerce.api.request.LoginRequest;
import com.microclouddata.ecommerce.api.response.ApiResponse;
import com.microclouddata.ecommerce.model.User;
import com.microclouddata.ecommerce.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {
    @Autowired
    UserService userService;
    @PostMapping("/user")
    public ResponseEntity<?> userSignup(@RequestBody HashMap<String,String> signupRequest){
        try {
            User user = userService.signUpUser(signupRequest);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage()));
        }
    }


}
