package com.project.hms.modules.authentication;

import com.project.hms.modules.authentication.dto.AuthenticationResponse;
import com.project.hms.modules.authentication.dto.LoginRequest;
import com.project.hms.modules.authentication.dto.UserPrincipal;
import com.project.hms.modules.account.UserAccountService;
import com.project.hms.common.security.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Objects;

@RestController
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

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> checkLoginData(@RequestBody LoginRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        if (Objects.isNull(request) || request.getLogin().isBlank() || request.getPassword().isBlank()) {
            response.setMessage("Login and password cannot be empty.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        UserPrincipal principal = userAccountService.loadUserByUsername(request.getLogin());
        response.setJwt(jwtUtils.generateToken(principal));
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", jwtUtils.generateRefreshToken(principal))
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofMinutes(10))
                .build();
        response.setUserId(principal.getId());
        response.setSuccess(true);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(response);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        AuthenticationResponse response = new AuthenticationResponse();
        System.out.println("Odświeżam token: " + refreshToken);
        if (refreshToken == null || refreshToken.isBlank()) {
            return unauthorized("Refresh token missing.");
        }
        try {
            String username = jwtUtils.extractUsername(refreshToken);
            UserPrincipal user = userAccountService.loadUserByUsername(username);
            if (jwtUtils.isTokenExpirationValid(refreshToken)) {
                response.setJwt(jwtUtils.generateToken(user));
                response.setUserId(user.getId());
                response.setSuccess(true);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            return unauthorized("Refresh token is expired.");
        }
        return unauthorized("Unexpected error while refreshing token.");
    }

    private ResponseEntity<AuthenticationResponse> unauthorized(String message) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setSuccess(false);
        response.setMessage(message);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}
