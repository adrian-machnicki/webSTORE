package com.machnickiadrian.webstore.controller;

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
public class InformationControllerTest {

    MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new InformationController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void getInformation_shouldRenderView() throws Exception {
        mockMvc.perform(get("/information"))
                .andExpect(status().isOk())
                .andExpect(view().name("information/information"))
                .andExpect(forwardedUrl("/view/information/information.jsp"));
    }

}