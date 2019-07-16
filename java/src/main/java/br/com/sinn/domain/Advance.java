package br.com.sinn.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity(name="TB_ADIANTAMENTO")
public class Advance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonProperty("valor")
	@Column(name="VALOR")
	private Double value;
	@JsonProperty("data_adiantamento")
	@Column(name="DATA_ADIANTAMENTO")
	private LocalDate advanceDate;
	@JsonProperty("data_devolucao_total")
	@Column(name="DATA_DEVOLUCAO_TOTAL")
	private LocalDate totalReturnDate;
	@JsonProperty("quitado")
	@Column(name="QUITADO")
	private Boolean settled;
	
	
	@JsonProperty("funcionario")
	@ManyToOne
	@JoinTable(name="TB_EMPREGADOS_TB_ADIANTAMENTO", joinColumns=@JoinColumn(name="advance_id"), inverseJoinColumns = @JoinColumn(name="employee_id") )
	private Employee employee;
	
	@JsonProperty("planos_de_pagamento")
	@OneToOne(mappedBy = "advance", cascade = CascadeType.ALL)
	private InstallmentPlan installmentPlan;

	public Advance() {
		super();
	}

	public Advance(Integer id, Double value, LocalDate advanceDate, LocalDate totalReturnDate,
			Boolean settled, Employee employee) {
		super();
		this.id = id;
		this.value = value;
		this.advanceDate = advanceDate;
		this.totalReturnDate = totalReturnDate;
		this.settled = settled;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public Double getValue() {
		return value;
	}

	public LocalDate getAdvanceDate() {
		return advanceDate;
	}

	public LocalDate getTotalReturnDate() {
		return totalReturnDate;
	}

	public Boolean getSettled() {
		return settled;
	}

	public Employee getEmployee() {
		return employee;
	}

	public InstallmentPlan getInstallmentPlan() {
		return installmentPlan;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setAdvanceDate(LocalDate advanceDate) {
		this.advanceDate = advanceDate;
	}

	public void setTotalReturnDate(LocalDate totalReturnDate) {
		this.totalReturnDate = totalReturnDate;
	}

	public void setSettled(Boolean settled) {
		this.settled = settled;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setInstallmentPlan(InstallmentPlan installmentPlan) {
		this.installmentPlan = installmentPlan;
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
		Advance other = (Advance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
