package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.UserDetailsDto;
import com.machnickiadrian.webstore.entity.UserDetails;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class UserDetailsToUserDetailsDtoConverter implements Converter<UserDetails, UserDetailsDto> {

    @Synchronized
    @Override
    public UserDetailsDto convert(UserDetails source) {
        if (source == null)
            return null;

        final UserDetailsDto dto = new UserDetailsDto();
        dto.setId(source.getId());
        dto.setAddress(source.getAddress());
        dto.setCity(source.getCity());

        return dto;
    }

}
