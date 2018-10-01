package com.machnickiadrian.webstore.user.mapper;

import com.machnickiadrian.webstore.order.mapper.OrderDtoToOrderMapper;
import com.machnickiadrian.webstore.user.dto.UserDto;
import com.machnickiadrian.webstore.user.entity.Role;
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
public class UserDtoToUserMapper implements Converter<UserDto, User> {

    private final RoleDtoToRoleMapper roleDtoToRoleMapper;
    private final OrderDtoToOrderMapper orderDtoToOrderMapper;
    private final UserDetailsDtoToUserDetailsMapper userDetailsDtoToUserDetailsMapper;

    @Autowired
    public UserDtoToUserMapper(RoleDtoToRoleMapper roleDtoToRoleMapper,
                               OrderDtoToOrderMapper orderDtoToOrderMapper,
                               UserDetailsDtoToUserDetailsMapper userDetailsDtoToUserDetailsMapper) {
        this.roleDtoToRoleMapper = roleDtoToRoleMapper;
        this.orderDtoToOrderMapper = orderDtoToOrderMapper;
        this.userDetailsDtoToUserDetailsMapper = userDetailsDtoToUserDetailsMapper;
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
                .map(roleDtoToRoleMapper::convert)
                .collect(Collectors.toList());
        user.setRoles(roles);

        user.setUserDetails(userDetailsDtoToUserDetailsMapper.convert(source.getUserDetails()));
        user.setEnabled(source.getEnabled());

        return user;
    }

}
