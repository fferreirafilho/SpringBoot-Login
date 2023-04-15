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
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.login.services.IRolesServices;
import br.com.login.utils.MediaType;
import br.com.login.v1.dtos.RoleDTO;

@Controller
@RequestMapping("/api/v1/roles")
public class RoleController {

	private IRolesServices service;

	Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	public RoleController(IRolesServices service) {
		this.service = service;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<RoleDTO>> findAll(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		logger.info("Find all");
		return ResponseEntity.ok().body(service.listAll(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<RoleDTO> findById(@PathVariable(value = "id") String id) {
		logger.info("Find one");
		return ResponseEntity.ok().body(service.findById(UUID.fromString(id)));
	}
}
