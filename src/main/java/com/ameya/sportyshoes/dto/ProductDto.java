package com.ameya.sportyshoes.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 6313571789616335572L;

	private int id;
	
	private String brand;

	private String name;
	
	private int size;

	private String category;

	private double price;

	private int quantity;
	
	private List<OrderDto> orders;

}
