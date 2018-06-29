package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.RoleDto;
import com.machnickiadrian.webstore.entity.Role;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class RoleDtoToRoleConverter implements Converter<RoleDto, Role> {

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
