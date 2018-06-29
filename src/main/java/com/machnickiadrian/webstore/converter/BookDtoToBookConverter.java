package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.BookDto;
import com.machnickiadrian.webstore.entity.Author;
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
public class BookDtoToBookConverter implements Converter<BookDto, Book> {

    private final AuthorDtoToAuthorConverter authorDtoToAuthorConverter;
    private final BookDetailsDtoToBookDetailsConverter bookDetailsDtoToBookDetailsConverter;

    @Autowired
    public BookDtoToBookConverter(AuthorDtoToAuthorConverter authorDtoToAuthorConverter,
                                  BookDetailsDtoToBookDetailsConverter bookDetailsDtoToBookDetailsConverter) {
        this.authorDtoToAuthorConverter = authorDtoToAuthorConverter;
        this.bookDetailsDtoToBookDetailsConverter = bookDetailsDtoToBookDetailsConverter;
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
                .map(authorDtoToAuthorConverter::convert)
                .collect(Collectors.toList());

        book.setAuthors(authors);
        book.setDetails(bookDetailsDtoToBookDetailsConverter.convert(source.getDetails()));
        book.setAmount(source.getAmount());

        return book;
    }

}
