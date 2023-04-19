package br.com.login.v1.controllers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.login.exceptions.StandardError;
import br.com.login.utils.MediaType;

@Controller
@RequestMapping("/acess-denied")
public class AccessDeniedController {

	@GetMapping(produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML }, value = "/{protocol}/{version}/{resource}")
	public ResponseEntity<StandardError> accessDenied(@PathVariable(value = "protocol") String protocol,
			@PathVariable(value = "version") String version, @PathVariable(value = "resource") String resource) {

		String path = "/" + protocol + "/" + resource;

		StandardError err = new StandardError(Instant.now(), 403, "Insufficient privilege",
				"Access is denied for this resource, please contact administrator of system", path);

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML }, value = "/{protocol}/{version}/{resource}/{id}")
	public ResponseEntity<StandardError> accessDeniedWithId(@PathVariable(value = "protocol") String protocol,
			@PathVariable(value = "version") String version, @PathVariable(value = "resource") String resource,
			@PathVariable(value = "id") String id) {

		String path = "/" + protocol + "/" + resource + "/" + id;

		StandardError err = new StandardError(Instant.now(), 403, "Insufficient privilege",
				"Access is denied for this resource, please contact administrator of system", path);

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<StandardError> UserDisabled() {

		StandardError err = new StandardError(Instant.now(), 403, "Insufficient privilege",
				"Access is denied for this resource, please contact administrator of system", null);

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
