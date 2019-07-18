package br.com.sinn.controllers.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

	private static final String[] CNPJ = { "89943227000184", "61192416000143" };

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

	@Test
	public void findAllSuccessTest() throws Exception {
		List<Enterprise> obj = getData();
		Mockito.when(mockService.findAll()).thenReturn(obj);
		mockMvc.perform(get("/enterprises")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].nome_empresa", is("Teste")))
				.andExpect(jsonPath("$[0].cnpj_empresa", is("89943227000184")))
				.andExpect(jsonPath("$[0].dono_empresa", is("John Doe"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].nome_empresa", is("Teste")))
				.andExpect(jsonPath("$[1].cnpj_empresa", is("61192416000143")))
				.andExpect(jsonPath("$[1].dono_empresa", is("John Doe")));
		verify(mockService, times(1)).findAll();
		verifyNoMoreInteractions(mockService);
	}

	private List<Enterprise> getData() {
		List<Enterprise> enterprise = new ArrayList<Enterprise>();
		Integer id = 0;
		for (int i = 0; i < CNPJ.length; i++) {
			Enterprise obj = new Enterprise(++id, "Teste", CNPJ[i], "John Doe");
			enterprise.add(obj);
		}
		return enterprise;
	}

}
