package br.com.sinn.controllers.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.sinn.configuracoes.MVCConfig;
import br.com.sinn.controller.EnterpriseController;
import br.com.sinn.controllers.test.util.PageBuilder;
import br.com.sinn.controllers.test.util.TestUtil;
import br.com.sinn.domain.Enterprise;
import br.com.sinn.domain.dto.EnterpriseDTO;
import br.com.sinn.domain.dto.EnterpriseNewDTO;
import br.com.sinn.repository.RepositoryEnterprise;
import br.com.sinn.services.ServiceEnterprise;
import br.com.sinn.services.validation.EnterpriseInsert;
import br.com.sinn.services.validation.EnterpriseInsertValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MVCConfig.class})
@EnableJpaRepositories(basePackages = "br.com.sinn")
@WebAppConfiguration
public class EnterpriseControllerTest {

	private static final String[] CNPJ = { "89943227000184", "61192416000143" };
		
	private MockMvc mockMvc;
	
	@Mock
	ConstraintValidatorContext mockConstraint;
	
	@Mock
	RepositoryEnterprise mockRepo;
	
	@InjectMocks
	EnterpriseInsertValidator mockValidation;
	
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

	@Test
	public void findByIdSuccessTest() throws Exception {
		Enterprise obj = getData().get(0);
		Mockito.when(mockService.findById(Mockito.anyInt())).thenReturn(obj);
		mockMvc.perform(get("/enterprises/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("id", is(1)))
				.andExpect(jsonPath("nome_empresa", is("Teste")))
				.andExpect(jsonPath("cnpj_empresa", is("89943227000184")))
				.andExpect(jsonPath("dono_empresa", is("John Doe")));
		verify(mockService, times(1)).findById(Mockito.anyInt());
		verifyNoMoreInteractions(mockService);
	}

	@Test
	public void findPageSuccessTest() throws Exception {
		Page<Enterprise> obj = getDataPage(CNPJ.length);
		Mockito.when(mockService.findPage(0, 24, "name", "ASC")).thenReturn(obj);
		mockMvc.perform(get("/enterprises/page")).andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].id", is(1)))
				.andExpect(jsonPath("$.content[0].nome_empresa", is("Teste")))
				.andExpect(jsonPath("$.content[0].cnpj_empresa", is("89943227000184")))
				.andExpect(jsonPath("$.content[0].dono_empresa", is("John Doe")))
				.andExpect(jsonPath("$.content[1].id", is(2)))
				.andExpect(jsonPath("$.content[1].nome_empresa", is("Teste")))
				.andExpect(jsonPath("$.content[1].cnpj_empresa", is("61192416000143")))
				.andExpect(jsonPath("$.content[1].dono_empresa", is("John Doe")));
		verify(mockService, times(1)).findPage(0, 24, "name", "ASC");
		verifyNoMoreInteractions(mockService);		
	}
	
	@Test
	public void insertEnterpriseSuccessTest() throws Exception{
		Enterprise obj = getData().get(0);
		EnterpriseNewDTO objNewDTO = new EnterpriseNewDTO("Teste", "89943227000184", "John Doe");
		Mockito.when(mockService.fromNewDTO(objNewDTO)).thenReturn(obj);
		Mockito.when(mockService.insert(obj)).thenReturn(obj);
		Mockito.when(mockValidation.isValid(objNewDTO, mockConstraint)).thenReturn(true) ;
		Mockito.when(mockRepo.findByCnpj("89943227000184")).thenReturn(null);
		mockMvc.perform(post("/enterprises")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(objNewDTO))
		)
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
		verify(mockService, times(1)).insert(obj);
		verifyNoMoreInteractions(mockService);
	}
	
	@Test
	public void updateEnterpriseSuccessTest() throws Exception{
		Enterprise obj = getData().get(0);
		EnterpriseDTO objDto = new EnterpriseDTO(1, "Teste", "John Doe");
		Mockito.when(mockService.fromDTO(objDto, 1)).thenReturn(obj);
		Mockito.when(mockService.update(obj, obj.getId())).thenReturn(obj);
		mockMvc.perform(put("/enterprises/1")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(objDto))
				)
		.andExpect(status().isOk());
	}
	
	
	private Page<Enterprise> getDataPage(Integer numElements) {
		PageRequest pr = new PageRequest(0, 24, Direction.ASC, "name");
		Page<Enterprise> page = new PageBuilder<Enterprise>().elements(getData()).pageRequest(pr)
				.totalElements(numElements).build();
		return page;
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
