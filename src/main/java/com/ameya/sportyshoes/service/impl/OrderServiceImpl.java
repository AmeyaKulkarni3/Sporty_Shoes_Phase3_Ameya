package com.ameya.sportyshoes.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import com.ameya.sportyshoes.dto.OrderDto;
import com.ameya.sportyshoes.dto.ProductDto;
import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.entity.Order;
import com.ameya.sportyshoes.entity.Product;
import com.ameya.sportyshoes.entity.User;
import com.ameya.sportyshoes.exception.ExceptionConstants;
import com.ameya.sportyshoes.exception.NoSuchProductException;
import com.ameya.sportyshoes.exception.NoSuchUserException;
import com.ameya.sportyshoes.exception.NotEnoughBalanceException;
import com.ameya.sportyshoes.exception.ProductOutOfStockException;
import com.ameya.sportyshoes.repository.OrderRepository;
import com.ameya.sportyshoes.repository.ProductRepository;
import com.ameya.sportyshoes.repository.UserRepository;
import com.ameya.sportyshoes.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:ValidationMessages.properties")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	Environment env;

	@Override
	public OrderDto createOrder(UserDto user, Map<Integer, Integer> orders)
			throws NoSuchUserException, NoSuchProductException, NotEnoughBalanceException, ProductOutOfStockException {
		User usr = userRepository.findById(user.getId()).orElseThrow(
				() -> new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString())));

		List<Product> products = new ArrayList<>();

		double totalPrice = 0;

		for (Entry<Integer, Integer> entry : orders.entrySet()) {
			Product product = productRepository.findById(entry.getKey()).orElseThrow(
					() -> new NoSuchProductException(env.getProperty(ExceptionConstants.PRODUCT_NOT_FOUND.toString())));
			int qty = entry.getValue();
			totalPrice = totalPrice + (qty * product.getPrice());
			if(product.getQuantity() < qty) {
				throw new ProductOutOfStockException(env.getProperty(ExceptionConstants.PRODUCT_OUTOF_STOCK.toString()));
			}
			product.setQuantity(product.getQuantity() - qty);
			productRepository.save(product);
			for(int i = 1; i <=qty; i++) {
				products.add(product);
			}	
		}

		Order order = new Order();
		order.setDate(LocalDate.now());
		if (usr.getBalance() < totalPrice) {
			throw new NotEnoughBalanceException(env.getProperty(ExceptionConstants.NOT_ENOUGH_BALANCE.toString()));
		}
		usr.setBalance(usr.getBalance() - totalPrice);
		order.setUser(usr);
		order.setProducts(products);
		order.setTotalAmount(totalPrice);
		Order saved = orderRepository.save(order);
		List<Order> userOrders = usr.getOrders();
		userOrders.add(saved);
		userRepository.save(usr);
		OrderDto returnValue = dataTransfer(order);
		return returnValue;
	}

	private OrderDto dataTransfer(Order order) {
		OrderDto dto = new OrderDto();
		UserDto u = new UserDto();
		u.setFirstName(order.getUser().getFirstName());
		u.setLastName(order.getUser().getLastName());
		u.setEmail(order.getUser().getEmail());
		u.setBalance(order.getUser().getBalance());
		dto.setUserDto(u);
		dto.setDate(order.getDate());
		dto.setTotalAmount(order.getTotalAmount());
		List<ProductDto> ps = new ArrayList<>();
		List<Product> products = order.getProducts();
		for(Product p : products) {
			ProductDto pdto = new ProductDto();
			pdto.setCategory(p.getCategory().getName());
			pdto.setBrand(p.getBrand());
			pdto.setName(p.getName());
			pdto.setSize(p.getSize());
			pdto.setPrice(p.getPrice());
			ps.add(pdto);
		}
		dto.setProducts(ps);
		return dto;
	}

	@Override
	public List<OrderDto> getUserOrders(int id) throws NoSuchUserException {
		User usr = userRepository.findById(id).orElseThrow(
				() -> new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString())));
		List<Order> orders = usr.getOrders();
		List<OrderDto> returnValue = new ArrayList<>();
		for(Order o : orders) {
			returnValue.add(dataTransfer(o));
		}
		return returnValue;
	}

	@Override
	public List<OrderDto> getOrdersByDate(LocalDate date) {
		List<Order> orders = orderRepository.findAllByDate(date);
		List<OrderDto> returnValue = new ArrayList<>();
		for(Order order : orders) {
			returnValue.add(dataTransfer(order));
		}
		return returnValue;
	}

}
