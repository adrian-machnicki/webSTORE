package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.UserRegisterDto;
import com.machnickiadrian.webstore.entity.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class UserRegisterDtoToUserConverter implements Converter<UserRegisterDto, User> {

    @Synchronized
    @Override
    public User convert(UserRegisterDto source) {
        if (source == null)
            return null;

        final User user = new User();
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setEmail(source.getEmail());
        return user;
    }

}
