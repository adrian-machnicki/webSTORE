package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.email.EmailService;
import com.machnickiadrian.webstore.email.EmailServiceImpl;
import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.order.dto.OrderRecordDto;
import com.machnickiadrian.webstore.order.dto.ShippingDetailsDto;
import com.machnickiadrian.webstore.user.dto.UserDto;
import com.machnickiadrian.webstore.user.dto.UserRegisterDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Adrian Machnicki
 */
public class EmailServiceImplTest {

    EmailService emailService;

    @Mock
    JavaMailSender emailSenderMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        emailService = new EmailServiceImpl(emailSenderMock);
    }

    @Test
    public void sendRegistrationConfirmation_ShouldBuildMessageAndCallService() {
        // given
        UserRegisterDto user = new UserRegisterDto();
        user.setUsername("SampleUser");

        // when
        emailService.sendRegistrationConfirmation(user);

        // then
        verify(emailSenderMock, timeout(100))
                .send(any(SimpleMailMessage.class));
        verifyNoMoreInteractions(emailSenderMock);
    }

    @Test
    public void sendOrderConfirmation_ShouldBuildMessageAndCallService() {
        // given
        OrderDto order = buildOrder();

        // when
        emailService.sendOrderConfirmation(order);

        // then
        verify(emailSenderMock, timeout(100))
                .send(any(SimpleMailMessage.class));
        verifyNoMoreInteractions(emailSenderMock);
    }

    private OrderDto buildOrder() {
        ShippingDetailsDto shippingDetailsDto = new ShippingDetailsDto();
        shippingDetailsDto.setEmail("test@email.com");

        OrderDto order = new OrderDto();
        order.setId(99L);
        order.setAmount(99.99);
        order.setRecords(new ArrayList<OrderRecordDto>());
        order.setShippingDetails(shippingDetailsDto);
        order.setUser(new UserDto());
        order.setDate(new Date());
        order.setPaid(false);
        order.setSent(false);
        return order;
    }

}