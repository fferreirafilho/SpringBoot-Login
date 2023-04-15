package br.com.login.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.login.exceptions.ResourceNotFoundException;
import br.com.login.mappers.SimpleMapper;
import br.com.login.repositories.AccessGroupRepositories;
import br.com.login.repositories.UserRepositories;
import br.com.login.services.IAccessGroupServices;
import br.com.login.v1.dtos.AccessGroupDTO;
import br.com.login.v1.models.AccessGroupModel;
import br.com.login.v1.models.RoleModel;
import jakarta.transaction.Transactional;

@Component
public class AccessGroupServices implements IAccessGroupServices {

	private AccessGroupRepositories accessGroupRepository;
	private UserRepositories userRepository;

	public AccessGroupServices(AccessGroupRepositories accessGroupRepository, UserRepositories userRepository) {
		this.accessGroupRepository = accessGroupRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Page<AccessGroupDTO> listAll(Pageable pageable) {
		Page<AccessGroupModel> page = accessGroupRepository.findAll(pageable);
		return page.map(r -> SimpleMapper.INSTANCE.accessGroup2AccessGroupDTO(r));
	}

	@Override
	public AccessGroupDTO findById(UUID id) {
		return accessGroupRepository.findById(id).map(g -> SimpleMapper.INSTANCE.accessGroup2AccessGroupDTO(g))
				.orElseThrow(() -> new ResourceNotFoundException("Access Group not found for this id: " + id));
	}

	@Transactional
	@Override
	public AccessGroupDTO insert(AccessGroupDTO accessGroupDTO) {
		
		AccessGroupModel model = SimpleMapper.INSTANCE.accessGroupDTO2AcessGroup(accessGroupDTO);
		model = toUpperCase(model);
		model = accessGroupRepository.save(model);
		
		return SimpleMapper.INSTANCE.accessGroup2AccessGroupDTO(model);
	}

	@Transactional
	@Override
	public AccessGroupDTO update(UUID id, AccessGroupDTO accessGroupDTO) {

		AccessGroupModel model = accessGroupRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Access Group not found for this id: " + id));
		model.getRoles().clear();
		accessGroupRepository.save(model);
		model.setName(accessGroupDTO.getName());		
		model = SimpleMapper.INSTANCE.accessGroupDTO2AcessGroup(accessGroupDTO);
		model.setId(id);
		model = toUpperCase(model);
		
		if (!accessGroupDTO.getRoles().isEmpty()) {
			List<RoleModel> list = new ArrayList<>();
			accessGroupDTO.getRoles().forEach((r) -> list.add(SimpleMapper.INSTANCE.roleDTO2Role(r)));
			model.setRoles(list);
		}

		model = accessGroupRepository.save(model);
		return SimpleMapper.INSTANCE.accessGroup2AccessGroupDTO(model);
	}

//	@Transactional
//	@Override
//	public DeleteResponse delete(UUID id) {
//
//		AccessGroupModel accessGroupDB = accessGroupRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Access Group not found for this id: " + id));
//		List<UserModel> list = userRepository.findByAccessGroup(accessGroupDB);
//		if (list.size() > 0) {
//			throw new InvalidOperationException(
//					"Invalid Operation, access group have at least one person associated. Please remove and try again");
//		}
//		accessGroupRepository.deleteById(id);
//
//		return new DeleteResponse(id.toString(), "Access Group deleted with success");
//	}
	
	private AccessGroupModel toUpperCase(AccessGroupModel model) {
		model.setName(model.getName().trim().toUpperCase());
		return model;
	}

}