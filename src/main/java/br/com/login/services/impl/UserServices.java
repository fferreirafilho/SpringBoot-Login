package br.com.login.services.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.login.exceptions.ResourceNotFoundException;
import br.com.login.mappers.SimpleMapper;
import br.com.login.repositories.UserRepositories;
import br.com.login.services.IUserServices;
import br.com.login.v1.dtos.UserDTO;
import br.com.login.v1.models.UserModel;

@Component
public class UserServices implements IUserServices {

	Logger logger = LoggerFactory.getLogger(UserServices.class);

	private UserRepositories userRepository;
	private PasswordEncoder passwordEncoder;
	private AccessGroupServices accessGroupService;

	public UserServices(UserRepositories userRepository, PasswordEncoder passwordEncoder,
			AccessGroupServices accessGroupService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.accessGroupService = accessGroupService;
	}

	public Page<UserDTO> listAll(Pageable pageable) {
		Page<UserModel> page = userRepository.findAll(pageable);
		return page.map(u -> SimpleMapper.INSTANCE.user2UserDTO(u));
	}

	@Override
	public UserDTO findById(UUID id) {
		return userRepository.findById(id).map(u -> SimpleMapper.INSTANCE.user2UserDTO(u))
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found for this id: " + id));
	}

//	@Override
//	public UserDTO insert(RegisterRequest request) {
//
//		request.setAccessGroup(accessGroupService.findById(request.getAccessGroup().getId()));
//
//		UserModel user = new UserModel(request.getName(), request.getUsername(),
//				passwordEncoder.encode(request.getPassword()), true,
//				SimpleMapper.INSTANCE.accessGroupDTO2AcessGroup(request.getAccessGroup()));
//		
//		toUpperCase(user);
//
//		user = userRepository.save(user);
//
//		return SimpleMapper.INSTANCE.user2UserDTO(user);
//	}

	@Override
	public UserDTO update(UUID id, UserDTO user) {
		user.setAccessGroup(accessGroupService.findById(user.getAccessGroup().getId()));
		UserModel model = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found for this id: " + id));

		model.setName(user.getName());
		model.setUsername(user.getUsername());
		model.setAccessGroup(SimpleMapper.INSTANCE.accessGroupDTO2AcessGroup(user.getAccessGroup()));
		model.setEnabled(user.getEnabled());
		
		toUpperCase(model);
		
		model = userRepository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.user2UserDTO(model);

	}

//	@Override
//	public UserDTO resetPassword(UUID id, @NotBlank String password) {
//
//		UserModel userDB = userRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Resource not found for this id: " + id));
//
//		String usernameDB = userDB.getUsername().toUpperCase().trim();
//		String usernameToken = JWTService.getUserName().toUpperCase();
//
//
//		if (!usernameDB.equals(usernameToken)) {
//			throw new InvalidUserOperation("Invalid operation, please see input data");
//		}
//
//		userDB.setPassword(passwordEncoder.encode(password));
//
//		userDB = userRepository.save(userDB);
//		return SimpleMapper.INSTANCE.user2UserDTO(userDB);
//	}
	
	private void toUpperCase(UserModel model) {
		model.setName(model.getName().trim().toUpperCase());
		model.setUsername(model.getUsername().trim().toUpperCase());
	}
}