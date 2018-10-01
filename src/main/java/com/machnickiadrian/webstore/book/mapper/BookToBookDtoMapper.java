package com.machnickiadrian.webstore.book.mapper;

import com.machnickiadrian.webstore.book.dto.AuthorDto;
import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.book.entity.Book;
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
public class BookToBookDtoMapper implements Converter<Book, BookDto> {

    private final AuthorToAuthorDtoMapper authorToAuthorDtoMapper;
    private final BookDetailsToBookDetailsDtoMapper bookDetailsToBookDetailsDtoMapper;

    @Autowired
    public BookToBookDtoMapper(AuthorToAuthorDtoMapper authorToAuthorDtoMapper,
                               BookDetailsToBookDetailsDtoMapper bookDetailsToBookDetailsDtoMapper) {
        this.authorToAuthorDtoMapper = authorToAuthorDtoMapper;
        this.bookDetailsToBookDetailsDtoMapper = bookDetailsToBookDetailsDtoMapper;
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
                .map(authorToAuthorDtoMapper::convert)
                .collect(Collectors.toList());

        dto.setAuthors(authorDtos);
        dto.setDetails(bookDetailsToBookDetailsDtoMapper.convert(source.getDetails()));
        dto.setAmount(source.getAmount());

        return dto;
    }

}
