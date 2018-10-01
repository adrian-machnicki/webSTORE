package com.machnickiadrian.webstore.order.entity;

import com.machnickiadrian.webstore.order.dto.OrderDto;

import java.util.List;

/**
 * Service interface for <code>Order</code> objects manipulation.
 *
 * @author Adrian Machnicki
 */
public interface OrderService {

    OrderDto findById(Long id);

    List<OrderDto> findAll();

    List<OrderDto> findAllByUsername(String username);

    void save(OrderDto order);

    void setPaid(Long id);

    void setSent(Long id);

    List<OrderDto> search(String phrase);

}