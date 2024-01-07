package com.twoteethreeeight.userservice.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.twoteethreeeight.userservice.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

@Component
public class JWTTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 3600;

	 private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	
	@Value("${jwt.secrect}")
	private static String secrect;
	  @Value("${jwt.expiration}")
	    private Long expiration;
	
	   public String getUsernameFromToken(String token) {
	        return getClaimsFromToken(token).getSubject();
	    }
	   private Claims getClaimsFromToken(String token) {
	        return  Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
	    }
	   private boolean isTokenExpired(String token) {
	        final Date expiration = getClaimsFromToken(token).getExpiration();
	        return expiration.before(new Date());
	    }


	private Claims getAllClaimFromToken(String token) {
		try {
			return  Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }
    
    public String generateTokenForUserDetail(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }
    public boolean validateToken(String token, UserDetails userValObject) {
        try {
            final String tokenUsername = getUsernameFromToken(token);
            if (tokenUsername.equals(userValObject.getUsername()) && !isTokenExpired(token)) {
                return true;
            }
        } catch (ExpiredJwtException e) {
            System.out.print("Token has expired");
            // Handle expiration if needed
        } catch (Exception e) {
        	 System.out.print("Error validating token: ");
        }
        return false;
    }

	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expiration * 1000000);

		return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SECRET_KEY,SignatureAlgorithm.HS256)
                .compact();
    }
	

//	public Boolean validateToken(String token,UserDetails userDetails) {
//		final String usernam =getUsernameFromToken(token);
//		if (usernam != null) {
//			return (usernam.equals(userDetails.getUsername()) && !isTokenExprired(token));
//		}
//		return false;
//	}
}
