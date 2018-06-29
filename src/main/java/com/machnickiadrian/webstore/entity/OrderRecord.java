package com.machnickiadrian.webstore.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity representing one record/row in the order.
 *
 * @author Adrian Machnicki
 */
@Entity
@Table(name = "order_records")
public class OrderRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;
    private double amount;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderRecord{");
        sb.append("id=").append(id);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}