package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.book.BookService;
import com.machnickiadrian.webstore.book.dto.AuthorDto;
import com.machnickiadrian.webstore.book.dto.BookDetailsDto;
import com.machnickiadrian.webstore.book.dto.BookDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adrian Machnicki
 */
public class AdminBooksControllerTest {

    MockMvc mockMvc;

    @Mock
    BookService bookServiceMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new AdminBooksController(bookServiceMock))
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getBooksManagement_withoutRequestParamsShouldReturnAllBooks() throws Exception {
        // given
        List<BookDto> books = prepareBooksList();
        given(bookServiceMock.findAll())
                .willReturn(books);

        // when
        // then
        mockMvc.perform(get("/admin/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/books"))
                .andExpect(forwardedUrl("/view/admin/books.jsp"))
                .andExpect(model().attribute("books", books));

        verify(bookServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void getBooksManagement_withSearchParamShouldReturnSearchedBooks() throws Exception {
        // given
        List<BookDto> allBooks = prepareBooksList();
        String phrase = "kowalski";
        given(bookServiceMock.search(phrase))
                .willReturn(allBooks);

        // when
        // then
        mockMvc.perform(get("/admin/books?search=" + phrase))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/books"))
                .andExpect(forwardedUrl("/view/admin/books.jsp"))
                .andExpect(model().attribute("books", allBooks));

        verify(bookServiceMock, times(1)).search(phrase);
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void getAddBookForm_ShouldReturnAddBookForm() throws Exception {
        // given
        List<AuthorDto> expectedBookAuthors = new ArrayList<>(Arrays.asList(new AuthorDto()));
        BookDto expectedBook = new BookDto();
        expectedBook.setAuthors(expectedBookAuthors);

        // when
        // then
        mockMvc.perform(get("/admin/books/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/add-update-book"))
                .andExpect(forwardedUrl("/view/admin/add-update-book.jsp"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void addAuthorToForm_ShouldRedirect() throws Exception {
        // given
        BookDto book = createBook();

        // when
        // then
        mockMvc.perform(post("/admin/books/add/addAuthor")
                .flashAttr("book", book)
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void addBook_WhenBookIsValidShouldCallServiceAndRedirect() throws Exception {
        // given
        BookDto book = createBook();

        // when
        // then
        mockMvc.perform(post("/admin/books/add")
                .flashAttr("book", book)
        )
                .andExpect(status().is3xxRedirection());

        verify(bookServiceMock, times(1)).save(any());
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void addBook_WhenBookIsNotValidShouldReturnViewBack() throws Exception {
        // given
        BookDto book = new BookDto();

        // when
        // then
        mockMvc.perform(post("/admin/books/add")
                .flashAttr("book", book)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("admin/add-update-book"))
                .andExpect(forwardedUrl("/view/admin/add-update-book.jsp"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void getUpdateBookForm_ShouldReturnUpdateBookForm() throws Exception {
        // given
        List<AuthorDto> expectedBookAuthors = new ArrayList<>(Arrays.asList(new AuthorDto()));
        BookDto expectedBook = new BookDto();
        expectedBook.setAuthors(expectedBookAuthors);
        given(bookServiceMock.findById(1L))
                .willReturn(expectedBook);

        // when
        // then
        mockMvc.perform(get("/admin/books/update?bookId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/add-update-book"))
                .andExpect(forwardedUrl("/view/admin/add-update-book.jsp"))
                .andExpect(model().attributeExists("book"));

        verify(bookServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void updateBook_ShouldCallServiceAndRedirect() throws Exception {
        // given
        BookDto book = new BookDto();

        // when
        // then
        mockMvc.perform(post("/admin/books/update")
                .flashAttr("book", book)
        )
                .andExpect(status().is3xxRedirection());

        verify(bookServiceMock, times(1)).save(any());
        verifyNoMoreInteractions(bookServiceMock);
    }

    @Test
    public void deleteBook_ShouldRedirectToAdminBooksManagementPage() throws Exception {
        // given
        Long bookId = 1L;

        // when
        // then
        mockMvc.perform(get("/admin/books/delete?bookId=" + bookId))
                .andExpect(status().is3xxRedirection());
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

    private BookDto createBook() {
        AuthorDto author = new AuthorDto();
        author.setId(1L);
        author.setFirstName("First");
        author.setLastName("last");
        List<AuthorDto> authors = new ArrayList<>();
        authors.add(author);
        BookDto book = new BookDto();
        book.setId(1L);
        book.setTitle("Spring in action");
        book.setAuthors(authors);
        book.setPrice(29.99);
        book.setAmount(100);
        book.setDetails(new BookDetailsDto());
        return book;
    }

}