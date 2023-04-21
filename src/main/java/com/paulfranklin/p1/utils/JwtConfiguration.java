package com.paulfranklin.p1.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;

public class JwtConfiguration {
    private final Logger logger = LoggerFactory.getLogger(JwtConfiguration.class);

    private final int tokenExpiration = 60 * 60 * 1000;
    private final SignatureAlgorithm jwtSignatureAlgorithm = SignatureAlgorithm.HS256;

    private final Key tokenKey;
    private final Properties tokenProperties;

    public JwtConfiguration() {
        tokenProperties = new Properties();

        try
        {
            tokenProperties.load(new FileReader("src/main/resources/db.jwtProperties"));
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.warn("Failed to create JwtConfiguration.");
        }

        String saltBase64 = tokenProperties.getProperty("salt");
        byte[] saltBase64Binary = DatatypeConverter.parseBase64Binary(saltBase64);
        tokenKey = new SecretKeySpec(saltBase64Binary, jwtSignatureAlgorithm.getJcaName());
    }

    public int getTokenExpiration() {
        return tokenExpiration;
    }

    public SignatureAlgorithm getJwtSignatureAlgorithm() {
        return jwtSignatureAlgorithm;
    }

    public Key getTokenKey() {
        return tokenKey;
    }
}
