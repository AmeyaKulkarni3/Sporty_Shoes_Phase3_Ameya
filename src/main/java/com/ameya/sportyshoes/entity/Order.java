package com.ameya.sportyshoes.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private LocalDate date;

	private double totalAmount;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private List<Product> products;

}
