package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.book.BookRepository;
import com.machnickiadrian.webstore.book.BookService;
import com.machnickiadrian.webstore.book.BookServiceImpl;
import com.machnickiadrian.webstore.book.dto.AuthorDto;
import com.machnickiadrian.webstore.book.dto.BookDto;
import com.machnickiadrian.webstore.book.entity.Author;
import com.machnickiadrian.webstore.book.entity.Book;
import com.machnickiadrian.webstore.book.exception.BookNotFoundException;
import com.machnickiadrian.webstore.book.mapper.BookDtoToBookMapper;
import com.machnickiadrian.webstore.book.mapper.BookToBookDtoMapper;
import com.machnickiadrian.webstore.order.dto.OrderDto;
import com.machnickiadrian.webstore.order.dto.OrderRecordDto;
import com.machnickiadrian.webstore.order.entity.OrderService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Adrian Machnicki
 */
public class BookServiceImplTest {

    private BookService bookService;

    @Mock
    BookRepository bookRepositoryMock;

    @Mock
    OrderService orderServiceMock;

    @Mock
    BookToBookDtoMapper bookToBookDtoMapperMock;

    @Mock
    BookDtoToBookMapper bookDtoToBookMapperMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        bookService = new BookServiceImpl(bookRepositoryMock, orderServiceMock,
                bookToBookDtoMapperMock, bookDtoToBookMapperMock);
    }

    @Test
    public void findById_WhenBookExists() {
        // given
        Long id = 1L;
        Book book = new Book();
        book.setId(id);
        BookDto expectedBook = new BookDto();
        expectedBook.setId(id);

        given(bookRepositoryMock.findById(id))
                .willReturn(Optional.of(book));
        given(bookToBookDtoMapperMock.convert(book))
                .willReturn(expectedBook);

        // when
        BookDto actualBook = bookService.findById(id);

        // then
        assertEquals("Book id should be 1", expectedBook.getId(), actualBook.getId());
        verify(bookRepositoryMock, times(1))
                .findById(anyLong());
        verify(bookToBookDtoMapperMock, times(1))
                .convert(any(Book.class));
    }

    @Test(expected = BookNotFoundException.class)
    public void findById_WhenBookNotExists() {
        // given
        Long id = 1L;
        given(bookRepositoryMock.findById(id))
                .willReturn(Optional.empty());

        // when
        bookService.findById(id);

        // then
        // BookNotFoundException expected
        verify(bookRepositoryMock, times(1))
                .findById(anyLong());
        verify(bookToBookDtoMapperMock, never())
                .convert(any(Book.class));
    }

    @Test
    public void findAll_ShouldReturnBooks() {
        // given
        Book book1 = new Book();
        book1.setId(1L);
        BookDto bookDto1 = new BookDto();
        bookDto1.setId(1L);

        Book book2 = new Book();
        book2.setId(2L);
        BookDto bookDto2 = new BookDto();
        bookDto2.setId(2L);

        given(bookRepositoryMock.findAll())
                .willReturn(Arrays.asList(book1, book2));
        given(bookToBookDtoMapperMock.convert(book1))
                .willReturn(bookDto1);
        given(bookToBookDtoMapperMock.convert(book2))
                .willReturn(bookDto2);

        // when
        List<BookDto> actualBooks = bookService.findAll();

        // then
        assertEquals("List containing two books should be returned", 2, actualBooks.size());
        verify(bookRepositoryMock, times(1))
                .findAll();
        verify(bookToBookDtoMapperMock, times(2))
                .convert(any(Book.class));
    }

    @Test
    public void findAll_PaginatedShouldReturnBooks() {
        // given
        int page = 1;
        int size = 10;
        Book book1 = new Book();
        book1.setId(1L);
        BookDto bookDto1 = new BookDto();
        bookDto1.setId(1L);

        Book book2 = new Book();
        book2.setId(2L);
        BookDto bookDto2 = new BookDto();
        bookDto2.setId(2L);

        given(bookRepositoryMock.findAll(PageRequest.of(page, size)))
                .willReturn(new PageImpl<>(Arrays.asList(book1, book2)));
        given(bookToBookDtoMapperMock.convert(book1))
                .willReturn(bookDto1);
        given(bookToBookDtoMapperMock.convert(book2))
                .willReturn(bookDto2);

        // when
        List<BookDto> actualBooks = bookService.findAll(page, size);

        // then
        assertEquals("List containing two books should be returned", 2, actualBooks.size());
        verify(bookRepositoryMock, times(1))
                .findAll(any(PageRequest.class));
        verify(bookToBookDtoMapperMock, times(2))
                .convert(any(Book.class));
    }

    @Test
    public void findAllBoughtByUsername_shouldReturnListOfBooks() {
        // given
        String username = "username";
        List<OrderDto> usersOrders = createUsersOrdersList();
        given(orderServiceMock.findAllByUsername(username))
                .willReturn(usersOrders);

        // when
        List<BookDto> actualBooks = bookService.findAllBoughtByUsername(username);

        // then
        assertEquals("List of books bought by user should contain 3 books", 3, actualBooks.size());
        verify(orderServiceMock, times(1))
                .findAllByUsername(anyString());
    }

    @Test
    public void save_test() {
        // given
        BookDto bookDto = new BookDto();
        given(bookDtoToBookMapperMock.convert(bookDto))
                .willReturn(new Book());

        // when
        bookService.save(bookDto);

        // then
        verify(bookDtoToBookMapperMock, times(1))
                .convert(any(BookDto.class));
        verify(bookRepositoryMock, times(1))
                .save(any(Book.class));
    }

    @Test
    public void deleteById_test() {
        // given
        Long id = 1L;

        // when
        bookService.deleteById(id);

        // then
        verify(bookRepositoryMock, times(1))
                .deleteById(id);
    }

    @Test
    public void search_shouldReturnAppropriateBookList() {
        // given
        String title1 = "Java 8 in Action";
        Book book1 = new Book();
        book1.setTitle(title1);
        book1.setAuthors(Arrays.asList(new Author()));
        BookDto bookDto1 = new BookDto();
        bookDto1.setTitle(title1);
        bookDto1.setAuthors(Arrays.asList(new AuthorDto()));

        String title2 = "Spring in Action";
        Book book2 = new Book();
        book2.setTitle(title2);
        book2.setAuthors(Arrays.asList(new Author()));
        BookDto bookDto2 = new BookDto();
        bookDto2.setTitle(title2);
        bookDto2.setAuthors(Arrays.asList(new AuthorDto()));

        given(bookRepositoryMock.findAll())
                .willReturn(Arrays.asList(book1, book2));
        given(bookToBookDtoMapperMock.convert(book1))
                .willReturn(bookDto1);
        given(bookToBookDtoMapperMock.convert(book2))
                .willReturn(bookDto2);

        // when
        List<BookDto> actualBooks = bookService.search("java");

        // then
        assertEquals("List should contain 1 book", 1, actualBooks.size());
        verify(bookRepositoryMock, times(1))
                .findAll();
        verify(bookToBookDtoMapperMock, times(2))
                .convert(any(Book.class));
    }

    @Test
    public void countAll_ShouldReturnNumberOfBooksAsInt() {
        // given
        long expectedNumberOfBooks = 100;

        // when
        given(bookRepositoryMock.count())
                .willReturn(100L);
        int actualNumberOfBooks = bookService.countAll();

        // then
        assertEquals("Number of books as in should be returned", expectedNumberOfBooks, actualNumberOfBooks);
    }

    private List<OrderDto> createUsersOrdersList() {
        BookDto book1 = new BookDto();
        book1.setId(1L);
        BookDto book2 = new BookDto();
        book2.setId(2L);
        BookDto book3 = new BookDto();
        book3.setId(3L);

        // order 1
        OrderRecordDto record1 = new OrderRecordDto();
        record1.setBook(book1);
        record1.setAmount(1);
        OrderRecordDto record2 = new OrderRecordDto();
        record2.setBook(book2);
        record2.setAmount(1);
        OrderDto order1 = new OrderDto();
        order1.setRecords(Arrays.asList(record1, record2));

        // order 2
        OrderRecordDto record3 = new OrderRecordDto();
        record3.setBook(book3);
        record3.setAmount(1);
        OrderRecordDto record4 = new OrderRecordDto();
        record4.setBook(book2);
        record4.setAmount(1);
        OrderDto order2 = new OrderDto();
        order2.setRecords(Arrays.asList(record3, record4));

        return Arrays.asList(order1, order2);
    }
}