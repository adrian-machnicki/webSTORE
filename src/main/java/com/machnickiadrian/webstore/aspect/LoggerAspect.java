package com.machnickiadrian.webstore.aspect;

import java.text.DecimalFormat;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.service.OrderService;
import com.machnickiadrian.webstore.service.UserService;

@Aspect
@Component
public class LoggerAspect {
	
	private static final Logger USER_SERVICE_LOGGER = Logger.getLogger(UserService.class.getName());
	private static final Logger ORDER_SERVICE_LOGGER = Logger.getLogger(OrderService.class.getName());
	
	private static final DecimalFormat doubleFormatter = new DecimalFormat("#0.00");
	
	@Around("execution(* com.machnickiadrian.webstore.service.UserServiceImpl.registerNewUser(com.machnickiadrian.webstore.dto.UserDto)) "
			+ "&& args(userDto)")
	public void logNewUser(ProceedingJoinPoint pjp, UserDto userDto) throws Throwable {
			pjp.proceed();
			String message = getNewUserMessage(userDto);
			USER_SERVICE_LOGGER.log(Level.INFO, message);
	}
	
	@AfterReturning("execution(* com.machnickiadrian.webstore.service.OrderServiceImpl.save(com.machnickiadrian.webstore.entity.Order)) "
			+ "&& args(order)")
	public void logNewOrder(Order order) {
		String message = getNewOrderMessage(order);
		ORDER_SERVICE_LOGGER.log(Level.INFO, message);
	}
	
	@AfterReturning("execution(* com.machnickiadrian.webstore.service.UserServiceImpl.deleteById(java.lang.Long)) "
			+ "&& args(id)")
	public void logDeletingUser(Long id) {
		String message = getDeletedUserMessage(id);
		ORDER_SERVICE_LOGGER.log(Level.INFO, message);
	}
	
	private String getNewUserMessage(UserDto user) {
		StringJoiner joiner = new StringJoiner(" ");
		joiner.add("New User")
			.add("[username=")
			.add(user.getUsername())
			.add(", email=")
			.add(user.getEmail())
			.add("] registered");
			
		return joiner.toString();
	}

	private String getNewOrderMessage(Order order) {
		StringJoiner joiner = new StringJoiner(" ");
		joiner.add("New Order[")
			.add("id=")
			.add(order.getId().toString())
			.add(", amount=")
			.add(doubleFormatter.format(order.getAmount()))
			.add(", city=")
			.add(order.getShippingDetails().getCity())
			.add("] placed");
		
		return joiner.toString();
	}
	
	private String getDeletedUserMessage(Long id) {
		return "User[id= " + id + "] deleted.";
	}
	
}

