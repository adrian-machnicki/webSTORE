package com.machnickiadrian.webstore.order;

import com.machnickiadrian.webstore.order.entity.Order;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository class for <code>Order</code> objects.
 *
 * @author Adrian Machnicki
 */
public interface OrderRepository extends Repository<Order, Long> {

    Order findById(Long id);

    List<Order> findAll();

    List<Order> findAllByUserUsername(String userName);

    void save(Order order);

}