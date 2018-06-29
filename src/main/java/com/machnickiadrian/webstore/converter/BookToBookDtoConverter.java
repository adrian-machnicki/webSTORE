package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.AuthorDto;
import com.machnickiadrian.webstore.dto.BookDto;
import com.machnickiadrian.webstore.entity.Book;
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
public class BookToBookDtoConverter implements Converter<Book, BookDto> {

    private final AuthorToAuthorDtoConverter authorToAuthorDtoConverter;
    private final BookDetailsToBookDetailsDtoConverter bookDetailsToBookDetailsDtoConverter;

    @Autowired
    public BookToBookDtoConverter(AuthorToAuthorDtoConverter authorToAuthorDtoConverter,
                                  BookDetailsToBookDetailsDtoConverter bookDetailsToBookDetailsDtoConverter) {
        this.authorToAuthorDtoConverter = authorToAuthorDtoConverter;
        this.bookDetailsToBookDetailsDtoConverter = bookDetailsToBookDetailsDtoConverter;
    }

    @Synchronized
    @Override
    public BookDto convert(Book source) {
        if (source == null)
            return null;

        final BookDto dto = new BookDto();
        dto.setId(source.getId());
        dto.setTitle(source.getTitle());
        dto.setPrice(source.getPrice());

        List<AuthorDto> authorDtos = source.getAuthors().stream()
                .map(authorToAuthorDtoConverter::convert)
                .collect(Collectors.toList());

        dto.setAuthors(authorDtos);
        dto.setDetails(bookDetailsToBookDetailsDtoConverter.convert(source.getDetails()));
        dto.setAmount(source.getAmount());

        return dto;
    }

}
