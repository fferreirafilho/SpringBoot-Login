package br.com.login.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.login.v1.models.AccessGroupModel;
import br.com.login.v1.models.UserModel;

public interface UserRepositories extends JpaRepository<UserModel, UUID> {

	Optional<UserModel> findByUsername(String username);
	List<UserModel> findByAccessGroup(AccessGroupModel accessGroup);
}