package com.ecommenrce.project.repository;

import com.ecommenrce.project.model.AppRole;
import com.ecommenrce.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role>findByRoleName(AppRole appRole);
}
