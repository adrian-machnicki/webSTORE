package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.book.BookService;
import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.book.exception.BookNotFoundException;
import com.machnickiadrian.webstore.enums.NavbarTab;
import com.machnickiadrian.webstore.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Adrian Machnicki
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private static final String NAVBAR_TAB = "navbarTab";
    private final BookService bookService;
    private Cart cart;

    @Autowired
    public BookController(BookService bookService, Cart cart) {
        this.bookService = bookService;
        this.cart = cart;
    }

    @GetMapping
    public String getBooks(Model model, @RequestParam(defaultValue = "") String search,
                           @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "11") int size) {
        List<BookDto> books;

        if (!search.equals("")) {
            books = bookService.search(search);
        } else {
            books = bookService.findAll(page - 1, size);
            int maxPage = (int) Math.ceil(((double) bookService.countAll()) / size);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("currentPage", page);
        }

        model.addAttribute("books", books);
        model.addAttribute("addedBooksIds", cart.getAddedBooksIds());
        model.addAttribute(NAVBAR_TAB, NavbarTab.BOOKS);
        return "books/books";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        BookDto book = bookService.findById(id);
        if (book == null)
            throw new BookNotFoundException(id);

        model.addAttribute("book", book);
        return "books/book";
    }

}