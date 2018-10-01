package com.machnickiadrian.webstore.order.mapper;

import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.order.dto.OrderRecordDto;
import com.machnickiadrian.webstore.order.entity.Order;
import com.machnickiadrian.webstore.user.mapper.UserToUserDtoMapper;
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
public class OrderToOrderDtoMapper implements Converter<Order, OrderDto> {

    private final OrderRecordToOrderRecordDtoMapper orderRecordToOrderRecordDtoMapper;
    private final ShippingDetailsToShippingDetailsDtoMapper shippingDetailsToShippingDetailsDtoMapper;
    private final UserToUserDtoMapper userToUserDtoMapper;

    @Autowired
    public OrderToOrderDtoMapper(OrderRecordToOrderRecordDtoMapper orderRecordToOrderRecordDtoMapper,
                                 ShippingDetailsToShippingDetailsDtoMapper shippingDetailsToShippingDetailsDtoMapper,
                                 @Lazy UserToUserDtoMapper userToUserDtoMapper) {
        this.orderRecordToOrderRecordDtoMapper = orderRecordToOrderRecordDtoMapper;
        this.shippingDetailsToShippingDetailsDtoMapper = shippingDetailsToShippingDetailsDtoMapper;
        this.userToUserDtoMapper = userToUserDtoMapper;
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
                .map(orderRecordToOrderRecordDtoMapper::convert)
                .collect(Collectors.toList());

        dto.setRecords(orderRecordDtos);
        dto.setUser(userToUserDtoMapper.convert(source.getUser()));
        dto.setShippingDetails(shippingDetailsToShippingDetailsDtoMapper.convert(source.getShippingDetails()));
        dto.setDate(source.getDate());
        dto.setPaid(source.isPaid());
        dto.setSent(source.isSent());

        return dto;
    }

}
