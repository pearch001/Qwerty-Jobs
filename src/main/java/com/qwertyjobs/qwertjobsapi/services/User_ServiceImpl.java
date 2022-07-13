package com.qwertyjobs.qwertjobsapi.services;

import com.qwertyjobs.qwertjobsapi.dao.UserDao;
import com.qwertyjobs.qwertjobsapi.model.JwtRequest;
import com.qwertyjobs.qwertjobsapi.model.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class User_ServiceImpl implements User_Service, UserDetailsService {
    private final UserDao userDao;


    @Autowired
    private  PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public User saveUser(User user) {
        log.info("Saving new user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userDao.findByEmail(email);
        if (user != null) {
            Collection<SimpleGrantedAuthority> claims = new ArrayList<>();
            if (email.equals("admin")){
            claims.add(new SimpleGrantedAuthority("Role_Admin"));
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    claims);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }



    @Override
    public void login(JwtRequest request) throws  Exception{

        log.info(request.getEmail());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
