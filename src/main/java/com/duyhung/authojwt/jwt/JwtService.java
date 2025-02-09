package com.duyhung.authojwt.jwt;

import com.duyhung.authojwt.entity.CustomUserDetail;
import com.duyhung.authojwt.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {

    private static final Long EXPIRY_DATE = 604800000L; // 7 days in milliseconds

    @Value("${jwt.secret-key}")
    private String secretKey;
    private SecretKey key;

    private SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        }
        return key;
    }

    public String generateJwtToken(CustomUserDetail user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRY_DATE))
                .signWith(getKey())
                .compact();
    }

    public Claims extractClaim(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractClaim(token).getSubject();
    }

    public boolean verifyToken(String token) {
        try {
            Claims claims = extractClaim(token);
            Date expirationDate = claims.getExpiration();
            return !expirationDate.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
