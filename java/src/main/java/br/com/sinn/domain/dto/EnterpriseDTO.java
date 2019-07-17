package br.com.sinn.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.sinn.domain.Employee;


public class EnterpriseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="field required")
	@Length(min=5, max=120, message="field required size min=5 and max=120")
	private String name;
	@NotEmpty(message="field required")
	@Length(min=5, max=120, message="field required size min=5 and max=120")
	private String businessOwner;
	private List<Employee> employees = new ArrayList<Employee>(); 
	
	public EnterpriseDTO(Integer id, String name, String businessOwner) {
		super();
		this.id = id;
		this.name = name;
		this.businessOwner = businessOwner;
	}

	public EnterpriseDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBusinessOwner() {
		return businessOwner;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}	
}
