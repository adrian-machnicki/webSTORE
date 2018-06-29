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
public class ShippingDetailsToShippingDetailsDtoConverter implements Converter<ShippingDetails, ShippingDetailsDto> {

    private final OrderToOrderDtoConverter orderToOrderDtoConverter;

    @Autowired
    public ShippingDetailsToShippingDetailsDtoConverter(@Lazy OrderToOrderDtoConverter orderToOrderDtoConverter) {
        this.orderToOrderDtoConverter = orderToOrderDtoConverter;
    }

    @Synchronized
    @Override
    public ShippingDetailsDto convert(ShippingDetails source) {
        if (source == null)
            return null;

        final ShippingDetailsDto dto = new ShippingDetailsDto();
        dto.setId(source.getId());
        dto.setFirstName(source.getFirstName());
        dto.setSecondName(source.getSecondName());
        dto.setLastName(source.getLastName());
        dto.setEmail(source.getEmail());
        dto.setAddress(source.getAddress());
        dto.setCity(source.getCity());

        return dto;
    }

}
