package com.machnickiadrian.webstore.book;

import com.machnickiadrian.webstore.book.dto.AuthorDto;
import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.book.entity.Book;
import com.machnickiadrian.webstore.book.exception.BookNotFoundException;
import com.machnickiadrian.webstore.book.mapper.BookDtoToBookMapper;
import com.machnickiadrian.webstore.book.mapper.BookToBookDtoMapper;
import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.order.dto.OrderRecordDto;
import com.machnickiadrian.webstore.order.entity.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Implementation of <code>BookService</code> interface. It's methods are
 * transactional and secured with Spring Security and JSR-250.
 *
 * @author Adrian Machnicki
 */
@Service
public class BookServiceImpl implements BookService {

    private static final String ADMIN = "ADMIN";
    private final BookRepository bookRepository;
    private final OrderService orderService;
    private final BookToBookDtoMapper bookToBookDtoMapper;
    private final BookDtoToBookMapper bookDtoToBookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, OrderService orderService,
                           BookToBookDtoMapper bookToBookDtoMapper, BookDtoToBookMapper bookDtoToBookMapper) {
        this.bookRepository = bookRepository;
        this.orderService = orderService;
        this.bookToBookDtoMapper = bookToBookDtoMapper;
        this.bookDtoToBookMapper = bookDtoToBookMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookToBookDtoMapper.convert(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookToBookDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size)).getContent().stream()
                .map(bookToBookDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAllBoughtByUsername(String username) {
        List<OrderDto> userOrders = orderService.findAllByUsername(username);
        List<BookDto> books = new ArrayList<>();
        BookDto book;
        for (OrderDto order : userOrders) {
            for (OrderRecordDto record : order.getRecords()) {
                book = record.getBook();
                if (!books.contains(book))
                    books.add(book);
            }
        }

        return books;
    }

    @RolesAllowed(ADMIN)
    @Transactional
    @Override
    public void save(BookDto book) {
        Book bookToSave = bookDtoToBookMapper.convert(book);
        bookRepository.save(bookToSave);
    }

    @RolesAllowed(ADMIN)
    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> search(String phrase) {
        phrase = phrase.trim();
        String[] keywords = phrase.split(" ");
        List<BookDto> allBooks = bookRepository.findAll().stream()
                .map(bookToBookDtoMapper::convert)
                .collect(Collectors.toList());
        List<BookDto> foundBooks = new ArrayList<>();

        for (BookDto book : allBooks) {
            if (book.getTitle().toLowerCase().contains(phrase.toLowerCase())) {
                foundBooks.add(book);
                continue;
            }

            StringJoiner bookData = new StringJoiner(" ");
            bookData.add(book.getTitle());
            for (AuthorDto author : book.getAuthors()) {
                bookData.add(author.getFirstName());
                bookData.add(author.getLastName());
            }

            boolean containsAll = true;
            for (String keyword : keywords) {
                if (!bookData.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    containsAll = false;
                    break;
                }
            }
            if (containsAll)
                foundBooks.add(book);
        }

        return foundBooks;
    }

    @Transactional(readOnly = true)
    @Override
    public int countAll() {
        return Math.toIntExact(bookRepository.count());
    }

}