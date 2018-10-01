package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.order.entity.OrderService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adrian Machnicki
 */
public class AdminOrdersControllerTest {

    MockMvc mockMvc;

    @Mock
    OrderService orderServiceMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new AdminOrdersController(orderServiceMock))
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getOrderManagement_WithoutRequestParamShouldReturnAllOrders() throws Exception {
        // given
        List<OrderDto> orders = new ArrayList<>(Arrays.asList(new OrderDto(), new OrderDto()));
        given(orderServiceMock.findAll())
                .willReturn(orders);

        // when
        // then
        mockMvc.perform(get("/admin/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/orders"))
                .andExpect(forwardedUrl("/view/admin/orders.jsp"))
                .andExpect(model().attribute("orders", orders));

        verify(orderServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(orderServiceMock);
    }

    @Test
    public void getOrderManagement_WithSearchParamShouldReturnSearchedOrders() throws Exception {
        // given
        String searchPhrase = "Kowalski";
        List<OrderDto> orders = new ArrayList<>(Arrays.asList(new OrderDto(), new OrderDto()));
        given(orderServiceMock.search(searchPhrase))
                .willReturn(orders);

        // when
        // then
        mockMvc.perform(get("/admin/orders?search=" + searchPhrase))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/orders"))
                .andExpect(forwardedUrl("/view/admin/orders.jsp"))
                .andExpect(model().attribute("orders", orders));

        verify(orderServiceMock, times(1)).search(searchPhrase);
        verifyNoMoreInteractions(orderServiceMock);
    }

    @Test
    public void setPaid_ShouldCallServiceAndRedirectToOrders() throws Exception {
        // given
        Long orderId = 1L;

        // when
        // then
        mockMvc.perform(post("/admin/orders/paid?orderId=" + orderId))
                .andExpect(status().is3xxRedirection());

        verify(orderServiceMock, times(1)).setPaid(orderId);
    }

    @Test
    public void setSent_ShouldCallServiceAndRedirectToOrders() throws Exception {
        // given
        Long orderId = 1L;

        // when
        // then
        mockMvc.perform(post("/admin/orders/sent?orderId=" + orderId))
                .andExpect(status().is3xxRedirection());

        verify(orderServiceMock, times(1)).setSent(orderId);
    }

    @Test
    public void getOrder_ShouldGetOrderAndRenderView() throws Exception {
        // given
        Long orderId = 1L;
        given(orderServiceMock.findById(orderId))
                .willReturn(new OrderDto());

        // when
        // then
        mockMvc.perform(get("/admin/orders/order?id=" + orderId))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/order"))
                .andExpect(forwardedUrl("/view/admin/order.jsp"))
                .andExpect(model().attributeExists("order"));

        verify(orderServiceMock, times(1)).findById(orderId);
        verifyNoMoreInteractions(orderServiceMock);
    }

}