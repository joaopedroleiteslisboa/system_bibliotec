package com.system.bibliotec.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoFuncionario {

	ATENDENTE("Atendente"),

	BIBLIOTECARIO("Bibliotecario"),

	DOCENTE("Docente");

	private String tipoFuncionariodo;

	TipoFuncionario(String tipoFuncionariodo) {

		this.tipoFuncionariodo = tipoFuncionariodo;
	}

	public String gettipoFuncionariodo() {
		return tipoFuncionariodo;
	}

	public void settipoFuncionariodo(String tipoFuncionariodo) {
		this.tipoFuncionariodo = tipoFuncionariodo;
	}

	@JsonCreator
	public static TipoFuncionario fromValueString(String tipoFuncionariodo) {
		if (tipoFuncionariodo == null) {
			throw new IllegalArgumentException();
		}
		for (TipoFuncionario tipoFuncionariodoSalved : values()) {
			if (tipoFuncionariodo.equals(tipoFuncionariodoSalved.gettipoFuncionariodo())) {
				return tipoFuncionariodoSalved;
			}
		}
		throw new IllegalArgumentException();
	}

}
