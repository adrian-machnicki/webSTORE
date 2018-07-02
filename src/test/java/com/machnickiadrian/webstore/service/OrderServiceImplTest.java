package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.converter.OrderDtoToOrderConverter;
import com.machnickiadrian.webstore.converter.OrderToOrderDtoConverter;
import com.machnickiadrian.webstore.dto.OrderDto;
import com.machnickiadrian.webstore.dto.ShippingDetailsDto;
import com.machnickiadrian.webstore.dto.UserDetailsDto;
import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.repository.OrderRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Adrian Machnicki
 */
public class OrderServiceImplTest {

    OrderService orderService;

    @Mock
    OrderRepository orderRepositoryMock;

    @Mock
    EmailService emailServiceMock;

    @Mock
    OrderToOrderDtoConverter orderToOrderDtoConverterMock;

    @Mock
    OrderDtoToOrderConverter orderDtoToOrderConverterMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        orderService = new OrderServiceImpl(orderRepositoryMock, emailServiceMock,
                orderToOrderDtoConverterMock, orderDtoToOrderConverterMock);
    }

    @Test
    public void findById_ShouldCallRepositoryAndReturnOrder() {
        // given
        Long id = 1L;
        Order order = new Order();
        order.setId(id);
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        given(orderRepositoryMock.findById(id))
                .willReturn(order);
        given(orderToOrderDtoConverterMock.convert(order))
                .willReturn(orderDto);

        // when
        OrderDto actualOrder = orderService.findById(id);

        // then
        assertNotNull("Should return order object", actualOrder);
        verify(orderRepositoryMock, times(1))
                .findById(id);
        verify(orderToOrderDtoConverterMock, times(1))
                .convert(order);
    }

    @Test
    public void findAll_ShouldCallRepositoryAndReturnAllOrders() {
        // given
        Order order1 = new Order();
        Order order2 = new Order();
        OrderDto orderDto = new OrderDto();
        List<Order> orders = new ArrayList<>(Arrays.asList(order1, order2));
        given(orderRepositoryMock.findAll())
                .willReturn(orders);
        given(orderToOrderDtoConverterMock.convert(order1))
                .willReturn(orderDto);
        given(orderToOrderDtoConverterMock.convert(order2))
                .willReturn(orderDto);

        // when
        List<OrderDto> actualOrders = orderService.findAll();

        // then
        assertEquals("Should return list containing 2 orders", 2, actualOrders.size());
        verify(orderRepositoryMock, times(1))
                .findAll();
        verify(orderToOrderDtoConverterMock, times(2)).convert(any(Order.class));
    }

    @Test
    public void findAllByUsername_ShouldCallRepositoryAndReturnAllUsersOrders() {
        // given
        String username = "username";
        Order order1 = new Order();
        Order order2 = new Order();
        OrderDto orderDto = new OrderDto();
        List<Order> orders = new ArrayList<>(Arrays.asList(order1, order2));
        given(orderRepositoryMock.findAllByUserUsername(username))
                .willReturn(orders);
        given(orderToOrderDtoConverterMock.convert(order1))
                .willReturn(orderDto);
        given(orderToOrderDtoConverterMock.convert(order2))
                .willReturn(orderDto);

        // when
        List<OrderDto> actualOrders = orderService.findAllByUsername(username);

        // then
        assertEquals("Should return list containing 2 orders", 2, actualOrders.size());
        verify(orderRepositoryMock, times(1))
                .findAllByUserUsername(username);
        verify(orderToOrderDtoConverterMock, times(2)).convert(any(Order.class));
    }

    @Test
    public void save_ShouldCallServices() {
        // given
        OrderDto orderDto = new OrderDto();
        Order order = new Order();
        given(orderDtoToOrderConverterMock.convert(orderDto))
                .willReturn(order);

        // when
        orderService.save(orderDto);

        // then
        verify(orderDtoToOrderConverterMock, times(1)).convert(orderDto);
        verify(orderRepositoryMock, times(1)).save(order);
        verify(emailServiceMock, times(1)).sendOrderConfirmation(orderDto);
    }

    @Test
    public void setPaid_ShouldSetOrderAsPaid() {
        // given
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        given(orderRepositoryMock.findById(orderId))
                .willReturn(order);

        // when
        orderService.setPaid(orderId);

        // then
        verify(orderRepositoryMock, times(1))
                .findById(orderId);
        assertTrue("Order should be set as paid", order.isPaid());
    }

    @Test
    public void setSent_ShouldSetOrderAsSent() {
        // given
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        given(orderRepositoryMock.findById(orderId))
                .willReturn(order);

        // when
        orderService.setSent(orderId);

        // then
        verify(orderRepositoryMock, times(1))
                .findById(orderId);
        assertTrue("Order should be set as sent", order.isSent());
    }

    @Test
    public void search_ShouldReturnSearchedOrders() {
        // given
        String phrase = "123";
        List<Order> orders = createOrderListForSearching();
        OrderDto orderDto0 = createOrderDto0();
        OrderDto orderDto1 = createOrderDto1();
        OrderDto orderDto2 = createOrderDto2();

        given(orderRepositoryMock.findAll())
                .willReturn(orders);
        given(orderToOrderDtoConverterMock.convert(orders.get(0)))
                .willReturn(orderDto0);
        given(orderToOrderDtoConverterMock.convert(orders.get(1)))
                .willReturn(orderDto1);
        given(orderToOrderDtoConverterMock.convert(orders.get(2)))
                .willReturn(orderDto2);

        // when
        List<OrderDto> actualOrders = orderService.search(phrase);

        // then
        assertEquals("Should return list containing 1 order", 1, actualOrders.size());
        verify(orderRepositoryMock, times(1))
                .findAll();
        verify(orderToOrderDtoConverterMock, times(3))
                .convert(any(Order.class));
    }

    private List<Order> createOrderListForSearching() {
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        return Arrays.asList(order1, order2, order3);
    }

    private OrderDto createOrderDto0() {
        OrderDto orderDto0 = new OrderDto();
        orderDto0.setId(123L);
        return orderDto0;
    }

    private OrderDto createOrderDto1() {
        OrderDto orderDto1 = new OrderDto();
        orderDto1.setId(124L);
        return orderDto1;
    }

    private OrderDto createOrderDto2() {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setCity("Paris");

        UserDto userDto = new UserDto();
        userDto.setUserDetails(userDetailsDto);

        ShippingDetailsDto shippingDetailsDto = new ShippingDetailsDto();

        OrderDto orderDto2 = new OrderDto();
        orderDto2.setId(125L);
        orderDto2.setUser(userDto);
        orderDto2.setShippingDetails(shippingDetailsDto);
        return orderDto2;
    }
}