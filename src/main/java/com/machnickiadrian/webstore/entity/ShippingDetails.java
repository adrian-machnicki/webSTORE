package com.machnickiadrian.webstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shipping_details")
public class ShippingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 4, max = 25)
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "second_name")
	private String secondName;

	@NotBlank
	@Size(min = 4, max = 50)
	@Column(name = "last_name")
	private String lastName;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String address;

	@NotBlank
	private String city;

	@OneToOne(mappedBy = "shippingDetails")
	private Order order;
	
	public void fillFromUserData(User user) {
		this.firstName = user.getFirstName();
		this.secondName = user.getSecondName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.address = user.getUserDetails().getAddress();
		this.city = user.getUserDetails().getCity();	
	}

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

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return String.format("ShippingDetails [firstName=%s, lastName=%s, city=%s, email=%s]", firstName, lastName, city,
				email);
	}

}
