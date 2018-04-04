package com.machnickiadrian.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.machnickiadrian.webstore.entity.User;
import com.machnickiadrian.webstore.enums.AdminTab;
import com.machnickiadrian.webstore.service.UserService;

/**
 * 
 * @author Adrian Machnicki
 *
 */
@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {
	
	private static final String ADMIN_TAB = "adminTab";
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getUsersManagement(Model model, @RequestParam(defaultValue = "") String search) {
		List<User> users;
		
		if(search.equals(""))
			users = userService.findAll();
		else
			users = userService.search(search);
			
		model.addAttribute("users", users);
		model.addAttribute(ADMIN_TAB, AdminTab.USERS);
		
		return "admin/users";
	}
	
	@GetMapping("/block-unblock")
	public String blockOrUnblockUser(@RequestParam Long id) {
		userService.setEnabledDisabled(id);
		
		return "redirect:/admin/users";
	}
	
	@GetMapping("/remove")
	public String removeUser(@RequestParam Long id) {
		userService.deleteById(id);
		
		return "redirect:/admin/users";
	}

}