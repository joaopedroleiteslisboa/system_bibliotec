package com.system.bibliotec.model.enums;

public enum StatusReserva {

	ATIVA("Ativa"), CANCELADA("Cancelada"), ALUGADA("Alugada");
	
	private String statusStatusReserva;

	StatusReserva(String statusStatusReserva) {

		this.statusStatusReserva = statusStatusReserva;
	}

	public String getstatusStatusReserva() {
		return statusStatusReserva;
	}


	
}
