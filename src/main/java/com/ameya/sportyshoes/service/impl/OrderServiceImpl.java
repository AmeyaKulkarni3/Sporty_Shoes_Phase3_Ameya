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
import com.ameya.sportyshoes.entity.Category;
import com.ameya.sportyshoes.entity.Order;
import com.ameya.sportyshoes.entity.Product;
import com.ameya.sportyshoes.entity.User;
import com.ameya.sportyshoes.exception.ExceptionConstants;
import com.ameya.sportyshoes.exception.NoSuchProductException;
import com.ameya.sportyshoes.exception.NoSuchUserException;
import com.ameya.sportyshoes.exception.NotEnoughBalanceException;
import com.ameya.sportyshoes.exception.ProductOutOfStockException;
import com.ameya.sportyshoes.repository.CategoryRepository;
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
	CategoryRepository categoryRepository;

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
			if (product.getQuantity() < qty) {
				throw new ProductOutOfStockException(
						env.getProperty(ExceptionConstants.PRODUCT_OUTOF_STOCK.toString()));
			}
			product.setQuantity(product.getQuantity() - qty);
			productRepository.save(product);
			for (int i = 1; i <= qty; i++) {
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
		for (Product p : products) {
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
		for (Order o : orders) {
			returnValue.add(dataTransfer(o));
		}
		return returnValue;
	}

	@Override
	public List<OrderDto> getOrdersByDate(LocalDate date) {
		List<Order> orders = orderRepository.findAllByDate(date);
		List<OrderDto> returnValue = new ArrayList<>();
		for (Order order : orders) {
			returnValue.add(dataTransfer(order));
		}
		return returnValue;
	}

	@Override
	public List<OrderDto> getOrdersByCategory(String category) {
		Category cat = categoryRepository.findByName(category);
		List<Product> products = productRepository.findAllByCategory(cat);
		List<OrderDto> dtos = new ArrayList<>();
		for(Product p : products) {
			List<Order> ord = p.getOrders();
			List<Order> orders = new ArrayList<>();
			for(Order or : ord) {
				if(!orders.contains(or)) {
					orders.add(or);
				}
			}
			for(Order o : orders) {
				List<Product> ps = o.getProducts();
				List<ProductDto> pdtos = new ArrayList<>();
				for(Product pr : ps) {
					if(pr.getCategory().getName().equals(category)) {
						ProductDto pdto = new ProductDto();
						pdto.setBrand(pr.getBrand());
						pdto.setName(pr.getName());
						pdto.setCategory(pr.getCategory().getName());
						pdto.setSize(pr.getSize());
						pdto.setPrice(pr.getPrice());
						pdtos.add(pdto);
					}
				}
				OrderDto odto = new OrderDto();
				odto.setId(o.getId());
				odto.setDate(o.getDate());
				odto.setTotalAmount(o.getTotalAmount());
				odto.setProducts(pdtos);
				User user = o.getUser();
				UserDto udto = new UserDto();
				udto.setFirstName(user.getFirstName());
				udto.setLastName(user.getLastName());
				udto.setEmail(user.getEmail());
				odto.setUserDto(udto);
				if(dtos.size() != 0) {
					for(OrderDto d : dtos) {
						if(d.getId() != odto.getId()) {
							dtos.add(odto);
							break;
						}
					}
				} else {
					dtos.add(odto);
				}
				
				
			}
		}
		return dtos;
	}

//	@Override
//	public List<OrderDto> getOrdersByCategory(String category) {
//		List<Order> orders = (List<Order>) orderRepository.findAll();
//		List<OrderDto> dtos = new ArrayList<>();
//		for (Order o : orders) {
//			List<Product> products = o.getProducts();
//			OrderDto dto = new OrderDto();
//			List<ProductDto> ps = new ArrayList<>();
//			for (Product p : products) {
//				if (p.getCategory().getName().equals(category)) {
//					if (dto.getProducts() == null || dto.getProducts().size() == 0) {
//						UserDto u = new UserDto();
//						u.setFirstName(o.getUser().getFirstName());
//						u.setLastName(o.getUser().getLastName());
//						u.setEmail(o.getUser().getEmail());
//						u.setBalance(o.getUser().getBalance());
//						dto.setUserDto(u);
//						dto.setDate(o.getDate());
//						dto.setTotalAmount(o.getTotalAmount());
//						ProductDto pd = new ProductDto();
//						pd.setBrand(p.getBrand());
//						pd.setName(p.getName());
//						pd.setSize(p.getSize());
//						pd.setPrice(p.getPrice());
//						pd.setCategory(p.getCategory().getName());
//						ps.add(pd);
//						dto.setProducts(ps);
//					} else {
//						ProductDto pd = new ProductDto();
//						pd.setBrand(p.getBrand());
//						pd.setName(p.getName());
//						pd.setSize(p.getSize());
//						pd.setPrice(p.getPrice());
//						pd.setCategory(p.getCategory().getName());
//						ps.add(pd);
//						dto.setProducts(ps);
//					}
//
//				}
//			}
//			dtos.add(dto);
//
//		}
//		return dtos;
//	}

}
