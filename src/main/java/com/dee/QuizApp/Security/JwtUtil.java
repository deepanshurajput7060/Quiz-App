package com.dee.QuizApp.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret; // Keep this in a safe place
	
	public String generateToken( String username) {
		return Jwts.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
					.setExpiration(new Date (System.currentTimeMillis() + 1000*60*60*10))
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
					
	}
	
	public String extractUsername(String token) {
		return Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
	}
	
	private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

	
	public boolean validateToken(String token, UserDetails details) {
		return extractUsername(token).equals(details.getUsername())
										&& !isTokenExpired(token);
	}
	
	
}




















