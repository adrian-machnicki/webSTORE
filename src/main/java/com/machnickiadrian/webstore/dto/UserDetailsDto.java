package com.machnickiadrian.webstore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Adrian Machnicki
 */
@Getter
@Setter
public class UserDetailsDto {

    private Long id;
    private String address;
    private String city;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetailsDto{");
        sb.append("id=").append(id);
        sb.append(", address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
