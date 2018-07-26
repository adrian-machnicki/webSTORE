package com.machnickiadrian.webstore.controller;


import com.machnickiadrian.webstore.dto.UserProfileDto;
import com.machnickiadrian.webstore.dto.UserRegisterDto;
import com.machnickiadrian.webstore.service.BookService;
import com.machnickiadrian.webstore.service.OrderService;
import com.machnickiadrian.webstore.service.UserService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adrian Machnicki
 */
public class UserControllerTest {

    MockMvc mockMvc;

    @Mock
    UserService userServiceMock;

    @Mock
    BookService bookServiceMock;

    @Mock
    OrderService orderServiceMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userServiceMock, bookServiceMock, orderServiceMock))
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void login_ShouldRenderLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl("/view/login.jsp"));
    }

    @Test
    public void showRegistrationForm_ShouldRenderRegisterView() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(forwardedUrl("/view/register.jsp"))
                .andExpect(model().attributeExists("userDto"));
    }

    @Test
    public void register_WithValidUserDtoAndUniqueEmailAndUsernameShouldRedirect() throws Exception {
        // given
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("akowalski");
        dto.setPassword("password");
        dto.setPasswordConfirm("password");
        dto.setEmail("ak@email.com");

        // when
        // then
        mockMvc.perform(post("/register")
                .flashAttr("userDto", dto)
        )
                .andExpect(status().is3xxRedirection());

        verify(userServiceMock, times(1)).registerNewUser(any());
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void saveProfile_WithValidDtoShouldCallServiceAndRedirect() throws Exception {
        // given
        UserProfileDto dto = new UserProfileDto();
        dto.setId(1L);
        dto.setFirstName("Adam");
        dto.setSecondName("Piotr");
        dto.setLastName("Kowalski");
        dto.setEmail("ak@email.com");
        dto.setAddress("Piotrkowska 1");
        dto.setCity("Lodz");

        // when
        // then
        mockMvc.perform(post("/user/profile")
                .flashAttr("user", dto)
        )
                .andExpect(status().is3xxRedirection());
    }

}