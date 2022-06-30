package com.ameya.sportyshoes.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CategoryDto implements Serializable{

	private static final long serialVersionUID = -546075958542658172L;

	private int id;
	
	private String name;

	private List<ProductDto> products;
	
}
