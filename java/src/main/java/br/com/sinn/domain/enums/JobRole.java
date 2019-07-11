package br.com.sinn.domain.enums;

import java.io.Serializable;


public enum JobRole implements Serializable {
	
	DIRECTOR(1, "Diretor"),
	INTERN(2, "Estagiário"),
	MANAGER(3, "GERENTE");
	
	private Integer id;
	private String name;	
	
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private JobRole(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static JobRole toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for(JobRole x: JobRole.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
