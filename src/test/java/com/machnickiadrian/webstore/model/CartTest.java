package com.machnickiadrian.webstore.model;

import com.machnickiadrian.webstore.dto.BookDto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Adrian Machnicki
 */
public class CartTest {

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    public void addBook_ShouldAddBookToCart() {
        BookDto book = createSampleBook();
        int amount = 3;

        // when
        cart.addBook(book, amount);

        // then
        boolean containsBook = false;
        List<CartRecord> books = cart.getBooks();
        for (CartRecord record : books)
            if (Long.valueOf(1).equals(record.getBook().getId()))
                containsBook = true;
        assertTrue("Cart should contain cart record containing added book.", containsBook);
    }

    @Test
    public void removeBook_ShouldRemoveBook() {
        // given
        BookDto book = createSampleBook();
        cart.addBook(book, 2);

        // when
        cart.removeBook(book);

        // then
        boolean containsBook = false;
        List<CartRecord> books = cart.getBooks();
        for (CartRecord record : books)
            if (Long.valueOf(1).equals(record.getBook().getId()))
                containsBook = true;
        assertFalse("Cart should not contain cart record containing removed book", containsBook);
    }

    @Test
    public void updateQuantity_ShouldUpdateQuantity() {
        // given
        BookDto book = createSampleBook();
        int bookQuantity = 5;
        cart.addBook(book, bookQuantity);

        // when
        cart.updateQuantity(book, 3);

        // then
        List<CartRecord> books = cart.getBooks();
        for (CartRecord record : books)
            if (Long.valueOf(1).equals(record.getBook().getId()))
                bookQuantity = record.getQuantity();

        assertEquals("Cart should contain now added book in quantity equal 3", 3, bookQuantity);
    }

    @Test
    public void empty_ShouldEmptyCart() {
        // given
        BookDto book = createSampleBook();
        cart.addBook(book, 5);

        // when
        cart.empty();

        // then
        double expectedFinalPrice = 0;
        assertTrue("Cart should be empty", cart.getBooks().isEmpty());
        assertTrue("List containing ids of added books should be empty", cart.getAddedBooksIds().isEmpty());
        assertEquals("Final price should be equal 0", expectedFinalPrice, cart.getFinalPrice(), 0);


    }

    private BookDto createSampleBook() {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setTitle("Spring in action");
        book.setPrice(49.99);
        return book;
    }

}