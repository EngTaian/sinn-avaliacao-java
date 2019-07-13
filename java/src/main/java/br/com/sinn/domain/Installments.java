package br.com.sinn.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Installments implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NUMERO_PARCELA")
	private Integer numberInstallment;
	@Column(name = "VALOR_PARCELA")
	private Double valueInstallment;
	@Column(name = "DATA_PAGAMENTO")
	private LocalDate paymentDate;

	@ManyToOne
	@JoinTable(name = "installmentPlan_id", joinColumns = @JoinColumn(name = "installments_id"), inverseJoinColumns = @JoinColumn(name = "installmentsPlan_id"))
	private InstallmentPlan installmentPlan;

	public Installments(Integer id, Integer numberInstallment, Double valueInstallment, LocalDate paymentDate,
			InstallmentPlan installmentPlan) {
		super();
		this.id = id;
		this.numberInstallment = numberInstallment;
		this.valueInstallment = valueInstallment;
		this.paymentDate = paymentDate;
		this.installmentPlan = installmentPlan;
	}

	public Installments() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getNumberInstallment() {
		return numberInstallment;
	}

	public Double getValueInstallment() {
		return valueInstallment;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public InstallmentPlan getInstallmentPlan() {
		return installmentPlan;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumberInstallment(Integer numberInstallment) {
		this.numberInstallment = numberInstallment;
	}

	public void setValueInstallment(Double valueInstallment) {
		this.valueInstallment = valueInstallment;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
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
		Installments other = (Installments) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
