package com.todoApp.todoApp.Service.Abstract;


import com.todoApp.todoApp.Model.DTO.RequestObject.CreateUserRequest;
import com.todoApp.todoApp.Model.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(CreateUserRequest request);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
