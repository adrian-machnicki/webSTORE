package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.book.BookService;
import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.model.Cart;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adrian Machnicki
 */
public class CartControllerTest {

    MockMvc mockMvc;

    @Mock
    Cart cartMock;

    @Mock
    BookService bookServiceMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new CartController(bookServiceMock, cartMock))
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getCart_ShouldReturnCartAndRenderView() throws Exception {
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart/cart"))
                .andExpect(forwardedUrl("/view/cart/cart.jsp"))
                .andExpect(model().attributeExists("cart"));
    }

    @Test
    public void addToCart_ShouldCallCartMethodAndRedirect() throws Exception {
        // given
        BookDto book = new BookDto();
        book.setId(1L);

        given(bookServiceMock.findById(1L))
                .willReturn(book);

        // when
        // then
        mockMvc.perform(get("/cart/add")
                .param("bookId", "1")
        )
                .andExpect(status().is3xxRedirection());

        verify(bookServiceMock, times(1)).findById(1L);
    }

    @Test
    public void removeFromCart_ShouldCallCartMethodAndRedirect() throws Exception {
        // given
        Long bookId = 1L;
        BookDto book = new BookDto();
        book.setId(bookId);

        given(bookServiceMock.findById(bookId))
                .willReturn(book);

        // when
        // then
        mockMvc.perform(get("/cart/remove")
                .param("id", "1")
        )
                .andExpect(status().is3xxRedirection());

        verify(bookServiceMock, times(1)).findById(1L);
        verify(cartMock, times(1)).removeBook(any());
    }

    @Test
    public void updateBookQuantity_ShouldCallCartMethodAndRedirect() throws Exception {
        // given
        int bookQuantity = 5;
        Long bookId = 1L;
        BookDto book = new BookDto();
        book.setId(bookId);

        given(bookServiceMock.findById(bookId))
                .willReturn(book);

        // when
        // then
        mockMvc.perform(get("/cart/updateQuantity")
                .param("bookId", "1")
                .param("quantity", String.valueOf(bookQuantity))
        )
                .andExpect(status().is3xxRedirection());

        verify(bookServiceMock, times(1)).findById(1L);
    }

}