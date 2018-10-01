package com.machnickiadrian.webstore.book;

import com.machnickiadrian.webstore.book.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository class for <code>Book</code> objects.
 *
 * @author Adrian Machnicki
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    List<Book> findAll();

    void deleteById(Long id);

}