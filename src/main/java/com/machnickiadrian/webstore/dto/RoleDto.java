package com.machnickiadrian.webstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Adrian Machnicki
 */
public class RoleDto {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 25)
    private String username;

    @NotBlank
    @Size(min = 4, max = 20)
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleDto{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", authority='").append(authority).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
