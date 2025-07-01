package com.project.hms.authentication.controller;

import com.project.hms.user.account.UserAccountService;
import com.project.hms.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hms/v1/")
public class AuthenticationController {

    private final UserAccountService userAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public AuthenticationController(AuthenticationManager authenticationManager, UserAccountService userAccountService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userAccountService = userAccountService;
        this.jwtUtils = jwtUtils;
    }

    //todo zmienić na POST z request body
    @GetMapping("login")
    public ResponseEntity<AuthenticationResponse> checkLoginData(@RequestParam final String login, @RequestParam final String password) {
        AuthenticationResponse response = new AuthenticationResponse();
        if (login.isBlank() || password.isBlank()) {
            response.setMessage("Login and password cannot be empty.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        UserDto userDto = userAccountService.loadUserByUsername(login);
        response.setJwt(jwtUtils.generateToken(userDto));
        response.setUserId(userDto.getId());
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
