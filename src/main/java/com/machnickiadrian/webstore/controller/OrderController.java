package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.order.dto.ShippingDetailsDto;
import com.machnickiadrian.webstore.order.entity.OrderService;
import com.machnickiadrian.webstore.user.UserService;
import com.machnickiadrian.webstore.user.dto.UserDto;
import com.machnickiadrian.webstore.user.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Adrian Machnicki
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private Cart cart;
    private OrderDto order;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, Cart cart) {
        this.orderService = orderService;
        this.userService = userService;
        this.cart = cart;
    }

    @GetMapping("/checkout")
    public String getCheckoutForm(Model model, Principal principal) {
        order = new OrderDto();
        order.addItemsFromCart(cart);

        UserDto user = userService.findByUsername(principal.getName());
        model.addAttribute("order", order);
        model.addAttribute("user", new UserProfileDto(user));
        return "order/checkout-user";
    }

    @GetMapping("/checkout/guest")
    public String getCheckoutFormForGuest(Model model) {
        order = new OrderDto();
        order.addItemsFromCart(cart);

        ShippingDetailsDto shippingDetails = new ShippingDetailsDto();
        model.addAttribute("order", order);
        model.addAttribute("shippingDetails", shippingDetails);
        return "order/checkout-guest";
    }

    @PostMapping("/checkout")
    public String placeUserOrder(@ModelAttribute("user") @Valid UserProfileDto userDto,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("order", order);
            return "order/checkout-user";
        }

        UserDto user = userService.findById(userDto.getId());
        order.setUser(user);
        order.fillUsersShippingDetails(user);
        orderService.save(order);
        Long orderId = order.getId();
        order = new OrderDto();
        cart.empty();

        return "redirect:/order/success/" + orderId;
    }

    @PostMapping("/checkout/guest")
    public String placeGuestOrder(@ModelAttribute("shippingDetails") @Valid ShippingDetailsDto shippingDetails,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("order", order);
            return "order/checkout-guest";
        }

        order.setShippingDetails(shippingDetails);
        orderService.save(order);
        Long orderId = order.getId();
        order = new OrderDto();
        cart.empty();

        return "redirect:/order/success/" + orderId;
    }

    @GetMapping("/success/{id}")
    public String confirmOrder(@PathVariable int id, Model model) {
        model.addAttribute("orderId", id);
        return "order/order-confirmation";
    }

}