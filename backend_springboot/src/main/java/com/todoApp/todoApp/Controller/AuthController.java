package com.todoApp.todoApp.Controller;

import com.todoApp.todoApp.Model.DTO.RequestObject.Authrequest;
import com.todoApp.todoApp.Model.DTO.RequestObject.CreateUserRequest;
import com.todoApp.todoApp.Model.Entity.User;
import com.todoApp.todoApp.Model.Enum.Role;
import com.todoApp.todoApp.Security.JWT.JWTService;
import com.todoApp.todoApp.Service.Abstract.UserService;
import jakarta.servlet.FilterChain;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/register",consumes = "application/json",produces = "application/json")
    public void register(@RequestBody CreateUserRequest request) {

    }
    @PostMapping(value = "/login",consumes = "application/json",produces = "application/json")
    public void login(@RequestBody Authrequest authrequest)  {

     }

}
