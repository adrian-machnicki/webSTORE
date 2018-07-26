package com.machnickiadrian.webstore.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ViewControllerTest {

    MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new ViewController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getNavbar_ShouldReturnNavbarView() throws Exception {
        mockMvc.perform(get("/view/navbar"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/navbar"))
                .andExpect(forwardedUrl("/view/fragments/navbar.jsp"));
    }

    @Test
    public void getAdminSidebar_ShouldReturnAdminSidebarView() throws Exception {
        mockMvc.perform(get("/view/admin-sidebar"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/admin-sidebar"))
                .andExpect(forwardedUrl("/view/fragments/admin-sidebar.jsp"));
    }

    @Test
    public void getUserSidebar_ShouldReturnUserSidebarView() throws Exception {
        mockMvc.perform(get("/view/user-sidebar"))
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/user-sidebar"))
                .andExpect(forwardedUrl("/view/fragments/user-sidebar.jsp"));
    }

}