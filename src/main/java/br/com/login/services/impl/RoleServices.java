package br.com.login.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.login.exceptions.ResourceNotFoundException;
import br.com.login.mappers.SimpleMapper;
import br.com.login.repositories.RolesRepositories;
import br.com.login.services.IRolesServices;
import br.com.login.v1.dtos.RoleDTO;
import br.com.login.v1.models.RoleModel;

@Component
public class RoleServices implements IRolesServices {

	private RolesRepositories repository;

	public RoleServices(RolesRepositories repository) {
		this.repository = repository;
	}

	@Override
	public Page<RoleDTO> listAll(Pageable pageable) {
		Page<RoleModel> page = repository.findAll(pageable);
		return page.map(r -> SimpleMapper.INSTANCE.role2RoleDTO(r));
	}

	@Override
	public RoleDTO findById(UUID id) {
		return repository.findById(id).map(r -> SimpleMapper.INSTANCE.role2RoleDTO(r))
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found for this id: " + id));
	}

}
