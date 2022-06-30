package com.ameya.sportyshoes.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.ameya.sportyshoes.dto.CategoryDto;
import com.ameya.sportyshoes.dto.ProductDto;
import com.ameya.sportyshoes.entity.Category;
import com.ameya.sportyshoes.entity.Product;
import com.ameya.sportyshoes.exception.CategoryAlreadyExistsException;
import com.ameya.sportyshoes.exception.ExceptionConstants;
import com.ameya.sportyshoes.exception.NoSuchCategoryException;
import com.ameya.sportyshoes.repository.CategoryRepository;
import com.ameya.sportyshoes.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:ValidationMessages.properties")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	Environment env;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public CategoryDto createCategory(String name) throws CategoryAlreadyExistsException {
		Category saved = categoryRepository.findByName(name);
		if (saved != null) {
			String catName = "";
			String newName = "";
			if (saved.getName().contains(" ")) {
				String[] s = saved.getName().split(" ");
				for (String st : s) {
					catName = catName + st;
				}
			} else {
				catName = saved.getName();
			}
			if (name.contains(" ")) {
				String[] s = name.split(" ");
				for (String st : s) {
					newName = newName + st;
				}
			} else {
				newName = name;
			}
			if (newName.toLowerCase().equals(catName.toLowerCase())) {
				throw new CategoryAlreadyExistsException(
						env.getProperty(ExceptionConstants.CATEGORY_ALREADY_EXISTS.toString()));
			}

		}

		Category cat = new Category();
		cat.setName(name);
		cat.setProducts(new ArrayList<>());
		Category c = categoryRepository.save(cat);
		CategoryDto dto = new CategoryDto();
		dto.setId(c.getId());
		dto.setName(c.getName());
		return dto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		List<CategoryDto> returnValue = new ArrayList<>();
		for(Category c : categories) {
			CategoryDto dto = new CategoryDto();
			dto.setId(c.getId());
			dto.setName(c.getName());
			List<Product> products = c.getProducts();
			List<ProductDto> pdtos = new ArrayList<>();
			for(Product p : products) {
				ProductDto pdto = new ProductDto();
				pdto.setId(p.getId());
				pdto.setBrand(p.getBrand());
				pdto.setName(p.getName());
				pdto.setPrice(p.getPrice());
				pdto.setSize(p.getSize());
				pdto.setQuantity(p.getQuantity());
				pdtos.add(pdto);
			}
			dto.setProducts(pdtos);
			returnValue.add(dto);
		}
		return returnValue;
	}

	@Override
	public CategoryDto updateCategory(int id, String name) throws NoSuchCategoryException {
		Category category = categoryRepository.findById(id).orElseThrow(() -> 
			new NoSuchCategoryException(env.getProperty(ExceptionConstants.CATEGORY_NOT_FOUND.toString())));
		category.setName(name);
		Category cat = categoryRepository.save(category);
		CategoryDto dto = new CategoryDto();
		dto.setId(cat.getId());
		dto.setName(cat.getName());
		List<Product> products = cat.getProducts();
		List<ProductDto> pdtos = new ArrayList<>();
		for(Product p : products) {
			ProductDto pdto = new ProductDto();
			pdto.setId(p.getId());
			pdto.setBrand(p.getBrand());
			pdto.setName(p.getName());
			pdto.setPrice(p.getPrice());
			pdto.setSize(p.getSize());
			pdto.setQuantity(p.getQuantity());
			pdtos.add(pdto);
		}
		dto.setProducts(pdtos);
		return dto;
	}

	@Override
	public void deleteCategory(int id) throws NoSuchCategoryException {
		Category category = categoryRepository.findById(id).orElseThrow(() -> 
		new NoSuchCategoryException(env.getProperty(ExceptionConstants.CATEGORY_NOT_FOUND.toString())));
		categoryRepository.delete(category);
	}

}
