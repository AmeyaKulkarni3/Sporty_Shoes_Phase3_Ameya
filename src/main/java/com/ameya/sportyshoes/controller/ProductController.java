package com.ameya.sportyshoes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ameya.sportyshoes.dto.CategoryDto;
import com.ameya.sportyshoes.dto.ProductDto;
import com.ameya.sportyshoes.entity.Product;
import com.ameya.sportyshoes.exception.CategoryAlreadyExistsException;
import com.ameya.sportyshoes.exception.NoSuchCategoryException;
import com.ameya.sportyshoes.exception.NoSuchProductException;
import com.ameya.sportyshoes.exception.ProductAlreadyExistsException;
import com.ameya.sportyshoes.repository.ProductRepository;
import com.ameya.sportyshoes.service.CategoryService;
import com.ameya.sportyshoes.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/product")
	public ModelAndView getProducts(){
		List<ProductDto> dtos = productService.getAllProducts();
		List<CategoryDto> catDtos = categoryService.getAllCategories();
		ModelAndView mv = new ModelAndView();
		mv.addObject("products",dtos);
		mv.addObject("categories", catDtos);
		mv.setViewName("productHome.jsp");
		return mv;
	}
	
	@RequestMapping("/add-product")
	public ModelAndView addCategory(HttpServletRequest req, HttpServletResponse res) throws ProductAlreadyExistsException {
		String brand = req.getParameter("brand");
		String name = req.getParameter("name");
		int size = Integer.parseInt(req.getParameter("size"));
		double price= Double.parseDouble(req.getParameter("price"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String category = req.getParameter("category");
		ProductDto dto = new ProductDto();
		dto.setBrand(brand);
		dto.setName(name);
		dto.setSize(size);
		dto.setQuantity(quantity);
		dto.setPrice(price);
		dto.setCategory(category);
		ProductDto created = productService.createProduct(dto);
//		List<ProductDto> products = productService.getAllProducts();
//		List<CategoryDto> catDtos = categoryService.getAllCategories();
////		products.add(created);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("products",products);
//		mv.addObject("categories", catDtos);
//		mv.setViewName("productHome.jsp");
		return getProducts();
	}
	
	@RequestMapping(value = "/update-product")
	public ModelAndView updateProduct(HttpServletRequest req, HttpServletResponse res) throws NoSuchProductException {
		int id = Integer.parseInt(req.getParameter("id"));
		String brand = req.getParameter("brand");
		String name = req.getParameter("name");
		int size = Integer.parseInt(req.getParameter("size"));
		double price= Double.parseDouble(req.getParameter("price"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String category = req.getParameter("category");
		ProductDto dto = new ProductDto();
		dto.setId(id);
		dto.setBrand(brand);
		dto.setName(name);
		dto.setSize(size);
		dto.setQuantity(quantity);
		dto.setPrice(price);
		dto.setCategory(category);
		productService.updateProduct(dto);
//		List<ProductDto> products = productService.getAllProducts();
//		products.add(updated);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("products",products);
//		mv.setViewName("productHome.jsp");
		return getProducts();
	}
	
	@RequestMapping(value = "/delete-product")
	public ModelAndView deleteCategory(HttpServletRequest req, HttpServletResponse res) throws NoSuchProductException {
		int id = Integer.parseInt(req.getParameter("id"));
		productService.deleteProduct(id);
		ModelAndView mv = getProducts();
		return mv;
	}
	
	@RequestMapping(value="/filter-products-categories")
	public ModelAndView getProductFromCategories(HttpServletRequest req, HttpServletResponse res) throws NoSuchProductException {
		String category = req.getParameter("category");
		List<ProductDto> products = productService.getProductsFromCategories(category);
		ModelAndView mv = new ModelAndView();
		mv.addObject("products", products);
		mv.setViewName("selectBrand.jsp");
		return mv;
	}
	
	@RequestMapping(value="/filter-products-admin")
	public ModelAndView getProductFromCategoriesAdmin(HttpServletRequest req, HttpServletResponse res) throws NoSuchProductException {
		String category = req.getParameter("category");
		List<ProductDto> products = productService.getProductsFromCategories(category);
		ModelAndView mv = new ModelAndView();
		mv.addObject("productsAdmin", products);
		mv.setViewName("listProductsWithCategory.jsp");
		return mv;
	}

}
