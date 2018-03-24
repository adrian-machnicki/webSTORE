package com.machnickiadrian.webstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.exception.EmailExistsException;
import com.machnickiadrian.webstore.exception.UsernameExistsException;
import com.machnickiadrian.webstore.service.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);

		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			try {
				service.registerNewUser(userDto);
			} catch (EmailExistsException e) {
				bindingResult.rejectValue("email", "User with this email already exists.");
			} catch (UsernameExistsException e) {
				bindingResult.rejectValue("username", "User with ths username already exists.");
			}
		}

		if (bindingResult.hasErrors()) {

			for (ObjectError error : bindingResult.getAllErrors()) {
				System.out.println("\n[ERROR] " + error.getObjectName() + " |" + error.getCode() + " | "
						+ error.getClass() + " | " + error.getDefaultMessage());

			}

			return "register";
		} else {
			return "redirect:/home";
		}
	}
}