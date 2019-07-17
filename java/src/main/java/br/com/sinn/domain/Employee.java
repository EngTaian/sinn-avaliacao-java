package br.com.sinn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sinn.domain.enums.JobRole;
import br.com.sinn.services.validation.EmployeeInsert;


@Entity(name="TB_EMPREGADOS")
@EmployeeInsert
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonProperty("nome_empregado")
	@Column(name="NOME")
	private String name;
	@JsonProperty("cpf_empregado")
	@Column(name="CPF")
	private String cpf;
	@JsonProperty("cargo")
	@Column(name="ID_JOB_ROLE")
	private Integer jobRole;
	@JsonProperty("salario")
	@Column(name="SALARIO")
	private Double salary;
	
	@JsonProperty("adiantamento")
	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Advance> advances = new ArrayList<Advance>();
	
	@JsonProperty("empresa")
	@ManyToOne
	@JoinTable(name="TB_EMPRESAS_TB_EMPREGADOS", joinColumns = @JoinColumn(name="employee_id"), inverseJoinColumns = @JoinColumn(name="enterprise_id"))
	private Enterprise enterprise;
	
	public Employee() {
		super();
	}

	public Employee(Integer id, String name, String cpf, JobRole jobRole, Double salary, Enterprise enterprise) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.jobRole = (jobRole == null) ? null : jobRole.getId();
		this.salary = salary;
		this.enterprise = enterprise;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Integer getJobRole() {
		return jobRole;
	}

	public Double getSalary() {
		return salary;
	}

	public List<Advance> getAdvances() {
		return advances;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setJobRole(Integer jobRole) {
		this.jobRole = jobRole;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setAdvances(List<Advance> advances) {
		this.advances = advances;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}
