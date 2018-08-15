package com.machnickiadrian.webstore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
public class OrderRecordDto {

    private Long id;
    private int quantity;
    private double price;
    private double amount;
    private BookDto book;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderRecordDto{");
        sb.append("id=").append(id);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
