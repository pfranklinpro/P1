package com.paulfranklin.p1.services;

import com.paulfranklin.p1.dtos.responses.Principal;
import com.paulfranklin.p1.utils.JwtConfiguration;
import com.paulfranklin.p1.utils.custom_exceptions.InvalidAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TokenService {
    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final JwtConfiguration jwtConfiguration;

    public TokenService(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public String createNewToken(Principal principalSubject) {
        long myTime = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()                                                                        // builds token
                .setId(principalSubject.getUserId())                                                                    // token id = user id
                .setIssuer("PaulFranklin719-Trainer-P1")                                                                // token issuer = project issuer
                .setIssuedAt(new Date(myTime))                                                                          // token issued time = request time
                .setExpiration(new Date(myTime + jwtConfiguration.getTokenExpiration()))                                // token expiration time = configuration expiration time
                .setSubject(principalSubject.getUsername())                                                             // token subject = username
                .claim("email", principalSubject.getEmail())
                .claim("givenName", principalSubject.getGivenName())
                .claim("surname", principalSubject.getSurname())
                .claim("role", principalSubject.getRole())
                .signWith(jwtConfiguration.getJwtSignatureAlgorithm(), jwtConfiguration.getTokenKey());                 // signs token using configuration

        String token = tokenBuilder.compact();
        return token;
    }

    public Principal retrievePrincipalFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfiguration.getTokenKey())                                                      // Using the server's key
                    .parseClaimsJws(token)                                                                              // Get the claims from the token
                    .getBody();                                                                                         // As a claims object
            return new Principal(
                    claims.getId(),                                                                                     // Principal userId = claim id
                    claims.getSubject(),                                                                                // Principal username = claim subject
                    claims.get("email", String.class),
                    claims.get("givenName", String.class),
                    claims.get("surname", String.class),
                    claims.get("role", String.class)
            );
        } catch (Exception e) {
            logger.warn("Attempted to retrieve principal from token and was not able to.");
            throw new InvalidAuthenticationException();
        }
    }
}
