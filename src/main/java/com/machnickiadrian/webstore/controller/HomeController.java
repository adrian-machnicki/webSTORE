package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.enums.NavbarTab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Adrian Machnicki
 */
@Controller
public class HomeController {

    private static final String NAVBAR_TAB = "navbarTab";

    @GetMapping({"/", "/home"})
    public String getHome(Model model) {
        model.addAttribute(NAVBAR_TAB, NavbarTab.HOME);
        return "home";
    }
}