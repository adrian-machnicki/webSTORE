package com.machnickiadrian.webstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.machnickiadrian.webstore.entity.User;

/**
 * Data Transfer Object representing an user profile, used during personal data updating.
 * 
 * @author Adrian Machnicki
 *
 */
public class UserProfileDto {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 50)
	private String firstName;

	private String secondName;

	@NotBlank
	@Size(min = 4, max = 70)
	private String lastName;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(max = 255)
	private String address;

	@NotBlank
	@Size(max = 50)
	private String city;
	
	public UserProfileDto() {}

	public UserProfileDto(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.secondName = user.getSecondName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		if (user.getUserDetails() != null) {
			this.address = user.getUserDetails().getAddress();
			this.city = user.getUserDetails().getCity();
		}
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

}
