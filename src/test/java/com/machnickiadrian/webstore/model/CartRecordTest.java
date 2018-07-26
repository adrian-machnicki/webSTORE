package com.machnickiadrian.webstore.model;

import com.machnickiadrian.webstore.dto.BookDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Adrian Machnicki
 */
public class CartRecordTest {

    private CartRecord cartRecord;

    @Before
    public void setUp() {
        cartRecord = new CartRecord();
        BookDto book = new BookDto();
        book.setTitle("Spring");
        book.setPrice(24.99);
        cartRecord.setBook(book);
    }

    @Test
    public void updateQuantity_ShouldUpdateQuantity() {
        // given
        cartRecord.setQuantity(3);

        // when
        cartRecord.updateQuantity(+5);

        // then
        int expectedQuantity = 8;
        int actualQuantity = cartRecord.getQuantity();
        assertEquals("Book quantity should be equal to 8 now", expectedQuantity, actualQuantity);
    }

    @Test
    public void getAmount_ShouldReturnAmount() {
        // given
        cartRecord.setQuantity(3);

        // when
        double actualAmount = cartRecord.getAmount();

        // then
        double expectedAmount = 74.97;
        assertEquals("Amount should be equal 74.97 ", expectedAmount, actualAmount, 0);
    }

}