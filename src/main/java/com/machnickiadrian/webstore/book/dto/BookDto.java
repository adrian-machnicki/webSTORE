package com.machnickiadrian.webstore.book.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
