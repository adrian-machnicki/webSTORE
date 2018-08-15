package com.machnickiadrian.webstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
public class BookDetailsDto {

    private Long id;
    private int pages;

    @Size(max = 4096)
    private String description;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookDetailsDto{");
        sb.append("id=").append(id);
        sb.append(", pages=").append(pages);
        sb.append('}');
        return sb.toString();
    }
}
