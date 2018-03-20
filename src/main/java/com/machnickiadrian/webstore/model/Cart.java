package com.machnickiadrian.webstore.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.machnickiadrian.webstore.entity.Book;

@Component
@Scope(scopeName = "session",
	proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

	private List<CartRecord> books;
	private double finalPrice;

	public Cart() {
		this.books = new ArrayList<>();
	}

	public void addBook(Book book, int amount) {
		CartRecord record = getRecordByBook(book);

		if (record == null) {
			record = new CartRecord();
			record.setBook(book);
			record.setQuantity(amount);
			books.add(record);
		} else {
			record.updateQuantity(amount);
		}

		updatePrice();
	}
	
	public void removeBook(Book book) {
		CartRecord record = getRecordByBook(book);
		if (record != null) {
			books.remove(record);
		}
		
		updatePrice();
	}

	private void updatePrice() {
		double price = 0;

		for (CartRecord record : books)
			price += record.getAmount();

		this.finalPrice = price;
	}

	private CartRecord getRecordByBook(Book book) {
		for (CartRecord record : books) {
			if (record.getBook().getId() == book.getId())
				return record;
		}

		return null;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public List<CartRecord> getBooks() {
		return books;
	}

	@Override
	public String toString() {
		return String.format("Cart [amount=%d, %s]", books.size(), Arrays.toString(books.toArray()));
	}

}