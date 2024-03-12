package com.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

	Roles findByName(String name);
}
