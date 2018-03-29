package com.machnickiadrian.webstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machnickiadrian.webstore.entity.Author;
import com.machnickiadrian.webstore.entity.Book;
import com.machnickiadrian.webstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	private static final String ADMIN = "ADMIN";

	@Autowired
	private BookRepository repository;

	@Transactional(readOnly = true)
	@Override
	public Book findById(Long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> findAll() {
		return repository.findAll();
	}

	@RolesAllowed(ADMIN)
	@Transactional
	@Override
	public void save(Book book) {
		repository.save(book);
	}

	@RolesAllowed(ADMIN)
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Book> search(String phrase) {
		phrase = phrase.trim();
		String[] keywords = phrase.split(" ");
		List<Book> allBooks = repository.findAll();
		List<Book> foundBooks = new ArrayList<>();
		
		for (Book book : allBooks) {
			if (book.getTitle().toLowerCase().contains(phrase.toLowerCase())) {
				foundBooks.add(book);
				continue;
			}
			
			StringJoiner bookData = new StringJoiner(" ");
			bookData.add(book.getTitle());
			for (Author author : book.getAuthors()) {
				bookData.add(author.getFirstName());
				bookData.add(author.getLastName());
			}
			
			boolean containsAll = true; 
			for (String keyword : keywords){
			    if (!bookData.toString().toLowerCase().contains(keyword.toLowerCase())){
			       containsAll = false;
			       break;
			    }
			}
			if (containsAll)
				foundBooks.add(book);			
		}
		
		return foundBooks;
	}

}