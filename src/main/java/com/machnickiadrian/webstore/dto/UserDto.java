package com.machnickiadrian.webstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 25)
    private String username;

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
    private List<RoleDto> roles;
    private UserDetailsDto userDetails;
    private int enabled;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
