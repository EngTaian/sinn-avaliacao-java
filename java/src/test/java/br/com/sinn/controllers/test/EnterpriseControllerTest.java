package br.com.sinn.controllers.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
	
	private static final String[] CPF = {"89943227000184","61192416000143"};
	
	private MockMvc mockMvc;
	
	@Mock
	private ServiceEnterprise mockService;
	
	@InjectMocks
	private EnterpriseController mockController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mockController).build();
	}
	
//	@Test
//	public void findAllSuccessTest() throws Exception{
////		List<Enterprise> obj = Arrays.asList(getData(), getData());
//		Mockito.when(mockService.findAll()).thenReturn(obj);
//		mockMvc.perform(get("/enterprises"))
//			.andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//			//.andExpect(jsonPath("$", hasSize(2)))
//			.andExpect(jsonPath("$[0].id", is(1)))
//			.andExpect(jsonPath("$[0].nome_empresa", is("Teste")))
//			.andExpect(jsonPath("$[0].cnpj_empresa", is("12345678900123")))
//			.andExpect(jsonPath("$[0].dono_empresa", is("Teste")))
//			.andExpect(jsonPath("$[0].id", is(2)))
//			.andExpect(jsonPath("$[0].nome_empresa", is("Teste")))
//			.andExpect(jsonPath("$[0].cnpj_empresa", is("12345678900123")))
//			.andExpect(jsonPath("$[0].dono_empresa", is("Teste")));
//		verify(mockService, times(1)).findAll();
//		verifyNoMoreInteractions(mockService);		
//	}
//	
//	private Enterprise getData() {
//		for()
//		return new Enterprise(1, "Teste", "12345678900123", "Teste");
//	}
//		 
}
