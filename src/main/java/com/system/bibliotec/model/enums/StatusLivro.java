package com.system.bibliotec.model.enums;

public enum StatusLivro {

	AVARIADO("Avariado"), RESERVADO("Reservado"), LOCADO("Locado"), LIVRE("Livre");

	private String statusReservado;

	StatusLivro(String statusReservado) {

		this.statusReservado = statusReservado;
	}

	public String getstatusReservado() {
		return statusReservado;
	}

	

}
