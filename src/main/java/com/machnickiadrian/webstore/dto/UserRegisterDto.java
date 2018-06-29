package com.machnickiadrian.webstore.dto;

import com.machnickiadrian.webstore.validation.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object representing an user, used for processing registering
 * new user with register-form.
 *
 * @author Adrian Machnicki
 */
@PasswordMatches
public class UserRegisterDto {

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
        final StringBuilder sb = new StringBuilder("UserRegisterDto{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}