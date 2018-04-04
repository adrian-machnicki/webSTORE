package com.machnickiadrian.webstore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Entity representing user role. They are used for security reasons.
 * 
 * @author adrian.machnicki
 *
 */
@Entity
@Table(name = "authorities")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 4, max = 25)
	private String username;

	@NotBlank
	@Size(min = 4, max = 20)
	private String authority;

	public Role() {
	}

	public Role(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return String.format("Role [id=%s, username=%s, authority=%s]", id, username, authority);
	}

}