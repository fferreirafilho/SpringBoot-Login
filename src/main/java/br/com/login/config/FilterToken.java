package br.com.login.config;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.login.repositories.UserRepositories;
import br.com.login.services.ITokenServices;
import br.com.login.v1.models.UserModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

	private ITokenServices tokenService;
	private UserRepositories userRepository;

	public FilterToken(ITokenServices tokenService, UserRepositories userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token;
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null) {

			token = authorizationHeader.replace("Bearer ", "");
			String subject = this.tokenService.getSubject(token);
			UserModel user = this.userRepository.findByUsername(subject).get();
			
			if(user.isEnabled() == false) {
				throw new DisabledException("User is disabled");
			}
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
}
