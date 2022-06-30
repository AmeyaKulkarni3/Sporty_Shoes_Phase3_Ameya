package com.ameya.sportyshoes.service;

import java.util.List;

import com.ameya.sportyshoes.dto.CategoryDto;
import com.ameya.sportyshoes.exception.CategoryAlreadyExistsException;
import com.ameya.sportyshoes.exception.NoSuchCategoryException;

public interface CategoryService {
	
	public CategoryDto createCategory(String name) throws CategoryAlreadyExistsException;
	
	public List<CategoryDto> getAllCategories();
	
	public CategoryDto updateCategory(int id, String name) throws NoSuchCategoryException;
	
	public void deleteCategory(int id) throws NoSuchCategoryException;

}
