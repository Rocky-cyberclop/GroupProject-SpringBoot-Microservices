package com.twoteethreeeight.userservice.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.twoteethreeeight.userservice.models.User;
import com.twoteethreeeight.userservice.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequesFilter extends OncePerRequestFilter{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String requesTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (requesTokenHeader == null || requesTokenHeader.isBlank() || requesTokenHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtToken = requesTokenHeader.split(" ")[1].trim();
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userRepository.findByEmail(username);
		
		if (jwtTokenUtil.validateToken(jwtToken, user)) {
		    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null);
		    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		} else {
		    SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(request, response);
	}

	
}
