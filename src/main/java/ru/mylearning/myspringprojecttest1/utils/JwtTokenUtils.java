package ru.mylearning.myspringprojecttest1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    private SecretKey getSecretKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(UserDetails userDetails){
        log.info("генерация токена generateToken");
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
                .claims(claims)
                .issuedAt(issueDate)
                .expiration(expiredDate)
                .subject(userDetails.getUsername())
                .signWith(getSecretKey())
                .compact();
    }



    public String getUserName(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token){
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token){
        log.info("получение информации из токена getAllClaimsFromToken");
        return (Claims) Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parse(token)
                .getPayload();
    }
}
