package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.book.BookService;
import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.enums.NavbarTab;
import com.machnickiadrian.webstore.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Adrian Machnicki
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private static final String NAVBAR_TAB = "navbarTab";
    private final BookService bookService;
    private Cart cart;

    @Autowired
    public CartController(BookService bookService, Cart cart) {
        this.bookService = bookService;
        this.cart = cart;
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute(NAVBAR_TAB, NavbarTab.CART);
        return "cart/cart";
    }

    @GetMapping("/add")
    public String addToCart(@RequestParam Long bookId, @RequestParam(defaultValue = "1") int amount) {
        BookDto book = bookService.findById(bookId);
        cart.addBook(book, amount);
        return "redirect:/books";
    }

    @GetMapping("/remove")
    public String removeFromCart(@RequestParam Long id) {
        BookDto book = bookService.findById(id);
        cart.removeBook(book);
        return "redirect:/cart";
    }

    @GetMapping("/updateQuantity")
    public String updateBookQuantity(@RequestParam Long bookId, @RequestParam int quantity) {
        BookDto book = bookService.findById(bookId);
        cart.updateQuantity(book, quantity);
        return "redirect:/cart";
    }

}