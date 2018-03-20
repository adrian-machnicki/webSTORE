package com.machnickiadrian.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	@GetMapping("/navbar")
	public String getNavbar() {
		
		return "fragments/navbar";
	}
	
	@GetMapping("/admin-sidebar")
	public String getAdminSidebar() {
		
		return "fragments/admin-sidebar";
	}

}