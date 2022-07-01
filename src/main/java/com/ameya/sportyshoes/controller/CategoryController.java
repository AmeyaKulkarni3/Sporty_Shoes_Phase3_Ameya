package com.ameya.sportyshoes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ameya.sportyshoes.dto.CategoryDto;
import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.exception.CategoryAlreadyExistsException;
import com.ameya.sportyshoes.exception.NoSuchCategoryException;
import com.ameya.sportyshoes.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/category")
	public ModelAndView getCategories(){
		List<CategoryDto> dtos = categoryService.getAllCategories();
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories",dtos);
		mv.setViewName("categoryHome.jsp");
		return mv;
	}
	
	@RequestMapping("/add-category")
	public ModelAndView addCategory(HttpServletRequest req, HttpServletResponse res) throws CategoryAlreadyExistsException {
		String catName = req.getParameter("catName");
		CategoryDto dto = categoryService.createCategory(catName);
		List<CategoryDto> dtos = categoryService.getAllCategories();
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories",dtos);
		mv.setViewName("categoryHome.jsp");
		return mv;
	}
	
	@RequestMapping(value = "/update-category")
	public ModelAndView updateCategory(HttpServletRequest req, HttpServletResponse res) throws NoSuchCategoryException {
		String catName = req.getParameter("catName");
		int id = Integer.parseInt(req.getParameter("id"));
		CategoryDto dto = categoryService.updateCategory(id, catName);
		ModelAndView mv = getCategories();
		return mv;
	}
	
	@RequestMapping(value = "/delete-category")
	public ModelAndView deleteCategory(HttpServletRequest req, HttpServletResponse res) throws NoSuchCategoryException {
		int id = Integer.parseInt(req.getParameter("id"));
		categoryService.deleteCategory(id);
		ModelAndView mv = getCategories();
		return mv;
	}
	
	@RequestMapping(value="/shop-category")
	public ModelAndView filterCategories(HttpServletRequest req, HttpServletResponse res) {
		List<CategoryDto> dtos = categoryService.getAllCategories();
//		HttpSession session = req.getSession();
//		UserDto dto = (UserDto) session.getAttribute("usr");
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories",dtos);
		mv.setViewName("selectCategory.jsp");
		return mv;
	}
	
	@RequestMapping("/get-categories-for-orders")
	public ModelAndView getCategoriesForOrders(HttpServletRequest req, HttpServletResponse res) {
		
		List<CategoryDto> dtos = categoryService.getAllCategories();
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("cats", dtos);
		mv.setViewName("selectPurchaseReportCategory.jsp");
		return mv;
	}
		
	


}
