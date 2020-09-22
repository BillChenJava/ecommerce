package com.microclouddata.ecommerce.service.impl;

import com.microclouddata.ecommerce.model.User;
import com.microclouddata.ecommerce.repository.UserRepo;
import com.microclouddata.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Override
    public User findByMobile(String mobile)throws Exception {
        return  userRepo.findByMobile(mobile).orElseThrow(()->new Exception("User by mobile Not Found.."));
    }

    @Override
    public User getUserDetailById(long userId) throws Exception {
        return userRepo.findById(userId).orElseThrow(()->new Exception("User by id Not Found.."));
    }
    @Override
    public User signUpUser(HashMap<String, String> signupRequest) throws Exception {

        try {
            if(userRepo.findByMobile(signupRequest.get("mobile")).isPresent()){
                throw new Exception("User is already registered with this Mobile No.");
            }else if(userRepo.findByName(signupRequest.get("name")).isPresent()){
                throw new Exception("User is already registered with this name No.");
            }else if(userRepo.findByName(signupRequest.get("name")).isPresent()){
                throw new Exception("User is already registered with this name No.");
            }
            User user = new User();
            user.setName(signupRequest.get("name"));
            user.setEmail(signupRequest.get("email"));
            user.setMobile(signupRequest.get("mobile"));
            user.setPassword(signupRequest.get("password"));
            userRepo.save(user);
            return user;
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }


}
