package com.machnickiadrian.webstore.service;

import java.util.List;

import com.machnickiadrian.webstore.entity.Order;

/**
 * Service interface for <code>Order</code> objects manipulation.
 * 
 * @author Adrian Machnicki
 *
 */
public interface OrderService {
	
	Order findById(Long id);
	
	List<Order> findAll();
	
	void save(Order order);
	
	void setPaid(Long id);
	
	void setSent(Long id);

	List<Order> search(String phrase);

}