package com.qwertyjobs.qwertjobsapi.services;

import com.qwertyjobs.qwertjobsapi.model.JwtRequest;
import com.qwertyjobs.qwertjobsapi.model.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface User_Service {

    User saveUser(User user);
    UserDetails loadUserByUsername(String username);
    void login(JwtRequest request) throws Exception;


}
