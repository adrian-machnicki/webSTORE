package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.dto.BookDto;

import java.util.List;

/**
 * Service interface for <code>Book</code> objects manipulation.
 *
 * @author Adrian Machnicki
 */
public interface BookService {

    BookDto findById(Long id);

    List<BookDto> findAll();

    List<BookDto> findAll(int page, int size);

    List<BookDto> findAllBoughtByUsername(String username);

    void save(BookDto book);

    void deleteById(Long id);

    List<BookDto> search(String phrase);

    int countAll();

}