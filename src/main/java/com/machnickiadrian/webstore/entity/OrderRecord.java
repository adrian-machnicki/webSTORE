package com.machnickiadrian.webstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity representing one record/row in the order.
 *
 * @author Adrian Machnicki
 */
@Getter
@Setter
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