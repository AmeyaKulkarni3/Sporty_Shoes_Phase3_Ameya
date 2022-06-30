package com.ameya.sportyshoes.repository;

import java.util.List;

import com.ameya.sportyshoes.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	public List<User> findAllByFirstName(String name);

}
