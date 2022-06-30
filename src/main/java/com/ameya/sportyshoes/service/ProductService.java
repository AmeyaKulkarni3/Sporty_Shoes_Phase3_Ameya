package com.ameya.sportyshoes.service;

import java.util.List;

import com.ameya.sportyshoes.dto.CategoryDto;
import com.ameya.sportyshoes.dto.ProductDto;
import com.ameya.sportyshoes.exception.CategoryAlreadyExistsException;
import com.ameya.sportyshoes.exception.NoSuchCategoryException;
import com.ameya.sportyshoes.exception.NoSuchProductException;
import com.ameya.sportyshoes.exception.ProductAlreadyExistsException;

public interface ProductService {
	
	public ProductDto createProduct(ProductDto product) throws ProductAlreadyExistsException;
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto updateProduct(ProductDto product) throws NoSuchProductException;
	
	public void deleteProduct(int id) throws NoSuchProductException;

	public List<ProductDto> getProductsFromCategories(String category) throws NoSuchProductException;

}
