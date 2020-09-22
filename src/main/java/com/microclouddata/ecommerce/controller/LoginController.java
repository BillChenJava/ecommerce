package com.microclouddata.ecommerce.controller;

import com.microclouddata.ecommerce.JWTConfiguration.AuthManager;
import com.microclouddata.ecommerce.JWTConfiguration.CustomUserDetailsService;
import com.microclouddata.ecommerce.JWTConfiguration.JwtTokenProvider;
import com.microclouddata.ecommerce.JWTConfiguration.UserPrincipal;
import com.microclouddata.ecommerce.api.request.LoginRequest;
import com.microclouddata.ecommerce.api.response.ApiResponse;
import com.microclouddata.ecommerce.api.response.JwtAuthenticationResponse;
import com.microclouddata.ecommerce.model.User;
import com.microclouddata.ecommerce.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    UserService userService;
    @Autowired
    AuthManager aMan;
    @Autowired
    JwtTokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("status")
    public ResponseEntity<?> serverStatus() {
        return new ResponseEntity<>(new ApiResponse("Server is running."), HttpStatus.OK);
    }

    @RequestMapping("/login/user")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {
        try {
//            loginRequest 封装在 request文件夹下，用来接收前端传过来的Json
            Authentication authentication =  aMan.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMobile(),loginRequest.getPassword()),loginRequest);
//用户名验证屏蔽掉，用手机验证
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                           loginRequest.getName(),
//                            loginRequest.getPassword()
//                   )
//            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.generateToken(authentication);


            JSONObject obj = this.getUserResponse(token);
            if(obj==null){
                throw new Exception("Error while generating Response");
            }
            return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
//            JwtAuthenticationResponse 封装在response 文件夹下，用Json传数据到前端
//            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } catch (Exception e) {
            logger.info("Error in authenticateUser ", e);
            return ResponseEntity.badRequest().body("Error in authenticateUser ");
        }
    }

    private JSONObject getUserResponse(String token) {
        try {
            User user = userService.getUserDetailById(_getUserId());
            HashMap<String, String> response = new HashMap<String, String>();
            response.put("user_id", "" + user.getId());
            response.put("name", "" + user.getName());
            response.put("email", "" + user.getEmail());
            response.put("mobile", "" + user.getMobile());

            JSONObject obj = new JSONObject();
            obj.put("user_profile_details",response);
            obj.put("token", token);
            return obj;
        } catch (Exception e) {
            logger.info("Error in getUserResponse", e);
        }
        return null;
    }

    private long _getUserId() {
        logger.info("user id vaildating." + SecurityContextHolder.getContext().getAuthentication());
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        logger.info("(LoginController)user id is " + userPrincipal.getId());
        return userPrincipal.getId();
    }
}
