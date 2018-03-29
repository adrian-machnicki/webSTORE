package com.machnickiadrian.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machnickiadrian.webstore.enums.AdminTab;
import com.machnickiadrian.webstore.enums.NavbarTab;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final String ADMIN_TAB = "adminTab";
	private static final String NAVBAR_TAB = "navbarTab";
	
	@GetMapping
	public String getAdminPanel(Model model) {
		model.addAttribute(ADMIN_TAB, AdminTab.DASHBOARD);
		model.addAttribute(NAVBAR_TAB, NavbarTab.ADMIN);
		
		return "admin/admin";
	}
	
}