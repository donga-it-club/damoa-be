package com.donga.damoa.global.config.security;

import com.donga.damoa.domain.member.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-hours}")
    private long expirationHours;

    @Value("${jwt.issuer}")
    private String issuer;

    private Key key;

    @PostConstruct
    protected void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createJwtToken(Member member) {
        Claims claims = Jwts.claims().setSubject(member.getEmail());
        claims.put("role", member.getRole().name());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationHours);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer(issuer)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key)
            .compact();
    }

}
