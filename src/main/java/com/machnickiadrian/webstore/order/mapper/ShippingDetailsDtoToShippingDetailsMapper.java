package com.machnickiadrian.webstore.order.mapper;

import com.machnickiadrian.webstore.order.dto.ShippingDetailsDto;
import com.machnickiadrian.webstore.order.entity.ShippingDetails;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class ShippingDetailsDtoToShippingDetailsMapper implements Converter<ShippingDetailsDto, ShippingDetails> {

    private final OrderDtoToOrderMapper orderDtoToOrderMapper;

    @Autowired
    public ShippingDetailsDtoToShippingDetailsMapper(@Lazy OrderDtoToOrderMapper orderDtoToOrderMapper) {
        this.orderDtoToOrderMapper = orderDtoToOrderMapper;
    }

    @Synchronized
    @Override
    public ShippingDetails convert(ShippingDetailsDto source) {
        if (source == null)
            return null;

        final ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setId(source.getId());
        shippingDetails.setFirstName(source.getFirstName());
        shippingDetails.setSecondName(source.getSecondName());
        shippingDetails.setLastName(source.getLastName());
        shippingDetails.setEmail(source.getEmail());
        shippingDetails.setAddress(source.getAddress());
        shippingDetails.setCity(source.getCity());

        return shippingDetails;
    }

}
