package com.machnickiadrian.webstore.order.mapper;

import com.machnickiadrian.webstore.book.mapper.BookDtoToBookMapper;
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
public class OrderRecordDtoToOrderRecordMapper implements Converter<OrderRecordDto, OrderRecord> {

    private final BookDtoToBookMapper bookDtoToBookMapper;

    @Autowired
    public OrderRecordDtoToOrderRecordMapper(BookDtoToBookMapper bookDtoToBookMapper) {
        this.bookDtoToBookMapper = bookDtoToBookMapper;
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
        orderRecord.setBook(bookDtoToBookMapper.convert(source.getBook()));

        return orderRecord;
    }

}
