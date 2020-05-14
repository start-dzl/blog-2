package com.dzl.blog2.config.security;


import com.dzl.blog2.config.GlobalProperties;
import com.dzl.blog2.enums.AccessTypeEnum;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.dzl.blog2.enums.AccessTypeEnum.ADMIN;


/**
 * Token 提供者
 *
 * @author yu
 */
@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private static final int INITIAL_CAPACITY = 5;
    private static String TYPE_KEY = "type";

    @Autowired
    private GlobalProperties globalProperties;

    private String token(String username, Date expiryDate, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, globalProperties.getSecret())
                .addClaims(claims).compact();
    }

    public String token(String username, AccessTypeEnum type) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + globalProperties.getExpire());
        Map<String, Object> claims = new HashMap<>(INITIAL_CAPACITY);
        claims.put(TYPE_KEY, type);
        return JwtTokenProvider.this.token(username, expiryDate, claims);
    }

    public String token(UserDetails userDetails) {
        //if (userDetails instanceof KafkaProperties.Admin) {
        return token(userDetails.getUsername(), ADMIN);
       /* } else {
            return token(userDetails.getUsername(), APP);
        }*/
    }

    public Pair<String, String> parse(String token) {
        Claims claims = Jwts.parser().setSigningKey(globalProperties.getSecret()).parseClaimsJws(token).getBody();
        String type = (String) claims.get(TYPE_KEY);
        String username = claims.getSubject();
        return Pair.of(type, username);
    }

    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(globalProperties.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.debug("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.debug("Invalid JWT token. token:{}", authToken);
        } catch (ExpiredJwtException ex) {
            logger.debug("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.debug("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.debug("JWT claims string is empty.");
        }
        return false;
    }
}