package br.com.login.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.login.services.ITokenServices;
import br.com.login.v1.models.UserModel;

@Component
public class TokenServices implements ITokenServices{

	@Override
	public String getToken(UserModel user) {
		return JWT.create()
				.withIssuer("Produtos")
				.withSubject(user.getUsername())
				.withClaim("id", user.getId().toString())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(10)
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("secreta"));
	}

}
