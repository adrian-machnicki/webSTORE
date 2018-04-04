package com.machnickiadrian.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for not found book, resulting in error 404 not found.
 * 
 * @author Adrian Machnicki
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book was not found")
public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Long bookId;

	public BookNotFoundException(Long id) {
		super("BookNotFoundException with id=" + id);
		this.bookId = id;
	}

	public Long getBookId() {
		return bookId;
	}

}