package com.machnickiadrian.webstore.controller;

import com.machnickiadrian.webstore.enums.AdminTab;
import com.machnickiadrian.webstore.enums.NavbarTab;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adrian Machnicki
 */
public class AdminControllerTest {

    MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new AdminController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getAdminPanel_ShouldReturnAdminPanel() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin"))
                .andExpect(forwardedUrl("/view/admin/admin.jsp"))
                .andExpect(model().attribute("adminTab", AdminTab.DASHBOARD))
                .andExpect(model().attribute("navbarTab", NavbarTab.ADMIN));
    }

}