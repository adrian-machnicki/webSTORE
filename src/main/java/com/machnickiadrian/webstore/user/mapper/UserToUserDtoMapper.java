package com.machnickiadrian.webstore.user.mapper;

import com.machnickiadrian.webstore.user.dto.RoleDto;
import com.machnickiadrian.webstore.user.dto.UserDto;
import com.machnickiadrian.webstore.user.entity.User;
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
public class UserToUserDtoMapper implements Converter<User, UserDto> {

    private final RoleToRoleDtoMapper roleToRoleDtoMapper;
    private final UserDetailsToUserDetailsDtoMapper userDetailsToUserDetailsDtoMapper;

    @Autowired
    public UserToUserDtoMapper(RoleToRoleDtoMapper roleToRoleDtoMapper,
                               UserDetailsToUserDetailsDtoMapper userDetailsToUserDetailsDtoMapper) {
        this.roleToRoleDtoMapper = roleToRoleDtoMapper;
        this.userDetailsToUserDetailsDtoMapper = userDetailsToUserDetailsDtoMapper;
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
                .map(roleToRoleDtoMapper::convert)
                .collect(Collectors.toList());
        dto.setRoles(roleDtos);

        dto.setUserDetails(userDetailsToUserDetailsDtoMapper.convert(source.getUserDetails()));
        dto.setEnabled(source.getEnabled());

        return dto;
    }

}
