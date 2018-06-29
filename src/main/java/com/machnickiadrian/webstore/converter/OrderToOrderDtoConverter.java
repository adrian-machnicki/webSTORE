package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.OrderDto;
import com.machnickiadrian.webstore.dto.OrderRecordDto;
import com.machnickiadrian.webstore.entity.Order;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Adrian Machnicki
 */
@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {

    private final OrderRecordToOrderRecordDtoConverter orderRecordToOrderRecordDtoConverter;
    private final ShippingDetailsToShippingDetailsDtoConverter shippingDetailsToShippingDetailsDtoConverter;
    private final UserToUserDtoConverter userToUserDtoConverter;

    @Autowired
    public OrderToOrderDtoConverter(OrderRecordToOrderRecordDtoConverter orderRecordToOrderRecordDtoConverter,
                                    ShippingDetailsToShippingDetailsDtoConverter shippingDetailsToShippingDetailsDtoConverter,
                                    @Lazy UserToUserDtoConverter userToUserDtoConverter) {
        this.orderRecordToOrderRecordDtoConverter = orderRecordToOrderRecordDtoConverter;
        this.shippingDetailsToShippingDetailsDtoConverter = shippingDetailsToShippingDetailsDtoConverter;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @Synchronized
    @Override
    public OrderDto convert(Order source) {
        if (source == null)
            return null;

        final OrderDto dto = new OrderDto();
        dto.setId(source.getId());
        dto.setAmount(source.getAmount());

        List<OrderRecordDto> orderRecordDtos = source.getRecords().stream()
                .map(orderRecordToOrderRecordDtoConverter::convert)
                .collect(Collectors.toList());

        dto.setRecords(orderRecordDtos);
        dto.setUser(userToUserDtoConverter.convert(source.getUser()));
        dto.setShippingDetails(shippingDetailsToShippingDetailsDtoConverter.convert(source.getShippingDetails()));
        dto.setDate(source.getDate());
        dto.setPaid(source.isPaid());
        dto.setSent(source.isSent());

        return dto;
    }

}
