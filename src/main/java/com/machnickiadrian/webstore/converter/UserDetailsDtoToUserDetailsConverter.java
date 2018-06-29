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
public class UserDetailsDtoToUserDetailsConverter implements Converter<UserDetailsDto, UserDetails> {

    @Synchronized
    @Override
    public UserDetails convert(UserDetailsDto source) {
        if (source == null)
            return null;

        final UserDetails userDetails = new UserDetails();
        userDetails.setId(source.getId());
        userDetails.setAddress(source.getAddress());
        userDetails.setCity(source.getCity());

        return userDetails;
    }

}
