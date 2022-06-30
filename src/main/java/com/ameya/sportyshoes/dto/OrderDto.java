package com.ameya.sportyshoes.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.ameya.sportyshoes.entity.User;

import lombok.Data;

@Data
public class OrderDto implements Serializable{

	private static final long serialVersionUID = -833678342817259094L;

	private int id;

	private LocalDate date;

	private double totalAmount;

	private UserDto userDto;
	
	private List<ProductDto> products;

}
