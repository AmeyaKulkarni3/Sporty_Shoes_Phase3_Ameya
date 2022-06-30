package com.ameya.sportyshoes.repository;

import java.util.List;

import com.ameya.sportyshoes.entity.Category;
import com.ameya.sportyshoes.entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	public Product findByName(String name);
	
	public List<Product> findAllByName(String name);

	public List<Product> findAllByCategory(Category cat);

}
