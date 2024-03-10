package com.ebenezer.audit.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private String SECRET = "357638792F423F4528482B4D6251655468576D5A7134743677397A2443264629";

    public String createJwt(String username, List<String> roles, Date expiryDate){
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expiryDate)
                .withClaim("username",username)
                .withClaim("roles",roles)
                .sign(algorithm);
    }
    public String extractUsernameFromJwt(String jwt){
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT theJwt = verifier.verify(jwt);
        return theJwt.getSubject();
    }
    public List<String> extractRolesFromJwt(String jwt){
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT theJwt = verifier.verify(jwt);
        return theJwt.getClaim("roles").asList(String.class);
    }

    public Date getExpirationDateFromToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT theJwt = verifier.verify(token);

        return theJwt.getExpiresAt();
    }
    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}


