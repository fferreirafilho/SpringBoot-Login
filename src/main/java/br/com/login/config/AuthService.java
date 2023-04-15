package br.com.login.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.login.repositories.UserRepositories;

@Service
public class AuthService implements UserDetailsService {

	private UserRepositories repository;

	public AuthService(UserRepositories repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username).get();
	}
}
