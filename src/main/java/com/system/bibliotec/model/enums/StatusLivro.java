package com.system.bibliotec.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusLivro {

	AVARIADO("Avariado"), RESERVADO("Reservado"), LOCADO("Locado"), LIVRE("Livre");

	private String statusReservado;

	StatusLivro(String statusReservado) {

		this.statusReservado = statusReservado;
	}

	public String getstatusReservado() {
		return statusReservado;
	}

	public void setstatusReservado(String statusReservado) {
		this.statusReservado = statusReservado;
	}

	@JsonCreator
	public static StatusLivro fromValueString(String statusReservado) {
		if (statusReservado == null) {
			throw new IllegalArgumentException();
		}
		for (StatusLivro statusReservadoSalved : values()) {
			if (statusReservado.equals(statusReservadoSalved.getstatusReservado())) {
				return statusReservadoSalved;
			}
		}
		throw new IllegalArgumentException();
	}

}
