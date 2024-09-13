package com.W1_AOP.Service;

import com.W1_AOP.Entities.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtService {

	@Value("${jwt.secretKey}")
	private String jwtSecretKey;

	private SecretKey getSecretKey(){
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(Employee employee){
		return Jwts.builder()
				.subject(employee.getId().toString())
				.claim("email",employee.getEmail())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000*60))
				.signWith(getSecretKey())
				.compact();
	}

	public Long getuserIdFromToken(String token){
		Claims claims = Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();

		return Long.valueOf(claims.getSubject());
	}

}
