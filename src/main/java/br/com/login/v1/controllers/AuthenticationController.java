package br.com.login.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.login.services.IAuthenticationServices;
import br.com.login.v1.dtos.AuthDataDTO;
import br.com.login.v1.dtos.AuthTokenDTO;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	private final IAuthenticationServices service;

	public AuthenticationController(IAuthenticationServices service) {
		this.service = service;
	}

//	@PostMapping("/register")
//	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
//		return ResponseEntity.ok(service.register(request));
//	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthTokenDTO> authenticate(@RequestBody @Valid AuthDataDTO request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

}