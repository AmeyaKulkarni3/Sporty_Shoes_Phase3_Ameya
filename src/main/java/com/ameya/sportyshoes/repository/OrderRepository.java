package com.ameya.sportyshoes.repository;

import java.time.LocalDate;
import java.util.List;

import com.ameya.sportyshoes.entity.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	List<Order> findAllByDate(LocalDate date);

}
