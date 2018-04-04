package com.machnickiadrian.webstore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.Book;
import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.entity.OrderRecord;
import com.machnickiadrian.webstore.entity.User;
import com.machnickiadrian.webstore.enums.NavbarTab;
import com.machnickiadrian.webstore.exception.EmailExistsException;
import com.machnickiadrian.webstore.exception.UsernameExistsException;
import com.machnickiadrian.webstore.service.UserServiceImpl;

/**
 * 
 * @author Adrian Machnicki
 *
 */
@Controller
public class UserController {
	
	private static final String NAVBAR_TAB = "navbarTab";

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);

		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			try {
				userService.registerNewUser(userDto);
			} catch (EmailExistsException e) {
				bindingResult.rejectValue("email", "User with this email already exists.", "User with this email already exists.");
			} catch (UsernameExistsException e) {
				bindingResult.rejectValue("username", "User with this username already exists.", "User with this username already exists.");
			}
		}

		if (bindingResult.hasErrors())
			return "register";
		else
			return "redirect:/home";
	}
	
	@GetMapping("/user")
	public String getUserPage(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute(NAVBAR_TAB, NavbarTab.PROFILE);
		
		return "user/user";
	}
	
	@GetMapping("/user/profile")
	public String getProfile(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		return "user/profile";
	}
	
	@PostMapping("/user/profile")
	public String saveProfile(@ModelAttribute User user, BindingResult bindingResult) {	
		if (bindingResult.hasErrors())
			return "user/profile";
		
		return "redirect:/user/profile";
	}
	
	@GetMapping("/user/books")
	public String getUsersBooks(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Book> books = new ArrayList<>();
		Book book;
		for (Order order : user.getOrders()) {
			for (OrderRecord record : order.getRecords()) {
				book = record.getBook();
				if (!books.contains(book))
					books.add(book);
			}
		}
		
		model.addAttribute("books", books);
		
		return "user/books";
	}
	
	@GetMapping("/user/orders")
	public String getUsersOrders(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Order> orders = user.getOrders();	
		model.addAttribute("orders", orders);
		
		return "user/orders";
	}
	
}