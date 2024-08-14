package com.semi.youtube.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.semi.youtube.model.vo.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProvider {

	private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	public String create(Member member) {
		return Jwts.builder()
				.signWith(secretKey)
				.setClaims(Map.of(
						"id", member.getId()
						))
				.setIssuedAt(new Date())
				.setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
				.compact();
	}
	
	public Member validate(String token) {
		Claims claims = Jwts
						.parser()
						.setSigningKey(secretKey)
						.parseClaimsJws(token).getBody();
		
		return Member.builder()
				.id((String) claims.get("id"))
				.build();
	}
}
