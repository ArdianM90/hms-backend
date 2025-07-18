package com.project.hms.common.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("auth/login") || uri.contains("auth/refresh") || uri.contains("auth/logout")) {
            chain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER)) {
            jwt = authHeader.substring(BEARER.length());
            try {
                username = jwtUtils.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                System.out.println("Token expired: " + e.getMessage());
                request.setAttribute("auth_error", "TOKEN_EXPIRED");
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            } catch (JwtException e) {
                request.setAttribute("auth_error", "INVALID_TOKEN");
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(jwt, username)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
