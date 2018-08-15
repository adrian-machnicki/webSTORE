package com.machnickiadrian.webstore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
public class AuthorDto {

    private Long id;

    @NotBlank(message = "First name can not be empty")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last name can not be empty")
    @Size(max = 50)
    private String lastName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorDto{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
