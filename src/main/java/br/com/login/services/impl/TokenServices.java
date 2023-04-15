package br.com.login.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.login.services.ITokenServices;
import br.com.login.v1.models.UserModel;

@Component
public class TokenServices implements ITokenServices{
	
	@Value(value = "${application.jwt.secret-key:default}")
	private String secretKey = "bolo_de_cenoura";
	
	@Value(value = "${application.jwt.expiration-minutes:default}")
	private Integer expirationMinutes = 60;
	
	@Value(value = "${application.jwt.issuer:default}")
	private String issuer = "login";

	@Override
	public String getToken(UserModel user) {
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(user.getUsername())
				.withClaim("id", user.getId().toString())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(expirationMinutes)
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256(secretKey));
	}

	@Override
	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256(secretKey)).withIssuer(issuer).build().verify(token).getSubject();
	}

}