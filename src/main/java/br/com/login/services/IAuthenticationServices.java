package br.com.login.services;

import org.springframework.stereotype.Service;

import br.com.login.v1.dtos.AuthDataDTO;
import br.com.login.v1.dtos.AuthTokenDTO;

@Service
public interface IAuthenticationServices{
		
//	public AuthenticationResponse register(RegisterRequest request);
	
	public AuthTokenDTO authenticate(AuthDataDTO request);
}
