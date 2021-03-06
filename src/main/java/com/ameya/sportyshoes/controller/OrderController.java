package com.ameya.sportyshoes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ameya.sportyshoes.dto.OrderDto;
import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.exception.NoSuchProductException;
import com.ameya.sportyshoes.exception.NoSuchUserException;
import com.ameya.sportyshoes.exception.NotEnoughBalanceException;
import com.ameya.sportyshoes.exception.ProductOutOfStockException;
import com.ameya.sportyshoes.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping("/purchase-product")
	public ModelAndView createOrder(HttpServletRequest req, HttpServletResponse res)
			throws NoSuchUserException, NoSuchProductException, NotEnoughBalanceException, ProductOutOfStockException {
		HttpSession session = req.getSession();
		Map<Integer, Integer> orders = (Map<Integer, Integer>) session.getAttribute("orders");
		UserDto user = (UserDto) session.getAttribute("usr");
		OrderDto order = orderService.createOrder(user, orders);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("buySuccess.jsp");
		return mv;
	}

	@RequestMapping(value = "/get-user-orders", params = { "id" })
	public ModelAndView getUserOrders(HttpServletRequest req, HttpServletResponse res, @RequestParam("id") String id)
			throws NumberFormatException, NoSuchUserException {
		List<OrderDto> orders = orderService.getUserOrders(Integer.parseInt(id));
		UserDto dto = orders.get(0).getUserDto();
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders",orders);
		mv.addObject("user",dto);
		mv.setViewName("userOrders.jsp");
		return mv;
	}
	
	@RequestMapping("/orders-by-date")
	public ModelAndView getOrdersByDate(HttpServletRequest req, HttpServletResponse res) {
		String d = req.getParameter("date");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		LocalDate date = LocalDate.parse(d,formatter);
		List<OrderDto> orders = orderService.getOrdersByDate(date);
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders",orders);
		mv.setViewName("purchaseReportDate.jsp");
		return mv;
	}
	
	@RequestMapping("/orders-by-category")
	public ModelAndView getOrdersByCategory(HttpServletRequest req, HttpServletResponse res) {
		String category = req.getParameter("category");
		List<OrderDto> orders = orderService.getOrdersByCategory(category);
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders",orders);
		mv.setViewName("purchaseReportCategory.jsp");
		return mv;
	}
	
	

}
