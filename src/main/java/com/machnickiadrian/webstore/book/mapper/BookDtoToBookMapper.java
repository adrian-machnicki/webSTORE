package com.machnickiadrian.webstore.book.mapper;

import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.book.entity.Author;
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
public class BookDtoToBookMapper implements Converter<BookDto, Book> {

    private final AuthorDtoToAuthorMapper authorDtoToAuthorMapper;
    private final BookDetailsDtoToBookDetailsMapper bookDetailsDtoToBookDetailsMapper;

    @Autowired
    public BookDtoToBookMapper(AuthorDtoToAuthorMapper authorDtoToAuthorMapper,
                               BookDetailsDtoToBookDetailsMapper bookDetailsDtoToBookDetailsMapper) {
        this.authorDtoToAuthorMapper = authorDtoToAuthorMapper;
        this.bookDetailsDtoToBookDetailsMapper = bookDetailsDtoToBookDetailsMapper;
    }

    @Synchronized
    @Override
    public Book convert(BookDto source) {
        if (source == null)
            return null;

        final Book book = new Book();
        book.setId(source.getId());
        book.setTitle(source.getTitle());
        book.setPrice(source.getPrice());

        List<Author> authors = source.getAuthors().stream()
                .map(authorDtoToAuthorMapper::convert)
                .collect(Collectors.toList());

        book.setAuthors(authors);
        book.setDetails(bookDetailsDtoToBookDetailsMapper.convert(source.getDetails()));
        book.setAmount(source.getAmount());

        return book;
    }

}
