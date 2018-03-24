package com.machnickiadrian.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.repository.OrderRepository;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderRepository repository;
	
	@Autowired
	Order order;
	
	@Autowired
	Cart cart;

	@GetMapping
	public String getOrderPage(Model model) {

		if (!model.containsAttribute("order")) {
			order.addItemsFromCart(cart);
			model.addAttribute("order", order);
		}
		
		return "order/order";
	}
	
	@GetMapping("/checkout")
	public String getCheckoutForm(Model model) {
		
		model.addAttribute("order", order);
		
		return "order/checkout";
	}

}