package com.machnickiadrian.webstore.dto;

import com.machnickiadrian.webstore.validation.PasswordMatches;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object representing an user, used for processing registering
 * new user with register-form.
 *
 * @author Adrian Machnicki
 */
@Getter
@Setter
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRegisterDto{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}