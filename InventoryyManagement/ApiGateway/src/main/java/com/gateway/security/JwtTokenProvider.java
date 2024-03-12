package com.gateway.security;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")	
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirationDate;
	
	
	private Key key() {
		return Keys.hmacShaKeyFor(
				Decoders.BASE64.decode(jwtSecret)
		);
	}
	
	public String getUsername(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parseClaimsJws(token)
			.getBody();
		
		String username = claims.getSubject();
		
		return username;
	}
	
	public boolean validateToken(String token) {
		Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parse(token);
		return true;
	}
	
	
}
