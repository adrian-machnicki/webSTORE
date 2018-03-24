package com.machnickiadrian.webstore.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.machnickiadrian.webstore.entity.Author;
import com.machnickiadrian.webstore.entity.Book;
import com.machnickiadrian.webstore.repository.BookRepository;

@Controller
@RequestMapping("/admin/books")
public class AdminBookController {
	
	@Autowired
	BookRepository repository;
	
	@GetMapping
	public String getBooksManagement(Model model) {
		List<Book> books = repository.findAll();
		model.addAttribute("books", books);
		
		return "admin/books";
	}
	
	@GetMapping("/add")
	public String getAddBookForm(Model model) {
		if(!model.containsAttribute("book")) {
			Book book = new Book();
			List<Author> authors = new ArrayList<>();
			authors.add(new Author());
			book.setAuthors(authors);
			model.addAttribute("book", book);
		}
		
		return "admin/add-book";
	}
	
	@RequestMapping(value= {"/add/addAuthor"}, method= {RequestMethod.GET, RequestMethod.POST})
	public String addAuthorToForm(@ModelAttribute("book") Book book, Model model, RedirectAttributes redirectAttr) {
		book.getAuthors().add(new Author());
		redirectAttr.addFlashAttribute("book", book);
		
		return "redirect:/admin/books/add";
	}
	
	@PostMapping("/add")
	public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "admin/add-book";
		}
		
		System.out.println("---------------------------");
		System.out.println("AUTHOR NOT VALIDATED");
		System.out.println("---------------------------");
		repository.save(book);
		
		return "redirect:/admin/books";
	}
	
	@GetMapping("/update")
	public String getUpdateBookForm(@RequestParam("bookId") Long id, Model model) {
		Book book = repository.findById(id);
		model.addAttribute("book", book);
		
		return "admin/add-book";
	}
	
	@PostMapping("/update")
	public String updateBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
		System.out.println("@BOOK:");
		System.out.println(book);
		
		return "redirect:/admin/books";
	}
	
	@GetMapping("/delete")
	public String deleteBook(@RequestParam("bookId") Long id) {
		repository.deleteById(id);
		
		return "redirect:/admin/books";
	}

}