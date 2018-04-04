package com.machnickiadrian.webstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.repository.OrderRepository;

/**
 * Implementation of <code>OrderService</code> interface. It's methods are
 * transactional and secured with Spring Security and JSR-250.
 * 
 * @author Adrian Machnicki
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private static final String ADMIN = "ROLE_ADMIN";
	
	@Autowired
	private OrderRepository repository;

	@PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.user.username == principal.username")
	@Transactional(readOnly = true)
	@Override
	public Order findById(Long id) {
		return repository.findById(id);
	}

	@RolesAllowed(ADMIN)
	@Transactional(readOnly = true)
	@Override
	public List<Order> findAll() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public void save(Order order) {
		repository.save(order);
	}

	@RolesAllowed(ADMIN)
	@Transactional
	@Override
	public void setPaid(Long id) {
		Order order = repository.findById(id);
		boolean paid = order.isPaid();
		order.setPaid(!paid);
	}

	@RolesAllowed(ADMIN)
	@Transactional
	@Override
	public void setSent(Long id) {
		Order order = repository.findById(id);
		boolean sent = order.isSent();
		order.setSent(!sent);
	}

	@RolesAllowed(ADMIN)
	@Transactional(readOnly = true)
	@Override
	public List<Order> search(String phrase) {
		phrase = phrase.trim();
		String[] keywords = phrase.split(" ");
		List<Order> allOrders = repository.findAll();
		List<Order> foundOrders = new ArrayList<>();
		
		for (Order order : allOrders) {
			String id = String.valueOf(order.getId());
			if(id.equals(phrase)) {
				foundOrders.add(order);
				continue;
			}
		
			StringJoiner orderData = new StringJoiner(" ");
			orderData.add(id);
			
			if (order.getUser() != null) {
				orderData.add(order.getUser().getFirstName());
				orderData.add(order.getUser().getLastName());
				orderData.add(order.getUser().getUsername());
				orderData.add(order.getUser().getUserDetails().getCity());
			}
			
			if (order.getShippingDetails() != null) {
				orderData.add(order.getShippingDetails().getFirstName());
				orderData.add(order.getShippingDetails().getLastName());
				orderData.add(order.getShippingDetails().getCity());
			}
			
			boolean containsAll = true;
			for (String keyword : keywords) {
				if (!orderData.toString().toLowerCase().contains(keyword.toLowerCase())) {
					containsAll = false;
					break;
				}
			}
			if (containsAll)
				foundOrders.add(order);			
		}
		
		return foundOrders;
	}
}