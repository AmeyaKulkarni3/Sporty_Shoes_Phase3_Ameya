package com.ameya.sportyshoes.repository;

import com.ameya.sportyshoes.entity.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public Role findByName(String name);

}
