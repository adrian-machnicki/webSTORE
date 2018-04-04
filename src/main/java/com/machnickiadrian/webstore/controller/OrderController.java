package com.machnickiadrian.webstore.controller;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machnickiadrian.webstore.entity.ShippingDetails;
import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.entity.User;
import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.service.OrderService;
import com.machnickiadrian.webstore.service.UserService;

/**
 * 
 * @author Adrian Machnicki
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private Cart cart;
	
	private Order order;

	@GetMapping("/checkout")
	public String getCheckoutForm(Model model, Principal principal) {
		order = new Order();
		order.addItemsFromCart(cart);

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("order", order);
		model.addAttribute("user", user);
		
		return "order/checkout-user";		
	}
	
	@GetMapping("/checkout/guest")
	public String getCheckoutFormForGuest(Model model) {
		order = new Order();
		order.addItemsFromCart(cart);
		
		ShippingDetails shippingDetails = new ShippingDetails();
		model.addAttribute("order", order);
		model.addAttribute("shippingDetails", shippingDetails);
		
		return "order/checkout-guest";		
	}

	@PostMapping("/checkout")
	public String placeUserOrder(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors())
			return "order/checkout-user";
		
		user = userService.findByUsername(user.getUsername());

		order.setUser(user);
		order.fillUsersShippingDetails(user);
		order.setDate(new Date());
		orderService.save(order);
		Long orderId = order.getId();
		order = new Order();
		cart.empty();

		return "redirect:/order/success/" + orderId;
	}

	@PostMapping("/checkout/guest")
	public String placeGuestOrder(@ModelAttribute @Valid ShippingDetails shippingDetails, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("order", order);
			return "order/checkout-guest";
		}

		order.setShippingDetails(shippingDetails);
		orderService.save(order);
		Long orderId = order.getId();
		order = new Order();
		cart.empty();

		return "redirect:/order/success/" + orderId;
	}
	
	@GetMapping("/success/{id}")
	public String confirmOrder(@PathVariable int id, Model model) {	
		model.addAttribute("orderId", id);
		
		return "order/order-confirmation";	
	}

}