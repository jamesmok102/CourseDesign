package xyz.jamesmok.minishop.util;

import xyz.jamesmok.minishop.exception.UserException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Component
public class JwtUtil {

    private static long time = 1000*60*60*24;
    private static String signature = "admin";

    public static String createToken() {

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                .claim("username","admin")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static String createToken2(long id, String name) {

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                .claim("id", id)
                .claim("username",name)
                .claim("role","saler")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static String createToken3(long id, String name) {

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                .claim("id", id)
                .claim("username",name)
                .claim("role","user")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static boolean checkToken(String token) throws UserException {
        if (token == null) {
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);

        } catch (Exception e) {
            return false;
        }
        return true;
//        if (Objects.equals(token, null)) {
//            throw new UserException("Null Token");
//        }
//        try {
//            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException e) {
//            throw new UserException("Invalid JWT signature.");
//        } catch (MalformedJwtException e) {
//            throw new UserException("Invalid JWT token.");
//        } catch (ExpiredJwtException e) {
//            throw new UserException("Expired JWT token.");
//        } catch (UnsupportedJwtException e) {
//            throw new UserException("Unsupported JWT token.");
//        } catch (IllegalArgumentException e) {
//            throw new UserException("JWT token compact of handler are invalid.");
//        }
    }



}
