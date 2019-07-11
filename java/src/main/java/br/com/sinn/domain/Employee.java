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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sinn.domain.enums.JobRole;
import lombok.Data;

@Data
@Entity
//@Table(name="TB_FUNCIONARIO")
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Advance> advances = new ArrayList<>();
	
	public Employee() {
		super();
	}

	public Employee(Integer id, String name, String cpf, JobRole jobRole, Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.jobRole = (jobRole == null) ? null : jobRole.getId();
		this.salary = salary;
	}	
}
