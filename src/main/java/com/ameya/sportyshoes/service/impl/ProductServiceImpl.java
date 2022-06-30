package com.ameya.sportyshoes.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.ameya.sportyshoes.dto.CategoryDto;
import com.ameya.sportyshoes.dto.OrderDto;
import com.ameya.sportyshoes.dto.ProductDto;
import com.ameya.sportyshoes.dto.UserDto;
import com.ameya.sportyshoes.entity.Category;
import com.ameya.sportyshoes.entity.Order;
import com.ameya.sportyshoes.entity.Product;
import com.ameya.sportyshoes.entity.User;
import com.ameya.sportyshoes.exception.CategoryAlreadyExistsException;
import com.ameya.sportyshoes.exception.ExceptionConstants;
import com.ameya.sportyshoes.exception.NoSuchProductException;
import com.ameya.sportyshoes.exception.ProductAlreadyExistsException;
import com.ameya.sportyshoes.repository.CategoryRepository;
import com.ameya.sportyshoes.repository.ProductRepository;
import com.ameya.sportyshoes.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:ValidationMessages.properties")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	Environment env;

	@Override
	public ProductDto createProduct(ProductDto product) throws ProductAlreadyExistsException {
		List<Product> products = productRepository.findAllByName(product.getName());
		for (Product saved : products) {
			if (saved.getBrand().equals(product.getBrand()) && saved.getName().equals(product.getName())
					&& saved.getSize() == product.getSize() && saved.getPrice() == product.getPrice()
					&& saved.getCategory().getName().equals(product.getCategory())) {
				throw new ProductAlreadyExistsException(
						env.getProperty(ExceptionConstants.PRODUCT_ALREADY_EXISTS.toString()));
			}
		}

		Product prod = new Product();
		prod.setBrand(product.getBrand());
		prod.setName(product.getName());
		prod.setPrice(product.getPrice());
		prod.setQuantity(product.getQuantity());
		prod.setSize(product.getSize());
		prod.setOrders(new ArrayList<>());
		String catName = product.getCategory();
		Category cat = categoryRepository.findByName(catName);
		prod.setCategory(cat);

		Product cp = productRepository.save(prod);

		ProductDto dto = dataTransfer(cp);

		return dto;

	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		List<ProductDto> returnValue = new ArrayList<>();
		for (Product p : products) {
			ProductDto dto = dataTransfer(p);
			returnValue.add(dto);
		}
		return returnValue;
	}

	@Override
	public ProductDto updateProduct(ProductDto product) throws NoSuchProductException {
		Product p = productRepository.findById(product.getId()).orElseThrow(
				() -> new NoSuchProductException(env.getProperty(ExceptionConstants.PRODUCT_NOT_FOUND.toString())));
		if (!product.getBrand().equals(p.getBrand())) {
			p.setBrand(product.getBrand());
		}
		if (!product.getCategory().equals(p.getCategory().getName())) {
			Category c = categoryRepository.findByName(product.getCategory());
			p.setCategory(c);
		}
		if (!product.getName().equals(p.getName())) {
			p.setName(product.getName());
		}
		if (product.getSize() != p.getSize()) {
			p.setSize(product.getSize());
		}
		if (product.getQuantity() != p.getQuantity()) {
			p.setQuantity(product.getQuantity());
		}
		if (product.getPrice() != p.getPrice()) {
			p.setPrice(product.getPrice());
		}

		Product saved = productRepository.save(p);

		return dataTransfer(saved);
	}

	@Override
	public void deleteProduct(int id) throws NoSuchProductException {
		Product p = productRepository.findById(id).orElseThrow(
				() -> new NoSuchProductException(env.getProperty(ExceptionConstants.PRODUCT_NOT_FOUND.toString())));
		productRepository.delete(p);
	}

	private ProductDto dataTransfer(Product cp) {

		ProductDto dto = new ProductDto();
		dto.setId(cp.getId());
		dto.setName(cp.getName());
		dto.setCategory(cp.getCategory().getName());
		dto.setBrand(cp.getBrand());
		dto.setPrice(cp.getPrice());
		dto.setSize(cp.getSize());
		dto.setQuantity(cp.getQuantity());
		List<Order> orders = cp.getOrders();
		List<OrderDto> orderDto = new ArrayList<>();
		for (Order o : orders) {
			OrderDto od = new OrderDto();
			od.setId(o.getId());
			od.setDate(o.getDate());
			od.setTotalAmount(o.getTotalAmount());
			User user = o.getUser();
			UserDto ud = new UserDto();
			ud.setFirstName(user.getFirstName());
			ud.setLastName(user.getLastName());
			ud.setEmail(user.getEmail());
			od.setUserDto(ud);
			orderDto.add(od);
		}
		dto.setOrders(orderDto);

		return dto;

	}

	@Override
	public List<ProductDto> getProductsFromCategories(String category) throws NoSuchProductException {
		Category cat = categoryRepository.findByName(category);
		List<Product> products = productRepository.findAllByCategory(cat);
		if(products.size() == 0 || products == null) {
			throw new NoSuchProductException(env.getProperty(ExceptionConstants.PRODUCT_NOT_FOUND.toString()));
		}
		List<ProductDto> returnValue = new ArrayList<>();
		for (Product p : products) {
			ProductDto dto = dataTransfer(p);
			returnValue.add(dto);
		}
		return returnValue;
	}

}
