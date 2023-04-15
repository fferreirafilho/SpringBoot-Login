package br.com.login.v1.controllers;

import java.net.URI;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.login.services.IAccessGroupServices;
import br.com.login.utils.MediaType;
import br.com.login.v1.dtos.AccessGroupDTO;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/groups")
public class AccessGroupController {

	private IAccessGroupServices service;

	public AccessGroupController(IAccessGroupServices service) {
		this.service = service;
	}

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<AccessGroupDTO>> findAll(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		logger.info("Find all");
		return ResponseEntity.ok().body(service.listAll(pageable));
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<AccessGroupDTO> findById(@PathVariable(value = "id") String id) {
		logger.info("Find one");
		return ResponseEntity.ok().body(service.findById(UUID.fromString(id)));
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<AccessGroupDTO> insert(@Valid @RequestBody AccessGroupDTO accessGroupDTO) {
		logger.info("Insert one");

		accessGroupDTO = service.insert(accessGroupDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accessGroupDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(accessGroupDTO);
	}
	
	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<AccessGroupDTO> update(@PathVariable UUID id,@Valid @RequestBody AccessGroupDTO accessGroupDTO) {
		
		logger.info("Update access group");
		return ResponseEntity.ok().body(service.update(id, accessGroupDTO));
	}
	
	@DeleteMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		
		logger.info("delete access group");
		
		return ResponseEntity.ok().body(service.delete(id));
	}
}