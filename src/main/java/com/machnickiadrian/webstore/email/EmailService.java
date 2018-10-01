package com.machnickiadrian.webstore.email;

import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.user.dto.UserRegisterDto;

/**
 * Service interface providing the possibility (contract) for emails sending.
 *
 * @author Adrian Machnicki
 */
public interface EmailService {

    void sendRegistrationConfirmation(UserRegisterDto user);

    void sendOrderConfirmation(OrderDto order);

}
