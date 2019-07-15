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

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity(name="EMPRESA")
public class Enterprise implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@JsonProperty("nome_empresa")
	@Column(name="NOME_EMPRESA")
	private String name;
	@JsonProperty("cnpj_empresa")
	@Column(name="CNPJ_EMPRESA")
	private String cnpj;
	@JsonProperty("dono_empresa")
	@Column(name="DONO_EMPRESA")
	private String businessOwner;
	
	@OneToMany(mappedBy="enterprise", cascade = CascadeType.ALL)
	List<Employee> employees = new ArrayList<Employee>();
	
	public Enterprise() {
		super();
	}

	public Enterprise(Integer id, String name, String cnpj, String businessOwner) {
		super();
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.businessOwner = businessOwner;
		
	}

	public Integer getId() {
		return id;
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

	public void setId(Integer id) {
		this.id = id;
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
		Enterprise other = (Enterprise) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}


