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
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Table(name="TB_PARCELAS")
public class Installments implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="VALOR_PARCELA")
	private Double valueInstallment;
	@Column(name="DATA_PAGAMENTO")
	private LocalDate paymentDate;
	
	@ManyToOne
	@JoinTable(name="installmentPlan_id", joinColumns = @JoinColumn(name="installments_id"), inverseJoinColumns = @JoinColumn(name="installmentsPlan_id"))
	private InstallmentPlan installmentPlan;
	
	
	
}
