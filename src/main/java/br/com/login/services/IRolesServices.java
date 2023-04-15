package br.com.login.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.login.v1.dtos.RoleDTO;

@Service
public interface IRolesServices {
	
	public Page<RoleDTO> listAll(Pageable pageable);
	
	public RoleDTO findById(UUID id);
}
