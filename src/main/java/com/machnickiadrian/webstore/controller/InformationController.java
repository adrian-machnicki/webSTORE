package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.enums.NavbarTab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Adrian Machnicki
 */
@Controller
@RequestMapping("/information")
public class InformationController {

    private static final String NAVBAR_TAB = "navbarTab";

    @GetMapping
    public String getInformation(Model model) {
        model.addAttribute(NAVBAR_TAB, NavbarTab.HELP);
        return "information/information";
    }

}