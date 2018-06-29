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
public class RoleToRoleDtoConverter implements Converter<Role, RoleDto> {

    @Synchronized
    @Override
    public RoleDto convert(Role source) {
        if (source == null)
            return null;

        final RoleDto dto = new RoleDto();
        dto.setId(source.getId());
        dto.setUsername(source.getUsername());
        dto.setAuthority(source.getAuthority());

        return dto;
    }

}
