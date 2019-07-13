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

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@Entity
public class InstallmentPlan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonProperty("numero_de_parcelas")
	@Column(name="NUMERO_DE_PARCELAS")
	private Integer numberOfInstallments;
	@JsonProperty("valor_a_ser_devolvido")
	@Column(name="VALOR_A_SER_DEVOLVIDO")
	private Double valueToBeReturned;
	@OneToMany(mappedBy = "installmentPlan", cascade = CascadeType.ALL)
	List<Installments> installments = new ArrayList<Installments>();
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="advance_id")
	@MapsId	
	private Advance advance;
	
	
	
}
