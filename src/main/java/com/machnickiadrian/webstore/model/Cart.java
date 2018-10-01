package com.machnickiadrian.webstore.model;

import com.machnickiadrian.webstore.book.dto.BookDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Object representing shop cart, to which user can add books before buying
 * them.
 *
 * @author Adrian Machnicki
 */
@Setter
@Getter
@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<CartRecord> books;
    private Set<Long> addedBooksIds;
    private double finalPrice;

    public Cart() {
        this.books = new ArrayList<>();
        this.addedBooksIds = new HashSet<>();
        this.finalPrice = 0;
    }

    public void addBook(BookDto book, int amount) {
        CartRecord record = getRecordByBook(book);

        if (record == null) {
            record = new CartRecord();
            record.setBook(book);
            record.setQuantity(amount);
            books.add(record);
        } else {
            record.updateQuantity(amount);
        }
        addedBooksIds.add(book.getId());

        updatePrice();
    }

    public void removeBook(BookDto book) {
        CartRecord record = getRecordByBook(book);
        if (record != null) {
            books.remove(record);
        }
        addedBooksIds.remove(book.getId());
        updatePrice();
    }

    public void updateQuantity(BookDto book, int quantity) {
        CartRecord record = getRecordByBook(book);
        if (record != null)
            record.setQuantity(quantity);
        updatePrice();
    }

    public void empty() {
        this.finalPrice = 0;
        this.books = new ArrayList<>();
        this.addedBooksIds = new HashSet<>();
    }

    private void updatePrice() {
        double price = 0;

        for (CartRecord record : books)
            price += record.getAmount();

        this.finalPrice = price;
    }

    private CartRecord getRecordByBook(BookDto book) {
        for (CartRecord record : books) {
            if (record.getBook().getId() == book.getId())
                return record;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("Cart [amount=%d, %s]", books.size(), Arrays.toString(books.toArray()));
    }

}