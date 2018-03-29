package com.machnickiadrian.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class HomeControllerTest {
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController())
        		.setViewResolvers(viewResolver)
        		.build();
	}

	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(view().name("home"));
	}

	@Test
	public void testHomePage2() throws Exception {
		mockMvc.perform(get("/home"))
			.andExpect(view().name("home"));
	}

}
