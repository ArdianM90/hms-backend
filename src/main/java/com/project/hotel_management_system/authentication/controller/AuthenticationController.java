package com.project.hotel_management_system.authentication.controller;

import com.project.hotel_management_system.authentication.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hms/v1/")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("login")
    public ResponseEntity<AuthenticationResponse> checkLoginData(@RequestParam String login, @RequestParam String password) {
        AuthenticationResponse response = new AuthenticationResponse();
        if (login.isBlank() || password.isBlank()) {
            response.setMessage("Login and password cannot be empty.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (authenticationService.validateUserLoginData(login, password, response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
