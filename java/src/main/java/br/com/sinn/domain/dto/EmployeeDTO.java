package br.com.sinn.domain.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.sinn.domain.Enterprise;

public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="field required")
	@Length(min=5, max=120, message="field required size min=5 and max=120")
	private String name;
	@NotEmpty(message="field required")
	private Integer jobRole;
	@NotEmpty(message="field required")
	private Double salary;
	@NotEmpty(message="field required")
	private Enterprise enterprise;
	

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(Integer id, String name, Integer jobRole, Double salary, Enterprise enterprise) {
		super();
		this.id = id;
		this.name = name;
		this.jobRole = jobRole;
		this.salary = salary;
		this.enterprise = enterprise;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getJobRole() {
		return jobRole;
	}

	public Double getSalary() {
		return salary;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJobRole(Integer jobRole) {
		this.jobRole = jobRole;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}	
}
