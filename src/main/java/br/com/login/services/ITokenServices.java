package br.com.login.services;

import org.springframework.stereotype.Service;

import br.com.login.v1.models.UserModel;

@Service
public interface ITokenServices {

	public String getToken(UserModel user);
	public String getSubject(String token);
}
