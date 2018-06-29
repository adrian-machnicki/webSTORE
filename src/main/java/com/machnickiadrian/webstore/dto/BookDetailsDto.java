package com.machnickiadrian.webstore.dto;

import javax.validation.constraints.Size;

/**
 * @author Adrian Machnicki
 */
public class BookDetailsDto {

    private Long id;
    private int pages;

    @Size(max = 4096)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookDetailsDto{");
        sb.append("id=").append(id);
        sb.append(", pages=").append(pages);
        sb.append('}');
        return sb.toString();
    }
}
