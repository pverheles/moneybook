package com.moneyrama.constants;

public enum EntityStates {
	ACTIVE('A'),
	CLOSED('C');
		
	private final Character code;

	public Character getCode() {
		return code;
	}

	private EntityStates(Character code) {
		this.code = code;
	}	
}
