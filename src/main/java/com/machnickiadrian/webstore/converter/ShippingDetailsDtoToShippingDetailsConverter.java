package com.machnickiadrian.webstore.converter;

import com.machnickiadrian.webstore.dto.ShippingDetailsDto;
import com.machnickiadrian.webstore.entity.ShippingDetails;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Adrian Machnicki
 */
@Component
public class ShippingDetailsDtoToShippingDetailsConverter implements Converter<ShippingDetailsDto, ShippingDetails> {

    private final OrderDtoToOrderConverter orderDtoToOrderConverter;

    @Autowired
    public ShippingDetailsDtoToShippingDetailsConverter(@Lazy OrderDtoToOrderConverter orderDtoToOrderConverter) {
        this.orderDtoToOrderConverter = orderDtoToOrderConverter;
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
