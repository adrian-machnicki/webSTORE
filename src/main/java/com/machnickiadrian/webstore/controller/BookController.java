package com.machnickiadrian.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.machnickiadrian.webstore.entity.Book;
import com.machnickiadrian.webstore.repository.BookRepository;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@GetMapping("/all")
	public String listAllBooks(Model model) {
		List<Book> books = repository.findAll();
		model.addAttribute("books", books);
		
		return "books/books";
	}
	
	@GetMapping
	public String getBook(@RequestParam("id") Long id, Model model) {	
		Book book = repository.findById(id);
		model.addAttribute("book", book);
		
		return "books/book";
	}

}