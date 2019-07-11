package br.com.sinn.domain.enums;

public enum PaymentState {
	
	PENDING(1, "Pendente"),
	DONE(2, "Quitado");
	
	private Integer id;
	private String description;
	
	
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	private PaymentState(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static PaymentState toEnum(Integer id) {
		if(id==null) return null;
		
		for(PaymentState x : PaymentState.values()) {
			if(id.equals(x.getId()))
				return x;
		}
		throw new IllegalArgumentException("Estado de pagamento incorreto");
	}
	
	
}
