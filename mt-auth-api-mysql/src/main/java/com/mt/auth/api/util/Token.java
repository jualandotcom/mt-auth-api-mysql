package com.mt.auth.api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Token {

	private static Token singleton = new Token();

	private static HashMap<String, String> keyMap;

	public Token() {
		keyMap = new HashMap<String, String>();
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

	public String generate(String email) {
		LocalDateTime expiration = LocalDateTime.now();
		expiration = expiration.plusDays(1);
		
		String salt = generateString(36);
		keyMap.put(email, salt);

		String token = Jwts.builder().setIssuer("mt-auth-api").setSubject("token").claim("name", email).claim("scope", "admins").setIssuedAt(new Date()).setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant())).signWith(SignatureAlgorithm.HS256, keyMap.get(email)).compact();

		return token;
	}

	public void invalidate(String email) {
		String salt = generateString(36);
		keyMap.put(email, salt);
	}

	public Claims claims(String email, String token) throws Exception {
		return Jwts.parser().setSigningKey(keyMap.get(email)).parseClaimsJws(token).getBody();
	}
}
