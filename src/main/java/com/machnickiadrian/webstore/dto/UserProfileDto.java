package com.machnickiadrian.webstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object representing an user profile, used during personal data updating.
 *
 * @author Adrian Machnicki
 */
@Getter
@Setter
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

    public UserProfileDto() {
    }

    public UserProfileDto(UserDto user) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserProfileDto{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
