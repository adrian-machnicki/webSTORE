package com.machnickiadrian.webstore.dto;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Adrian Machnicki
 */
public class BookDto {

    private Long id;

    @NotBlank(message = "Title can not be empty")
    private String title;

    @Digits(integer = 3, fraction = 2)
    private double price;

    @Valid
    private List<AuthorDto> authors;

    @Valid
    private BookDetailsDto details;

    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    public BookDetailsDto getDetails() {
        return details;
    }

    public void setDetails(BookDetailsDto details) {
        this.details = details;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
