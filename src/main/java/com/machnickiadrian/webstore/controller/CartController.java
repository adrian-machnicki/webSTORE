package com.machnickiadrian.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.machnickiadrian.webstore.entity.Book;
import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.repository.BookRepository;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private BookRepository repository;
	
	@GetMapping
	public String getCart(Model model) {
		model.addAttribute("cart", cart);
		
		return "cart/cart";
	}
	
	@GetMapping("/add")
	public String addToCart(@RequestParam("bookId") Long bookId, @RequestParam(name="amount", defaultValue="1") int amount) {
		Book book = repository.findById(bookId);
		cart.addBook(book, amount);
		
		return "redirect:/books/all";
	}
	
	@GetMapping("/remove")
	public String removeFromCart(@RequestParam("id") Long id) {
		Book book = repository.findById(id);
		cart.removeBook(book);
		
		return "redirect:/cart";
	}

}
