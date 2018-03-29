package com.machnickiadrian.webstore.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.machnickiadrian.webstore.entity.Book;

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
		addedBooksIds.add(book.getId());

		updatePrice();
	}

	public void removeBook(Book book) {
		CartRecord record = getRecordByBook(book);
		if (record != null) {
			books.remove(record);
		}
		addedBooksIds.remove(book.getId());

		updatePrice();
	}

	public void updateQuantity(Book book, int quantity) {
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

	private CartRecord getRecordByBook(Book book) {
		for (CartRecord record : books) {
			if (record.getBook().getId() == book.getId())
				return record;
		}
		return null;
	}

	public List<CartRecord> getBooks() {
		return books;
	}

	public Set<Long> getAddedBooksIds() {
		return addedBooksIds;
	}

	public void setAddedBooksIds(Set<Long> addedBooksIds) {
		this.addedBooksIds = addedBooksIds;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	@Override
	public String toString() {
		return String.format("Cart [amount=%d, %s]", books.size(), Arrays.toString(books.toArray()));
	}

}