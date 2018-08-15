package com.machnickiadrian.webstore.model;

import com.machnickiadrian.webstore.dto.BookDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Object representing one record/row in the shopping cart.
 * 
 * @author Adrian Machnicki
 *
 */
@Setter
@Getter
public class CartRecord {

	private BookDto book;
	private int quantity;

	public CartRecord() {
		this.quantity = 0;
	}

	public void updateQuantity(int quantity) {
		this.quantity += quantity;
	}

	public double getAmount() {
		BigDecimal bookQuantity = BigDecimal.valueOf(this.quantity);
		BigDecimal bookPrice = BigDecimal.valueOf(this.book.getPrice());
		BigDecimal amount = bookQuantity.multiply(bookPrice).setScale(2, RoundingMode.HALF_UP);
		return amount.doubleValue();
	}

	@Override
	public String toString() {
		return String.format("<book=%s, quantity=%s>", book, quantity);
	}
}