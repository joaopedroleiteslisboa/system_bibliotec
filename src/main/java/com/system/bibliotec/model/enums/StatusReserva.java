package com.system.bibliotec.model.enums;

public enum StatusReserva {

	ATIVA("Ativa"), CANCELADA("Cancelada"), FINALIZADA("Finalizada");
	
	private String statusStatusReserva;

	StatusReserva(String statusStatusReserva) {

		this.statusStatusReserva = statusStatusReserva;
	}

	public String getstatusStatusReserva() {
		return statusStatusReserva;
	}


	
}
