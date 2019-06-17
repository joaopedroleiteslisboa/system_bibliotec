package com.system.bibliotec.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCliente {

	FISICA("Fisica"), JURIDICA("Juridica");

	private String tipoCliente;

	TipoCliente(String tipoCliente) {

		this.tipoCliente = tipoCliente;
	}

	public String gettipoCliente() {
		return tipoCliente;
	}

	public void settipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	@JsonCreator
	public static TipoCliente fromValueString(String tipoCliente) {
		if (tipoCliente == null) {
			throw new IllegalArgumentException();
		}
		for (TipoCliente tipoClienteSalved : values()) {
			if (tipoCliente.equals(tipoClienteSalved.gettipoCliente())) {
				return tipoClienteSalved;
			}
		}
		throw new IllegalArgumentException();
	}

}
