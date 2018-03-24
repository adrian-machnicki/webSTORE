package com.machnickiadrian.webstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.model.CartRecord;

@Entity
@Table(name = "orders")
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double amount;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private List<OrderRecord> records;

	public void addItemsFromCart(Cart cart) {
		records = new ArrayList<>();

		OrderRecord orderRecord;
		double orderAmount = 0;
		
		for (CartRecord cartRecord : cart.getBooks()) {
			orderAmount += cartRecord.getAmount();
			
			orderRecord = new OrderRecord();
			orderRecord.setQuantity(cartRecord.getQuantity());
			orderRecord.setPrice(cartRecord.getBook().getPrice());
			orderRecord.setAmount(cartRecord.getAmount());
			orderRecord.setBook(cartRecord.getBook());
			records.add(orderRecord);
		}
		
		this.amount = orderAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<OrderRecord> getRecords() {
		return records;
	}

	public void setRecords(List<OrderRecord> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return String.format("Order [id=%s]", id);
	}

}