package com.machnickiadrian.webstore.order.dto;

import com.machnickiadrian.webstore.user.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
public class ShippingDetailsDto {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 25)
    private String firstName;
    private String secondName;

    @NotBlank
    @Size(min = 4, max = 50)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    public void fillFromUserData(UserDto user) {
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.address = user.getUserDetails().getAddress();
        this.city = user.getUserDetails().getCity();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShippingDetailsDto{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
