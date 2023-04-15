package br.com.login.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.login.services.IAuthenticationServices;
import br.com.login.services.ITokenServices;
import br.com.login.v1.dtos.AuthDataDTO;
import br.com.login.v1.dtos.AuthTokenDTO;
import br.com.login.v1.models.UserModel;

@Component
public class AuthenticationServices implements IAuthenticationServices {

	Logger logger = LoggerFactory.getLogger(AuthenticationServices.class);

	private AuthenticationManager authenticationManager;
	private ITokenServices tokenService;

	public AuthenticationServices(AuthenticationManager authenticationManager, ITokenServices tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

//	@Transactional
//	public AuthenticationResponse register(RegisterRequest request) {
//		UserModel user = new UserModel(request.getName(), request.getEmail(),
//				passwordEncoder.encode(request.getPassword()), true,
//				new AccessGroupModel(UUID.fromString("d7e51510-cbfd-11ed-afa1-0242ac120002"), "ADMIN"));
//		repository.save(user);
//		String jwt = jwtService.gernerateToken(user);
//		return new AuthenticationResponse(jwt);
//	}

	public AuthTokenDTO authenticate(AuthDataDTO request) {

		UsernamePasswordAuthenticationToken usernamePassworAuthenticationToken = new UsernamePasswordAuthenticationToken(
				request.getEmail(), request.getPassword());

		Authentication authenticate = this.authenticationManager.authenticate(usernamePassworAuthenticationToken);
		UserModel usuario = (UserModel) authenticate.getPrincipal();
		AuthTokenDTO token = new AuthTokenDTO(tokenService.getToken(usuario));

		return token;
	}
}