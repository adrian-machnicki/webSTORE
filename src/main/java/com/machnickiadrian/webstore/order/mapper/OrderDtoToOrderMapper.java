package com.machnickiadrian.webstore.order.mapper;

import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.order.entity.Order;
import com.machnickiadrian.webstore.order.entity.OrderRecord;
import com.machnickiadrian.webstore.user.mapper.UserDtoToUserMapper;
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
public class OrderDtoToOrderMapper implements Converter<OrderDto, Order> {

    private final OrderRecordDtoToOrderRecordMapper orderRecordDtoToOrderRecordMapper;
    private final ShippingDetailsDtoToShippingDetailsMapper shippingDetailsDtoToShippingDetailsMapper;
    private final UserDtoToUserMapper userDtoToUserMapper;

    @Autowired
    public OrderDtoToOrderMapper(OrderRecordDtoToOrderRecordMapper orderRecordDtoToOrderRecordMapper,
                                 ShippingDetailsDtoToShippingDetailsMapper shippingDetailsDtoToShippingDetailsMapper,
                                 @Lazy UserDtoToUserMapper userDtoToUserMapper) {
        this.orderRecordDtoToOrderRecordMapper = orderRecordDtoToOrderRecordMapper;
        this.shippingDetailsDtoToShippingDetailsMapper = shippingDetailsDtoToShippingDetailsMapper;
        this.userDtoToUserMapper = userDtoToUserMapper;
    }

    @Synchronized
    @Override
    public Order convert(OrderDto source) {
        if (source == null)
            return null;

        final Order order = new Order();
        order.setId(source.getId());
        order.setAmount(source.getAmount());

        List<OrderRecord> orderRecords = source.getRecords().stream()
                .map(orderRecordDtoToOrderRecordMapper::convert)
                .collect(Collectors.toList());

        order.setRecords(orderRecords);
        order.setUser(userDtoToUserMapper.convert(source.getUser()));
        order.setShippingDetails(shippingDetailsDtoToShippingDetailsMapper.convert(source.getShippingDetails()));
        order.setDate(source.getDate());
        order.setPaid(source.isPaid());
        order.setSent(source.isSent());

        return order;
    }

}
