package com.todoApp.todoApp.Model.DTO.RequestObject;

import com.todoApp.todoApp.Model.Enum.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


public record CreateUserRequest(
        String username,
        String email,
        String password
){
}
