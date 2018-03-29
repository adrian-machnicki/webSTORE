package com.machnickiadrian.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book was not found	")
public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
}