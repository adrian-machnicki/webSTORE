package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.exception.BookNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Adrian Machnicki
 */
public class GlobalExceptionHandlerTest {

    GlobalExceptionHandler exceptionHandler;

    @Before
    public void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void handleBookNotFoundException_ShouldReturnViewName() {
        // given
        String expectedViewName = "error/book-not-found";
        Long bookId = 100L;

        // when
        String actualViewName = exceptionHandler.handleBookNotFoundException(new BookNotFoundException(bookId), new ExtendedModelMap());

        // then
        assertEquals("Expected view name should be equal to 'error/book-not-found'", expectedViewName, actualViewName);
    }

}