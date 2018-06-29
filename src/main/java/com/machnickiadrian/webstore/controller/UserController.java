package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.dto.*;
import com.machnickiadrian.webstore.enums.NavbarTab;
import com.machnickiadrian.webstore.exception.EmailExistsException;
import com.machnickiadrian.webstore.exception.UsernameExistsException;
import com.machnickiadrian.webstore.service.BookService;
import com.machnickiadrian.webstore.service.OrderService;
import com.machnickiadrian.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author Adrian Machnicki
 */
@Controller
public class UserController {

    private static final String NAVBAR_TAB = "navbarTab";
    private final UserService userService;
    private final BookService bookService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, BookService bookService, OrderService orderService) {
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserRegisterDto userDto = new UserRegisterDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") @Valid UserRegisterDto userDto, BindingResult bindingResult) {

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
        UserDto user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute(NAVBAR_TAB, NavbarTab.PROFILE);
        return "user/user";
    }

    @GetMapping("/user/profile")
    public String getProfile(Principal principal, Model model) {
        UserDto user = userService.findByUsername(principal.getName());
        model.addAttribute("user", new UserProfileDto(user));
        return "user/profile";
    }

    @PostMapping("/user/profile")
    public String saveProfile(@ModelAttribute("user") @Valid UserProfileDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/profile";

        userService.save(user);
        return "redirect:/user/profile";
    }

    @GetMapping("/user/books")
    public String getUsersBooks(Model model, Principal principal) {
        List<BookDto> books = bookService.findAllBoughtByUsername(principal.getName());
        model.addAttribute("books", books);
        return "user/books";
    }

    @GetMapping("/user/orders")
    public String getUsersOrders(Model model, Principal principal) {
        List<OrderDto> orders = orderService.findAllByUsername(principal.getName());
        model.addAttribute("orders", orders);
        return "user/orders";
    }

}