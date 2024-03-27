package com.todoApp.todoApp.Security.JWT;

import com.todoApp.todoApp.Model.Entity.User;
import com.todoApp.todoApp.Model.Enum.Role;
import com.todoApp.todoApp.Repository.UserRepository;
import com.todoApp.todoApp.Service.Abstract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.ErrorManager;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {


    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.findByEmail(authentication.getName());

        if (user!=null) {

            if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
                for (Role role : user.getAuthorities()) {
                    grantedAuthorityList.add(new SimpleGrantedAuthority(role.getAuthority()));
                }

                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorityList);
            } else {

                throw new BadCredentialsException("");

            }
        } else {

            throw new BadCredentialsException("User not found in database.");
        }
    }
}