package br.com.sinn.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.sinn.configuracoes.AvaliacaoEntrevistaJavaAppInitializer;
import br.com.sinn.controller.EnterpriseController;
import br.com.sinn.domain.Enterprise;
import br.com.sinn.services.ServiceEnterprise;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AvaliacaoEntrevistaJavaAppInitializer.class,ServletConfig.class})
@WebAppConfiguration
public class EnterpriseControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private ServiceEnterprise serviceEnterprise;
	
	@InjectMocks
	private EnterpriseController controller;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders
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
