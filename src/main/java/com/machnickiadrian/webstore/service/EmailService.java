package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.entity.User;

/**
 * Service interface providing the possibility (contract) for emails sending.
 * 
 * @author Adrian Machnicki
 *
 */
public interface EmailService {
	
	void sendRegistrationConfirmation(User user);
	
	void sendOrderConfirmation(Order order);

}
