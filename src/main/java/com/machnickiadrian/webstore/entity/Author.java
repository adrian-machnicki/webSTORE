package com.machnickiadrian.webstore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Entity representing an book author.
 * 
 * @author Adrian Machnicki
 *
 */
@Entity
@Table(name="authors")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	@NotBlank(message="First name can not be empty")
	@Size(max = 50)
	private String firstName;

	@Column(name = "last_name")
	@NotBlank(message="Last name can not be empty")
	@Size(max = 50)
	private String lastName;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="books_authors",
				joinColumns = @JoinColumn(name = "author_id"),
				inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return String.format("%s =%s", firstName, lastName);
	}
	
}