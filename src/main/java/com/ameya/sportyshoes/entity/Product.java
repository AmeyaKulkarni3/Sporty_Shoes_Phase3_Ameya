package com.ameya.sportyshoes.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String brand;
	
	private String name;
	
	private int size;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
	private double price;
	
	private int quantity;
	
	@ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private List<Order> orders;

}
