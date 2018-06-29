package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.RoleDto;
import com.machnickiadrian.webstore.dto.UserDto;
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
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    private final RoleToRoleDtoConverter roleToRoleDtoConverter;
    private final UserDetailsToUserDetailsDtoConverter userDetailsToUserDetailsDtoConverter;

    @Autowired
    public UserToUserDtoConverter(RoleToRoleDtoConverter roleToRoleDtoConverter,
                                  UserDetailsToUserDetailsDtoConverter userDetailsToUserDetailsDtoConverter) {
        this.roleToRoleDtoConverter = roleToRoleDtoConverter;
        this.userDetailsToUserDetailsDtoConverter = userDetailsToUserDetailsDtoConverter;
    }

    @Synchronized
    @Override
    public UserDto convert(User source) {
        if (source == null)
            return null;

        final UserDto dto = new UserDto();
        dto.setId(source.getId());
        dto.setUsername(source.getUsername());
        dto.setFirstName(source.getFirstName());
        dto.setSecondName(source.getSecondName());
        dto.setLastName(source.getLastName());
        dto.setEmail(source.getEmail());

        List<RoleDto> roleDtos = source.getRoles().stream()
                .map(roleToRoleDtoConverter::convert)
                .collect(Collectors.toList());
        dto.setRoles(roleDtos);

        dto.setUserDetails(userDetailsToUserDetailsDtoConverter.convert(source.getUserDetails()));
        dto.setEnabled(source.getEnabled());

        return dto;
    }

}
