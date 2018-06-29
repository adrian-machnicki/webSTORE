package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.OrderRecordDto;
import com.machnickiadrian.webstore.entity.OrderRecord;
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
public class OrderRecordToOrderRecordDtoConverter implements Converter<OrderRecord, OrderRecordDto> {

    private final BookToBookDtoConverter bookToBookDtoConverter;

    @Autowired
    public OrderRecordToOrderRecordDtoConverter(BookToBookDtoConverter bookToBookDtoConverter) {
        this.bookToBookDtoConverter = bookToBookDtoConverter;
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
        dto.setBook(bookToBookDtoConverter.convert(source.getBook()));

        return dto;
    }

}
