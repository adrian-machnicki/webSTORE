package com.machnickiadrian.webstore.repository;

import java.util.ArrayList;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.machnickiadrian.webstore.entity.Book;

/**
 * Repository class for <code>Book</code> objects.
 * 
 * @author Adrian Machnicki
 *
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	ArrayList<Book> findAll();

	void deleteById(Long id);

}