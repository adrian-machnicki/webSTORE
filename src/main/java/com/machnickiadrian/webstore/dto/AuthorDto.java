package com.machnickiadrian.webstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Adrian Machnicki
 */
public class AuthorDto {

    private Long id;

    @NotBlank(message = "First name can not be empty")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last name can not be empty")
    @Size(max = 50)
    private String lastName;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
