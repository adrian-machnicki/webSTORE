package com.machnickiadrian.webstore.dto;

import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.model.CartRecord;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
public class OrderDto {

    private Long id;
    private double amount;
    private List<OrderRecordDto> records;

    private UserDto user;
    private ShippingDetailsDto shippingDetails;
    private Date date;
    private boolean paid = false;
    private boolean sent = false;

    public void addItemsFromCart(Cart cart) {
        records = new ArrayList<>();

        OrderRecordDto orderRecord;
        BigDecimal orderAmount = BigDecimal.ZERO;
        BigDecimal recordAmount;

        for (CartRecord cartRecord : cart.getBooks()) {
            recordAmount = BigDecimal.valueOf(cartRecord.getAmount());
            orderAmount = orderAmount.add(recordAmount);

            orderRecord = new OrderRecordDto();
            orderRecord.setQuantity(cartRecord.getQuantity());
            orderRecord.setPrice(cartRecord.getBook().getPrice());
            orderRecord.setAmount(cartRecord.getAmount());
            orderRecord.setBook(cartRecord.getBook());
            records.add(orderRecord);
        }

        this.amount = orderAmount.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void fillUsersShippingDetails(UserDto user) {
        if (shippingDetails == null)
            shippingDetails = new ShippingDetailsDto();

        shippingDetails.fillFromUserData(user);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDto{");
        sb.append("id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append(", date=").append(date);
        sb.append(", paid=").append(paid);
        sb.append(", sent=").append(sent);
        sb.append('}');
        return sb.toString();
    }
}
