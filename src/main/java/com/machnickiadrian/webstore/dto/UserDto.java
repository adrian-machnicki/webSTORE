package com.machnickiadrian.webstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.machnickiadrian.webstore.validation.PasswordMatches;

@PasswordMatches
public class UserDto {

	@NotBlank
	@Size(min = 4, max = 25)
	private String username;

	@NotBlank
	@Size(min = 4, max = 25)
	private String password;

	@NotBlank
	private String passwordConfirm;

	@NotBlank
	@Email
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("UserDto [username=%s, password=%s, passwordConfirm=%s, email=%s]", username, password,
				passwordConfirm, email);
	}

}
