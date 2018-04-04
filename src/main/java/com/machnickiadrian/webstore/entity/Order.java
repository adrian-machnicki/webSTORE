package com.machnickiadrian.webstore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.model.CartRecord;

/**
 * Entity representing an order.
 * 
 * @author Adrian Machnicki
 *
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double amount;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private List<OrderRecord> records;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_details_id")
	private ShippingDetails shippingDetails;
	
	private Date date;

	private boolean paid = false;
	private boolean sent = false;

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
	
	public void fillUsersShippingDetails(User user) {
		if (shippingDetails == null)
			shippingDetails = new ShippingDetails();
		
		shippingDetails.fillFromUserData(user);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ShippingDetails getShippingDetails() {
		return shippingDetails;
	}

	public void setShippingDetails(ShippingDetails shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	@Override
	public String toString() {
		return String.format("Order [id=%s, amount=%s, records=%s, user=%s, shippingDetails=%s, date=%s, paid=%s, sent=%s]", id,
				amount, records, user, shippingDetails, date, paid, sent);
	}

}