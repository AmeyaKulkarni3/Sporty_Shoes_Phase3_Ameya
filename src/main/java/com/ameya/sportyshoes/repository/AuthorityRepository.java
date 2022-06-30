package com.ameya.sportyshoes.repository;

import com.ameya.sportyshoes.entity.Authority;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
	
	public Authority findByName(String name);

}
