package com.machnickiadrian.webstore.model;

import com.machnickiadrian.webstore.entity.Book;

/**
 * Object representing one record/row in the shopping cart.
 * 
 * @author Adrian Machnicki
 *
 */
public class CartRecord {

	private Book book;
	private int quantity;

	public CartRecord() {
		this.quantity = 0;
	}

	public void updateQuantity(int quantity) {
		this.quantity += quantity;
	}

	public double getAmount() {
		return this.quantity * this.book.getPrice();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return String.format("<book=%s, quantity=%s>", book, quantity);
	}
}