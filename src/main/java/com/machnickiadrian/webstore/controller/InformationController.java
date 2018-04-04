package com.machnickiadrian.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machnickiadrian.webstore.enums.NavbarTab;

/**
 * 
 * @author Adrian Machnicki
 *
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