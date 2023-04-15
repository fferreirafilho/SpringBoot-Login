package br.com.login.v1.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.login.services.IUserServices;
import br.com.login.utils.MediaType;
import br.com.login.v1.dtos.UserDTO;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

	private IUserServices service;

	public UserController(IUserServices service) {
		this.service = service;
	}

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<UserDTO>> findAll(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		logger.warn("Find all");
		return ResponseEntity.ok().body(service.listAll(pageable));
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") String id) {
		logger.warn("Find by id");
		return ResponseEntity.ok().body(service.findById(UUID.fromString(id)));
	}

//	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
//			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public ResponseEntity<UserDTO> insert(@Valid @RequestBody RegisterRequest registerRequest) {
//		logger.info("Insert one");
//
//		UserDTO userDTO = service.insert(registerRequest);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
//				.toUri();
//		return ResponseEntity.created(uri).body(userDTO);
//	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO) {

		logger.info("Update person");
		return ResponseEntity.ok().body(service.update(id, userDTO));
	}
//
//	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
//			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/reset-password/{id}")
//
//	public ResponseEntity<UserDTO> resetPassword(@PathVariable UUID id, @Valid @RequestBody ResetPasswordRequest request) {
//		logger.warn("reset password" + request.getPassword());
//
//		return ResponseEntity.ok().body(service.resetPassword(id, request.getPassword()));
//	}
}