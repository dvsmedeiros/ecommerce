package com.dvsmedeiros.ecommerce.api.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.controller.dao.impl.UserDAO;
import com.dvsmedeiros.commons.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService extends ApplicationEntity {
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	static final String EXPOSE_HEADER = "Access-Control-Expose-Headers";
	
	public void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);		
		response.addHeader(EXPOSE_HEADER, HEADER_STRING);
		
	}
	
	public Authentication getAuthentication(HttpServletRequest request) {
		
		long start = System.currentTimeMillis();
		
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
		
		long end = System.currentTimeMillis();
		getLogger(this.getClass()).debug("Authenticated in: " + (end - start) + " ms."); 
		
		return null;
	}
}
