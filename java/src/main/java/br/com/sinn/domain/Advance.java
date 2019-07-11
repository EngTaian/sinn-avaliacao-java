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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
//@Table(name="TB_ADIANTAMENTO")
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
	private Double settled;
	
	@ManyToOne
	@JoinTable(name="EMPLOYEE_ADVANCE", joinColumns=@JoinColumn(name="advance_id"), inverseJoinColumns = @JoinColumn(name="employee_id") )
	private Employee employee;
	
	@OneToOne(mappedBy = "advance", cascade = CascadeType.ALL)
	private InstallmentPlan installmentPlan;

	public Advance() {
		super();
	}

	public Advance(Integer id, Double value, LocalDate advanceDate, LocalDate totalReturnDate,
			Double settled, Employee employee) {
		super();
		this.id = id;
		this.value = value;
		this.advanceDate = advanceDate;
		this.totalReturnDate = totalReturnDate;
		this.settled = settled;
		this.employee = employee;
	}
	
	
	
}
