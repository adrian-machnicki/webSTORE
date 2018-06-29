package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.converter.OrderDtoToOrderConverter;
import com.machnickiadrian.webstore.converter.OrderToOrderDtoConverter;
import com.machnickiadrian.webstore.dto.OrderDto;
import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Implementation of <code>OrderService</code> interface. It's methods are
 * transactional and secured with Spring Security and JSR-250.
 *
 * @author Adrian Machnicki
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final String ADMIN = "ROLE_ADMIN";
    private final OrderRepository repository;
    private final EmailService emailService;
    private final OrderToOrderDtoConverter orderToOrderDtoConverter;
    private final OrderDtoToOrderConverter orderDtoToOrderConverter;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, EmailService emailService,
                            OrderToOrderDtoConverter orderToOrderDtoConverter,
                            OrderDtoToOrderConverter orderDtoToOrderConverter) {
        this.repository = repository;
        this.emailService = emailService;
        this.orderToOrderDtoConverter = orderToOrderDtoConverter;
        this.orderDtoToOrderConverter = orderDtoToOrderConverter;
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.user.username == principal.username")
    @Transactional(readOnly = true)
    @Override
    public OrderDto findById(Long id) {
        Order order = repository.findById(id);
        return orderToOrderDtoConverter.convert(order);
    }

    @RolesAllowed(ADMIN)
    @Transactional(readOnly = true)
    @Override
    public List<OrderDto> findAll() {
        return repository.findAll().stream()
                .map(orderToOrderDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDto> findAllByUsername(String username) {
        return repository.findAllByUserUsername(username).stream()
                .map(orderToOrderDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void save(OrderDto order) {
        order.setDate(new Date());
        Order orderToSave = orderDtoToOrderConverter.convert(order);
        repository.save(orderToSave);
        order.setId(orderToSave.getId());
        emailService.sendOrderConfirmation(order);
    }

    @RolesAllowed(ADMIN)
    @Transactional
    @Override
    public void setPaid(Long id) {
        Order order = repository.findById(id);
        boolean paid = order.isPaid();
        order.setPaid(!paid);
    }

    @RolesAllowed(ADMIN)
    @Transactional
    @Override
    public void setSent(Long id) {
        Order order = repository.findById(id);
        boolean sent = order.isSent();
        order.setSent(!sent);
    }

    @RolesAllowed(ADMIN)
    @Transactional(readOnly = true)
    @Override
    public List<OrderDto> search(String phrase) {
        phrase = phrase.trim();
        String[] keywords = phrase.split(" ");
        List<OrderDto> allOrders = repository.findAll().stream()
                .map(orderToOrderDtoConverter::convert)
                .collect(Collectors.toList());
        List<OrderDto> foundOrders = new ArrayList<>();

        for (OrderDto order : allOrders) {
            String id = String.valueOf(order.getId());
            if (id.equals(phrase)) {
                foundOrders.add(order);
                continue;
            }

            StringJoiner orderData = new StringJoiner(" ");
            orderData.add(id);

            if (order.getUser() != null) {
                orderData.add(order.getUser().getFirstName());
                orderData.add(order.getUser().getLastName());
                orderData.add(order.getUser().getUsername());
                orderData.add(order.getUser().getUserDetails().getCity());
            }

            if (order.getShippingDetails() != null) {
                orderData.add(order.getShippingDetails().getFirstName());
                orderData.add(order.getShippingDetails().getLastName());
                orderData.add(order.getShippingDetails().getCity());
            }

            boolean containsAll = true;
            for (String keyword : keywords) {
                if (!orderData.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    containsAll = false;
                    break;
                }
            }
            if (containsAll)
                foundOrders.add(order);
        }

        return foundOrders;
    }
}