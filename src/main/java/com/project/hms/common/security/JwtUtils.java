package com.project.hms.common.security;

import com.project.hms.modules.authentication.dto.UserPrincipal;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    private final String SECRET;
    private final long SHORT_EXPIRATION_MS = 5 * 60 * 1000;
    private final long LONG_EXPIRATION_MS = 24 * 60 * 60 * 1000;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.SECRET = secret;
        System.out.println("Secret: " + SECRET.length());
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserPrincipal principal) {
        String token = Jwts.builder()
                .subject(principal.getUsername())
                .claim("id", principal.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + SHORT_EXPIRATION_MS))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
        System.out.println("Wygenerowany token JWT: " + token);
        return token;
    }

    public String generateRefreshToken(UserPrincipal principal) {
        String token = Jwts.builder()
                .subject(principal.getUsername())
                .claim("id", principal.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + LONG_EXPIRATION_MS))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
        System.out.println("Wygenerowany refresh token: " + token);
        return token;
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        try {
            String tokenUsername = extractUsername(token);
            return tokenUsername.equals(username) && isTokenExpirationValid(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isTokenExpirationValid(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.after(new Date());
    }
}
