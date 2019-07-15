package br.com.sinn.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.sinn.controller.EnterpriseController;
import br.com.sinn.domain.Enterprise;
import br.com.sinn.services.ServiceEnterprise;

public class EnterpriseControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private ServiceEnterprise serviceEnterprise;
	
	@InjectMocks
	private EnterpriseController controller;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();
	}
	
	@Test
	public void getListAllEnterprises() throws Exception{
		List<Enterprise> enterprises = Arrays.asList(mockData(), mockData());
		Mockito.when(serviceEnterprise.findAll()).thenReturn(enterprises);
		mockMvc.perform(get("/enterprises"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));	
		verify(serviceEnterprise, times(1)).findAll();
		verifyNoMoreInteractions(serviceEnterprise);
	}
	
	
	
	private Enterprise mockData() {
		return new Enterprise(null, "Doe", "12345678900123", "John Doe");
	}
	
	
}
