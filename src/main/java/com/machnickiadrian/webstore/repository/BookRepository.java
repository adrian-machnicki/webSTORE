package com.machnickiadrian.webstore.repository;

import java.util.ArrayList;

import org.springframework.data.repository.Repository;

import com.machnickiadrian.webstore.entity.Book;

public interface BookRepository extends Repository<Book, Long> {
	
	Book findById(Long id);
	
	ArrayList<Book> findAll();

	void save(Book book);

	void deleteById(Long id);

}