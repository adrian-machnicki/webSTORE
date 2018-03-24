package com.machnickiadrian.webstore.repository;

import java.util.ArrayList;

import org.springframework.data.repository.Repository;

import com.machnickiadrian.webstore.entity.Order;

public interface OrderRepository extends Repository<Order, Long>   {
	
	Order findById(Long id);
	
	ArrayList<Order> findAll();
	
	Order save(Order order);

}
