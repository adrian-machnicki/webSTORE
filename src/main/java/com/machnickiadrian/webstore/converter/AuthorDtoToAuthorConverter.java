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
public class AuthorDtoToAuthorConverter implements Converter<AuthorDto, Author> {

    @Synchronized
    @Override
    public Author convert(AuthorDto source) {
        if (source == null)
            return null;

        final Author author = new Author();
        author.setId(source.getId());
        author.setFirstName(source.getFirstName());
        author.setLastName(source.getLastName());
        return author;
    }
}
