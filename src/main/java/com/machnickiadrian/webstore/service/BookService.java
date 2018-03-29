package com.machnickiadrian.webstore.service;

import java.util.List;

import com.machnickiadrian.webstore.entity.Book;

public interface BookService {
	
	Book findById(Long id);
	
	List<Book> findAll();

	void save(Book book);

	void deleteById(Long id);

	List<Book> search(String phrase);

}
