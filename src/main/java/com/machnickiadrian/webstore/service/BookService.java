package com.machnickiadrian.webstore.service;

import java.util.List;

import com.machnickiadrian.webstore.entity.Book;

/**
 * Service interface for <code>Book</code> objects manipulation.
 * 
 * @author Adrian Machnicki
 *
 */
public interface BookService {
	
	Book findById(Long id);
	
	List<Book> findAll();
	
	List<Book> findAll(int page, int size);

	void save(Book book);

	void deleteById(Long id);

	List<Book> search(String phrase);
	
	int countAll();

}