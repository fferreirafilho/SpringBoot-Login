package br.com.login.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.login.v1.models.RoleModel;

public interface RolesRepositories extends JpaRepository<RoleModel, UUID> {

}