package br.com.sinn.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
//@Table(name="TB_EMPRESA")
public class Enterprise implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	
}

