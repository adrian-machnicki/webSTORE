package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.user.UserService;
import com.machnickiadrian.webstore.user.dto.UserDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adrian Machnicki
 */
public class AdminUsersControllerTest {

    MockMvc mockMvc;

    @Mock
    UserService userServiceMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new AdminUsersController(userServiceMock))
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getUsersManagement_withoutRequestParamsShouldReturnAllUsers() throws Exception {
        // given
        List<UserDto> users = new ArrayList<>(Arrays.asList(new UserDto(), new UserDto()));

        given(userServiceMock.findAll())
                .willReturn(users);

        // when
        // then
        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/users"))
                .andExpect(forwardedUrl("/view/admin/users.jsp"))
                .andExpect(model().attribute("users", users));

        verify(userServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void getUsersManagement_withSearchParamShouldReturnSearchedUsers() throws Exception {
        // given
        String searchPhrase = "kowalski";
        List<UserDto> users = new ArrayList<>(Arrays.asList(new UserDto(), new UserDto()));

        given(userServiceMock.search(searchPhrase))
                .willReturn(users);

        // when
        // then
        mockMvc.perform(get("/admin/users?search=" + searchPhrase))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/users"))
                .andExpect(forwardedUrl("/view/admin/users.jsp"))
                .andExpect(model().attribute("users", users));

        verify(userServiceMock, times(1)).search(searchPhrase);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void blockOrUnblockUser_ShouldCallServiceAndRedirect() throws Exception {
        // given
        Long userId = 1L;

        // when
        // then
        mockMvc.perform(get("/admin/users/block-unblock?id=" + userId))
                .andExpect(status().is3xxRedirection());

        verify(userServiceMock, times(1)).setEnabledDisabled(userId);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void removeUser_ShouldCallServiceAndRedirect() throws Exception {
        // given
        Long userId = 1L;

        // when
        // then
        mockMvc.perform(get("/admin/users/remove?id=" + userId))
                .andExpect(status().is3xxRedirection());

        verify(userServiceMock, times(1)).deleteById(userId);
        verifyNoMoreInteractions(userServiceMock);
    }

}