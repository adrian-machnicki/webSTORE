package com.machnickiadrian.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.enums.AdminTab;
import com.machnickiadrian.webstore.service.OrderService;

/**
 * 
 * @author Adrian Machnicki
 *
 */
@Controller
@RequestMapping("/admin/orders")
public class AdminOrdersController {

	private static final String ADMIN_TAB = "adminTab";

	@Autowired
	private OrderService orderService;

	@GetMapping
	public String getOrdersManagement(Model model, @RequestParam(defaultValue = "") String search) {
		List<Order> orders;
		
		if (search.equals(""))
			orders = orderService.findAll();
		else
			orders = orderService.search(search);
		
		model.addAttribute("orders", orders);
		model.addAttribute(ADMIN_TAB, AdminTab.ORDERS);

		return "admin/orders";
	}

	@PostMapping("/paid")
	public String setPaid(@RequestParam Long orderId) {
		orderService.setPaid(orderId);

		return "redirect:/admin/orders";
	}

	@PostMapping("/sent")
	public String setSent(@RequestParam Long orderId) {
		orderService.setSent(orderId);

		return "redirect:/admin/orders";
	}
	
	@GetMapping("/order")
	public String getOrder(@RequestParam Long id, Model model) {
		Order order = orderService.findById(id);
		model.addAttribute("order", order);

		return "admin/order";
	}

}