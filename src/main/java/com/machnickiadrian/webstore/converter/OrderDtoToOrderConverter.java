package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.OrderDto;
import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.entity.OrderRecord;
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
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {

    private final OrderRecordDtoToOrderRecordConverter orderRecordDtoToOrderRecordConverter;
    private final ShippingDetailsDtoToShippingDetailsConverter shippingDetailsDtoToShippingDetailsConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;

    @Autowired
    public OrderDtoToOrderConverter(OrderRecordDtoToOrderRecordConverter orderRecordDtoToOrderRecordConverter,
                                    ShippingDetailsDtoToShippingDetailsConverter shippingDetailsDtoToShippingDetailsConverter,
                                    @Lazy UserDtoToUserConverter userDtoToUserConverter) {
        this.orderRecordDtoToOrderRecordConverter = orderRecordDtoToOrderRecordConverter;
        this.shippingDetailsDtoToShippingDetailsConverter = shippingDetailsDtoToShippingDetailsConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
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
                .map(orderRecordDtoToOrderRecordConverter::convert)
                .collect(Collectors.toList());

        order.setRecords(orderRecords);
        order.setUser(userDtoToUserConverter.convert(source.getUser()));
        order.setShippingDetails(shippingDetailsDtoToShippingDetailsConverter.convert(source.getShippingDetails()));
        order.setDate(source.getDate());
        order.setPaid(source.isPaid());
        order.setSent(source.isSent());

        return order;
    }

}
