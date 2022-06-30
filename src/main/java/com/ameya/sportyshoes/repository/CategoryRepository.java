package com.ameya.sportyshoes.repository;

import com.ameya.sportyshoes.entity.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

	public Category findByName(String name);

}
