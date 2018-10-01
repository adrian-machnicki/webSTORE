package com.machnickiadrian.webstore.book.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity representing additional book's details.
 *
 * @author Adrian Machnicki
 */
@Getter
@Setter
@Entity
@Table(name = "book_details")
public class BookDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pages;
    private String description;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookDetails{");
        sb.append("id=").append(id);
        sb.append(", pages=").append(pages);
        sb.append('}');
        return sb.toString();
    }
}