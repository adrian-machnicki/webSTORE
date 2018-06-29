package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.AuthorDto;
import com.machnickiadrian.webstore.entity.Author;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class AuthorToAuthorDtoConverter implements Converter<Author, AuthorDto> {

    @Synchronized
    @Override
    public AuthorDto convert(Author source) {
        if (source == null)
            return null;

        final AuthorDto dto = new AuthorDto();
        dto.setId(source.getId());
        dto.setFirstName(source.getFirstName());
        dto.setLastName(source.getLastName());
        return dto;
    }

}
