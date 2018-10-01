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
public class BookDetailsToBookDetailsDtoMapper implements Converter<BookDetails, BookDetailsDto> {

    @Synchronized
    @Override
    public BookDetailsDto convert(BookDetails source) {
        if (source == null)
            return null;

        final BookDetailsDto dto = new BookDetailsDto();
        dto.setId(source.getId());
        dto.setPages(source.getPages());
        dto.setDescription(source.getDescription());

        return dto;
    }

}
