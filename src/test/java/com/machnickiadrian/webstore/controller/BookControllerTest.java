package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.dto.AuthorDto;
import com.machnickiadrian.webstore.dto.BookDetailsDto;
import com.machnickiadrian.webstore.dto.BookDto;
import com.machnickiadrian.webstore.model.Cart;
import com.machnickiadrian.webstore.service.BookService;
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
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adrian Machnicki
 */
public class BookControllerTest {

    MockMvc mockMvc;

    @Mock
    BookService bookServiceMock;

    @Mock
    Cart cartMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new BookController(bookServiceMock, cartMock))
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getBooks_WithoutRequestParamsShouldAddBooksToModelAndRenderView() throws Exception {
        // given
        List<BookDto> books = prepareBooksList();
        int page = 0;
        int size = 11;

        given(bookServiceMock.findAll(page, size))
                .willReturn(books);
        given(bookServiceMock.countAll())
                .willReturn(1);
        given(cartMock.getAddedBooksIds())
                .willReturn(new HashSet<>());

        // when
        // then
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/books"))
                .andExpect(forwardedUrl("/view/books/books.jsp"))
                .andExpect(model().attribute("books", hasSize(2)));

        verify(bookServiceMock, times(1)).findAll(page, size);
        verify(bookServiceMock, times(1)).countAll();
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void getBooks_WithSearchParamShouldAddBooksToModelAndRenderView() throws Exception {
        // given
        List<BookDto> books = prepareBooksList();
        String searchPhrase = "kowalski";

        given(bookServiceMock.search(searchPhrase))
                .willReturn(books);
        given(bookServiceMock.countAll())
                .willReturn(1);
        given(cartMock.getAddedBooksIds())
                .willReturn(new HashSet<>());

        // when
        // then
        mockMvc.perform(get("/books?search=kowalski"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/books"))
                .andExpect(forwardedUrl("/view/books/books.jsp"))
                .andExpect(model().attribute("books", hasSize(2)));

        verify(bookServiceMock, times(1)).search(searchPhrase);
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void getBook_ShouldAddBookToModelAndRenderViewIfBookExists() throws Exception {
        // given
        Long bookId = 10L;
        BookDto book = new BookDto();
        book.setId(bookId);
        book.setTitle("Spring in action");

        given(bookServiceMock.findById(bookId))
                .willReturn(book);

        // when
        // then
        mockMvc.perform(get("/books/book/" + bookId))
                .andExpect(status().isOk())
                .andExpect(view().name("books/book"))
                .andExpect(forwardedUrl("/view/books/book.jsp"))
                .andExpect(model().attribute("book", book));

        verify(bookServiceMock, times(1)).findById(bookId);
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void getBook_ShouldReturnNotFound() throws Exception {
        // given
        Long notExistingBookId = 100L;
        BookDto nullBook = null;

        given(bookServiceMock.findById(notExistingBookId))
                .willReturn(nullBook);

        // when
        // then
        mockMvc.perform(get("/books/book/" + notExistingBookId))
                .andExpect(status().isNotFound());

        verify(bookServiceMock, times(1)).findById(notExistingBookId);
        verifyNoMoreInteractions(bookServiceMock);
    }

    private List<BookDto> prepareBooksList() {
        AuthorDto author = new AuthorDto();
        author.setId(1L);
        author.setFirstName("Adam");
        author.setLastName("Kowalski");

        BookDetailsDto details1 = new BookDetailsDto();
        details1.setId(1L);
        details1.setPages(100);
        details1.setDescription("It is a sample book nr 1");

        BookDto book1 = new BookDto();
        book1.setId(1L);
        book1.setTitle("Sample book 1");
        book1.setAuthors(Arrays.asList(author));
        book1.setPrice(29.99);
        book1.setAmount(100);
        book1.setDetails(details1);

        BookDetailsDto details2 = new BookDetailsDto();
        details1.setId(2L);
        details1.setPages(100);
        details1.setDescription("It is a sample book nr 2");

        BookDto book2 = new BookDto();
        book2.setId(2L);
        book2.setTitle("Sample book 2");
        book2.setAuthors(Arrays.asList(author));
        book2.setPrice(29.99);
        book2.setAmount(100);
        book2.setDetails(details2);

        return new ArrayList<>(Arrays.asList(book1, book2));
    }

}