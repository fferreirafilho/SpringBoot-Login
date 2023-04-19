package br.com.login.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.login.v1.models.AccessGroupModel;
import br.com.login.v1.models.UserModel;

public interface UserRepositories extends JpaRepository<UserModel, UUID> {

	Optional<UserModel> findByUsername(String username);
	List<UserModel> findByAccessGroup(AccessGroupModel accessGroup);
	
	@Query("SELECT U FROM UserModel U WHERE U.enabled = TRUE")
	Page<UserModel> listAllActive(Pageable pageable);
	
	@Query("SELECT U FROM UserModel U WHERE U.enabled = FALSE")
	Page<UserModel> listAllDisable(Pageable pageable);
}