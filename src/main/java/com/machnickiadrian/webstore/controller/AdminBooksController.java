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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.machnickiadrian.webstore.entity.Author;
import com.machnickiadrian.webstore.entity.Book;
import com.machnickiadrian.webstore.enums.AdminTab;
import com.machnickiadrian.webstore.service.BookService;

@Controller
@RequestMapping("/admin/books")
public class AdminBooksController {
	
	private static final String ADMIN_TAB = "adminTab";

	@Autowired
	private BookService bookService;

	@GetMapping
	public String getBooksManagement(Model model, @RequestParam(defaultValue = "") String search) {
		List<Book> books;
		
		if (search.equals(""))
			books = bookService.findAll();
		else
			books = bookService.search(search);
		
		model.addAttribute("books", books);
		model.addAttribute(ADMIN_TAB, AdminTab.BOOKS);
		
		return "admin/books";
	}

	@GetMapping("/add")
	public String getAddBookForm(Model model) {
		if (!model.containsAttribute("book")) {
			Book book = new Book();
			List<Author> authors = new ArrayList<>();
			authors.add(new Author());
			book.setAuthors(authors);
			model.addAttribute("book", book);
		}

		return "admin/add-book";
	}

	@PostMapping(value = { "/add/addAuthor" })
	public String addAuthorToForm(@ModelAttribute Book book, Model model, RedirectAttributes redirectAttr) {
		book.getAuthors().add(new Author());
		redirectAttr.addFlashAttribute("book", book);

		return "redirect:/admin/books/add";
	}

	@PostMapping("/add")
	public String addBook(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "admin/add-book";
			
		bookService.save(book);
		
		return "redirect:/admin/books";
	}

	@GetMapping("/update")
	public String getUpdateBookForm(@RequestParam("bookId") Long id, Model model) {
		Book book = bookService.findById(id);
		model.addAttribute("book", book);

		return "admin/add-book";
	}

	@PostMapping("/update")
	public String updateBook(@ModelAttribute Book book, BindingResult bindingResult) {
		bookService.save(book);

		return "redirect:/admin/books";
	}

	@GetMapping("/delete")
	public String deleteBook(@RequestParam("bookId") Long id) {
		bookService.deleteById(id);

		return "redirect:/admin/books";
	}

}