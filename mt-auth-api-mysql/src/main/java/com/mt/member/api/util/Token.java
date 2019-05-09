package com.mt.member.api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Token {

	private static Token singleton = new Token();

	private static String key;

	public Token() {
		key = generateString(36);
	}

	private String generateString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
		Random rnd = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rnd.nextInt(characters.length()));
		}
		return new String(text);
	}

	public static Token getInstance() {
		return singleton;
	}

	public String get(String name) {
		LocalDateTime expiration = LocalDateTime.now();
		expiration = expiration.plusDays(1);

		String token = Jwts.builder().setIssuer("mt-auth-api").setSubject("token").claim("name", name).claim("scope", "admins").setIssuedAt(new Date()).setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant())).signWith(SignatureAlgorithm.HS256, key).compact();

		return token;
	}

	public Claims claims(String token) throws Exception {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
}
