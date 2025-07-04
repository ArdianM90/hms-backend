package com.project.hms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.hms.common.HttpErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${app.cors.allowed-origin}")
    private String allowedOrigin;

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        System.out.println("Authentication failed: " + authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String errorCode = (String) request.getAttribute("auth_error");
        String message;
        if ("TOKEN_EXPIRED".equals(errorCode)) {
            message = "Access token has expired.";
            errorCode = "TOKEN_EXPIRED";
        } else {
            message = "Authentication required or invalid.";
            errorCode = "UNAUTHORIZED";
        }

        HttpErrorResponse error = new HttpErrorResponse(errorCode, message);
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
