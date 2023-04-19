package br.com.login.services;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.login.v1.dtos.InsertUserDTO;
import br.com.login.v1.dtos.UserDTO;

@Service
public interface IUserServices {

	public Page<UserDTO> listAll(Pageable pageable);
	
	public Page<UserDTO> active(Pageable pageable);
	
	public Page<UserDTO> disabled(Pageable pageable);

	public UserDTO findById(UUID id);
	
	public UserDTO insert(InsertUserDTO request);
	
	public UserDTO update(UUID id, UserDTO user);

	public UserDTO resetPassword(UUID id, String password);
}
