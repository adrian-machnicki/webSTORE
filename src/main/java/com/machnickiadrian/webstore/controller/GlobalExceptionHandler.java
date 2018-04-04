package com.machnickiadrian.webstore.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.machnickiadrian.webstore.exception.BookNotFoundException;

/**
 * 
 * @author Adrian Machnicki
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public String handleBookNotFoundException(BookNotFoundException e, Model model) {
		model.addAttribute("bookId", e.getBookId());	
		return "error/book-not-found";
	}
}