package com.todoApp.todoApp.Model.DTO.RequestObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.stream.Collectors;

public record Authrequest (
         String email,
        String password
) {

    Authrequest MapToRequest(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        String body = request.getReader().lines().collect(Collectors.joining());
        return objectMapper.readValue(body, Authrequest.class);

    }

}
