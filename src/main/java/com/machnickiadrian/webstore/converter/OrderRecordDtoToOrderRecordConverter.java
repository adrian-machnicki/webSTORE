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
public class OrderRecordDtoToOrderRecordConverter implements Converter<OrderRecordDto, OrderRecord> {

    private final BookDtoToBookConverter bookDtoToBookConverter;

    @Autowired
    public OrderRecordDtoToOrderRecordConverter(BookDtoToBookConverter bookDtoToBookConverter) {
        this.bookDtoToBookConverter = bookDtoToBookConverter;
    }

    @Synchronized
    @Override
    public OrderRecord convert(OrderRecordDto source) {
        if (source == null)
            return null;

        final OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(source.getId());
        orderRecord.setQuantity(source.getQuantity());
        orderRecord.setPrice(source.getPrice());
        orderRecord.setAmount(source.getAmount());
        orderRecord.setBook(bookDtoToBookConverter.convert(source.getBook()));

        return orderRecord;
    }

}
