package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.dto.OrderDto;
import com.machnickiadrian.webstore.dto.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

/**
 * @author Adrian Machnicki
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final String REGISTRATION_CONF_SUBJECT = "Welcome to webSTORE!";
    private static final String ORDER_CONF_SUBJECT = "webSTORE - order confirmation";
    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendRegistrationConfirmation(UserRegisterDto user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(REGISTRATION_CONF_SUBJECT);
        email.setText(buildRegistrationConfText(user));
        new Thread(() -> emailSender.send(email)).start();
    }

    @Override
    public void sendOrderConfirmation(OrderDto order) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(order.getShippingDetails().getEmail());
        email.setSubject(ORDER_CONF_SUBJECT);
        email.setText(buildOrderConfText(order));
        new Thread(() -> emailSender.send(email)).start();
    }

    private String buildRegistrationConfText(UserRegisterDto user) {
        StringJoiner messageJoiner = new StringJoiner(" ");
        messageJoiner.add("Hallo")
                .add(user.getUsername())
                .add("!\n We are pleased to see you at our webSTORE! :-)");

        return messageJoiner.toString();
    }

    private String buildOrderConfText(OrderDto order) {
        StringJoiner messageJoiner = new StringJoiner(" ");
        messageJoiner.add("Your order with id")
                .add(order.getId().toString())
                .add("has been accepted.\nAmount to pay: $")
                .add(String.valueOf(order.getAmount()))
                .add(".");

        return messageJoiner.toString();
    }

}
