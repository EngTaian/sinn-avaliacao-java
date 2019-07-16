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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="TB_PLANO_PARCELAMENTO")
public class InstallmentPlan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonProperty("numero_de_parcelas")
	@Column(name = "NUMERO_DE_PARCELAS")
	private Integer numberOfInstallments;
	@JsonProperty("valor_a_ser_devolvido")
	@Column(name = "VALOR_A_SER_DEVOLVIDO")
	private Double valueToBeReturned;
	
	@JsonIgnore
	@JsonProperty("parcelas")
	@OneToMany(mappedBy = "installmentPlan", cascade = CascadeType.ALL)
	List<Installments> installments = new ArrayList<Installments>();
		
	@JsonProperty("adiantamento")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "advance_id")
	@MapsId
	private Advance advance;

	public InstallmentPlan() {
		super();
	}

	public InstallmentPlan(Integer id, Integer numberOfInstallments, Double valueToBeReturned, Advance advance) {
		super();
		this.id = id;
		this.numberOfInstallments = numberOfInstallments;
		this.valueToBeReturned = valueToBeReturned;
		this.advance = advance;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public Double getValueToBeReturned() {
		return valueToBeReturned;
	}

	public List<Installments> getInstallments() {
		return installments;
	}

	public Advance getAdvance() {
		return advance;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

	public void setValueToBeReturned(Double valueToBeReturned) {
		this.valueToBeReturned = valueToBeReturned;
	}

	public void setInstallments(List<Installments> installments) {
		this.installments = installments;
	}

	public void setAdvance(Advance advance) {
		this.advance = advance;
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
		InstallmentPlan other = (InstallmentPlan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
