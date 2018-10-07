package com.blackcrowsys.dinewell.hotel.common.service;

import com.blackcrowsys.dinewell.hotel.security.repository.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtSecurityTokeniser {

    @Value("${security.jwt.key}")
    private String key;

    public String tokenise(final User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, key)
                .claim("Type", user.getUserType().getType())
                .compact();
    }

    @SuppressWarnings("squid:S00108")
    public boolean validate(String jwtToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken).getBody();
            if (claims.containsKey("Type")) {
                return true;
            }
        } catch (JwtException ex) {
        }
        return false;
    }
}
