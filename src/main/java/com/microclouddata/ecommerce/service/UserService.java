package com.microclouddata.ecommerce.service;

import com.microclouddata.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface UserService {
   User findByMobile(String mobile) throws Exception;
   User getUserDetailById(long userId) throws Exception;
   User signUpUser(HashMap<String,String> signupRequest) throws Exception;
}
