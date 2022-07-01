package com.ameya.sportyshoes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.ameya.sportyshoes.dto.OrderDto;
import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.exception.NoSuchProductException;
import com.ameya.sportyshoes.exception.NoSuchUserException;
import com.ameya.sportyshoes.exception.NotEnoughBalanceException;
import com.ameya.sportyshoes.exception.ProductOutOfStockException;

public interface OrderService {

	public OrderDto createOrder(UserDto user, Map<Integer, Integer> orders)
			throws NoSuchUserException, NoSuchProductException, NotEnoughBalanceException, ProductOutOfStockException;

	public List<OrderDto> getUserOrders(int id) throws NoSuchUserException;

	public List<OrderDto> getOrdersByDate(LocalDate date);

	public List<OrderDto> getOrdersByCategory(String category);

}
