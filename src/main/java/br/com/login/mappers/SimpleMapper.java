package br.com.login.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.login.v1.dtos.AccessGroupDTO;
import br.com.login.v1.dtos.RoleDTO;
import br.com.login.v1.dtos.UserDTO;
import br.com.login.v1.models.AccessGroupModel;
import br.com.login.v1.models.RoleModel;
import br.com.login.v1.models.UserModel;

@Mapper
public interface SimpleMapper {

	SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);
		
	AccessGroupDTO accessGroup2AccessGroupDTO(AccessGroupModel group);
	
	AccessGroupModel accessGroupDTO2AcessGroup(AccessGroupDTO group);
	
	RoleDTO role2RoleDTO(RoleModel role);
	
	RoleModel roleDTO2Role(RoleDTO role);
	
	UserDTO user2UserDTO(UserModel user);
	
	UserModel userDTO2User(UserDTO user);

}
