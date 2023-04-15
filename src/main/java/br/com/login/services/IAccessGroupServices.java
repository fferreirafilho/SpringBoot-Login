package br.com.login.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.login.v1.dtos.AccessGroupDTO;

@Service
public interface IAccessGroupServices {

	public Page<AccessGroupDTO> listAll(Pageable pageable);

	public AccessGroupDTO findById(UUID fromString);

	public AccessGroupDTO insert(AccessGroupDTO accessGroupDTO);

	public AccessGroupDTO update(UUID id, AccessGroupDTO accessGroupDTO);

//	public String delete(UUID id);

}