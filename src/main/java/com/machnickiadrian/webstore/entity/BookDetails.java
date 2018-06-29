package com.machnickiadrian.webstore.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity representing additional book's details.
 *
 * @author Adrian Machnicki
 */
@Entity
@Table(name = "book_details")
public class BookDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pages;
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
        final StringBuilder sb = new StringBuilder("BookDetails{");
        sb.append("id=").append(id);
        sb.append(", pages=").append(pages);
        sb.append('}');
        return sb.toString();
    }
}