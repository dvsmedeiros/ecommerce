package com.dvsmedeiros.ecommerce.api.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.dao.impl.UserDAO;
import com.dvsmedeiros.commons.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	public void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);		
		response.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
		
	}
	
	public Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			
			User load = (User) dao.loadUserByUsername(user);
			
			if (load != null && load.getId() > 0) {
				return new UsernamePasswordAuthenticationToken(load, null, load.getAuthorities());
			}
		}
		return null;
	}
}
