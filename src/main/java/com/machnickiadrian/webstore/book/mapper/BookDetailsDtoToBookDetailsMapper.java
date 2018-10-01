package com.machnickiadrian.webstore.book.mapper;

import com.machnickiadrian.webstore.book.dto.BookDetailsDto;
import com.machnickiadrian.webstore.book.entity.BookDetails;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class BookDetailsDtoToBookDetailsMapper implements Converter<BookDetailsDto, BookDetails> {

    @Synchronized
    @Override
    public BookDetails convert(BookDetailsDto source) {
        if (source == null)
            return null;

        final BookDetails bookDetails = new BookDetails();
        bookDetails.setId(source.getId());
        bookDetails.setPages(source.getPages());
        bookDetails.setDescription(source.getDescription());

        return bookDetails;
    }

}
