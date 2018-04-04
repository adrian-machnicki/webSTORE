package com.machnickiadrian.webstore.repository;

import java.util.ArrayList;

import org.springframework.data.repository.Repository;

import com.machnickiadrian.webstore.entity.Order;

/**
 * Repository class for <code>Order</code> objects.
 * 
 * @author Adrian Machnicki
 *
 */
public interface OrderRepository extends Repository<Order, Long> {
	
	Order findById(Long id);
	
	ArrayList<Order> findAll();
	
	void save(Order order);

}