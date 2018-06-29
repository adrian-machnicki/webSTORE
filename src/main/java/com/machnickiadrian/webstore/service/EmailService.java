package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.dto.OrderDto;
import com.machnickiadrian.webstore.dto.UserRegisterDto;

/**
 * Service interface providing the possibility (contract) for emails sending.
 *
 * @author Adrian Machnicki
 */
public interface EmailService {

    void sendRegistrationConfirmation(UserRegisterDto user);

    void sendOrderConfirmation(OrderDto order);

}
