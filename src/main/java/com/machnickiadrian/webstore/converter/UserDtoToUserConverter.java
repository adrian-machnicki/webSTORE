package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.Role;
import com.machnickiadrian.webstore.entity.User;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Adrian Machnicki
 */
@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    private final RoleDtoToRoleConverter roleDtoToRoleConverter;
    private final OrderDtoToOrderConverter orderDtoToOrderConverter;
    private final UserDetailsDtoToUserDetailsConverter userDetailsDtoToUserDetailsConverter;

    @Autowired
    public UserDtoToUserConverter(RoleDtoToRoleConverter roleDtoToRoleConverter,
                                  OrderDtoToOrderConverter orderDtoToOrderConverter,
                                  UserDetailsDtoToUserDetailsConverter userDetailsDtoToUserDetailsConverter) {
        this.roleDtoToRoleConverter = roleDtoToRoleConverter;
        this.orderDtoToOrderConverter = orderDtoToOrderConverter;
        this.userDetailsDtoToUserDetailsConverter = userDetailsDtoToUserDetailsConverter;
    }

    @Synchronized
    @Override
    public User convert(UserDto source) {
        if (source == null)
            return null;

        final User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setFirstName(source.getFirstName());
        user.setSecondName(source.getSecondName());
        user.setLastName(source.getLastName());
        user.setEmail(source.getEmail());

        List<Role> roles = source.getRoles().stream()
                .map(roleDtoToRoleConverter::convert)
                .collect(Collectors.toList());
        user.setRoles(roles);

        user.setUserDetails(userDetailsDtoToUserDetailsConverter.convert(source.getUserDetails()));
        user.setEnabled(source.getEnabled());

        return user;
    }

}
