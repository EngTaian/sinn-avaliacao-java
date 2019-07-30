package br.com.sinn.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sinn.domain.Employee;
import br.com.sinn.services.validation.EnterpriseInsert;

@EnterpriseInsert
public class EnterpriseNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@JsonProperty("nome_empresa")
	@Column(name="NOME_EMPRESA")
	@NotEmpty(message="field required")
	@Length(min=5, max=120, message="field required size min=5 and max=120")
	private String name;
	
	@NotEmpty(message="field required")
	@JsonProperty("cnpj_empresa")
	@Column(name="CNPJ_EMPRESA")
	private String cnpj;
	
	@NotEmpty(message="field required")
	@Length(min=5, max=120, message="field required size min=5 and max=120")
	@JsonProperty("dono_empresa")
	@Column(name="DONO_EMPRESA")
	private String businessOwner;
	
	@JsonProperty("funcionarios")
	@JsonBackReference
	@OneToMany(mappedBy="enterprise", cascade = CascadeType.ALL)
	List<Employee> employees = new ArrayList<Employee>();
	
	public EnterpriseNewDTO() {
		super();
	}

	public EnterpriseNewDTO(String name, String cnpj, String businessOwner) {
		super();
		this.name = name;
		this.cnpj = cnpj;
		this.businessOwner = businessOwner;
		
	}

	public String getName() {
		return name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getBusinessOwner() {
		return businessOwner;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}	
}


