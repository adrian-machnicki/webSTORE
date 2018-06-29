package com.machnickiadrian.webstore.dto;

/**
 * @author Adrian Machnicki
 */
public class OrderRecordDto {

    private Long id;
    private int quantity;
    private double price;
    private double amount;
    private BookDto book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

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
