package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.book.BookService;
import com.machnickiadrian.webstore.book.dto.AuthorDto;
import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.enums.AdminTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Machnicki
 */
@Controller
@RequestMapping("/admin/books")
public class AdminBooksController {

    private static final String ADMIN_TAB = "adminTab";
    private final BookService bookService;

    @Autowired
    public AdminBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooksManagement(Model model, @RequestParam(defaultValue = "") String search) {
        List<BookDto> books;

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
            BookDto book = new BookDto();
            List<AuthorDto> authors = new ArrayList<>();
            authors.add(new AuthorDto());
            book.setAuthors(authors);
            model.addAttribute("book", book);
        }
        return "admin/add-update-book";
    }

    @PostMapping("/add/addAuthor")
    public String addAuthorToForm(@ModelAttribute("book") BookDto book, Model model, RedirectAttributes redirectAttr) {
        book.getAuthors().add(new AuthorDto());
        redirectAttr.addFlashAttribute("book", book);
        return "redirect:/admin/books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") @Valid BookDto book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/add-update-book";

        bookService.save(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/update")
    public String getUpdateBookForm(@RequestParam("bookId") Long id, Model model) {
        BookDto book = bookService.findById(id);
        model.addAttribute("book", book);
        return "admin/add-update-book";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute(name = "book") BookDto book, BindingResult bindingResult) {
        bookService.save(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") Long id) {
        bookService.deleteById(id);
        return "redirect:/admin/books";
    }

}