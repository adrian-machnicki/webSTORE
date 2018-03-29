package com.machnickiadrian.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.machnickiadrian.webstore.entity.Book;
import com.machnickiadrian.webstore.enums.NavbarTab;
import com.machnickiadrian.webstore.exception.BookNotFoundException;
import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	private static final String NAVBAR_TAB = "navbarTab";
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private Cart cart;
	
	@GetMapping
	public String getBooks(Model model, @RequestParam(defaultValue = "") String search) {
		List<Book> books;
		
		if (search.equals(""))
			books = bookService.findAll();
		else
			books = bookService.search(search);
			
		model.addAttribute("books", books);
		model.addAttribute("addedBooksIds", cart.getAddedBooksIds());
		model.addAttribute(NAVBAR_TAB, NavbarTab.BOOKS);
		
		return "books/books";
	}
	
	@GetMapping("/book")
	public String getBook(@RequestParam Long id, Model model) {	
		Book book = bookService.findById(id);
		if (book == null)
			throw new BookNotFoundException();
			
		model.addAttribute("book", book);	
		return "books/book";
	}

}