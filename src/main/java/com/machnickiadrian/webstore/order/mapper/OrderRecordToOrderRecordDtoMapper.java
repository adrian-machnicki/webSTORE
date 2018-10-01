package com.machnickiadrian.webstore.order.mapper;

import com.machnickiadrian.webstore.book.mapper.BookToBookDtoMapper;
import com.machnickiadrian.webstore.order.dto.OrderRecordDto;
import com.machnickiadrian.webstore.order.entity.OrderRecord;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderRecordToOrderRecordDtoMapper implements Converter<OrderRecord, OrderRecordDto> {

    private final BookToBookDtoMapper bookToBookDtoMapper;

    @Autowired
    public OrderRecordToOrderRecordDtoMapper(BookToBookDtoMapper bookToBookDtoMapper) {
        this.bookToBookDtoMapper = bookToBookDtoMapper;
    }

    @Synchronized
    @Override
    public OrderRecordDto convert(OrderRecord source) {
        if (source == null)
            return null;

        final OrderRecordDto dto = new OrderRecordDto();
        dto.setId(source.getId());
        dto.setQuantity(source.getQuantity());
        dto.setPrice(source.getPrice());
        dto.setAmount(source.getAmount());
        dto.setBook(bookToBookDtoMapper.convert(source.getBook()));

        return dto;
    }

}
