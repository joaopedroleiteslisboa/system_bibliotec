package com.system.bibliotec.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusReserva {

	ATIVA("Ativa"), CANCELADA("Cancelada"), ALUGADA("Alugada");
	
	private String statusStatusReserva;

	StatusReserva(String statusStatusReserva) {

		this.statusStatusReserva = statusStatusReserva;
	}

	public String getstatusStatusReserva() {
		return statusStatusReserva;
	}

	public void setstatusStatusReserva(String statusStatusReserva) {
		this.statusStatusReserva = statusStatusReserva;
	}

	@JsonCreator
	public static StatusReserva fromValueString(String statusStatusReserva) {
		if (statusStatusReserva == null) {
			throw new IllegalArgumentException();
		}
		for (StatusReserva statusStatusReservaSalved : values()) {
			if (statusStatusReserva.equals(statusStatusReservaSalved.getstatusStatusReserva())) {
				return statusStatusReservaSalved;
			}
		}
		throw new IllegalArgumentException();
	}
	
}
