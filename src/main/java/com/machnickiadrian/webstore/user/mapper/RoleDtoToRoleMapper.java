package com.machnickiadrian.webstore.user.mapper;

import com.machnickiadrian.webstore.user.dto.RoleDto;
import com.machnickiadrian.webstore.user.entity.Role;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class RoleDtoToRoleMapper implements Converter<RoleDto, Role> {

    @Synchronized
    @Override
    public Role convert(RoleDto source) {
        if (source == null)
            return null;

        final Role role = new Role();
        role.setId(source.getId());
        role.setUsername(source.getUsername());
        role.setAuthority(source.getAuthority());

        return role;
    }

}
