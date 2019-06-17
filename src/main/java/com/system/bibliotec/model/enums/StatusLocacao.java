package com.system.bibliotec.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusLocacao {

	ATRASADA("Atrasada"), ATIVA("Ativa"), CANCELADA("Cancelada"), DEVOLVIDA("Devolvida");

	private String statusEmprestimo;

	StatusLocacao(String statusEmprestimo) {

		this.statusEmprestimo = statusEmprestimo;
	}

	public String getstatusEmprestimo() {
		return statusEmprestimo;
	}

	public void setstatusEmprestimo(String statusEmprestimo) {
		this.statusEmprestimo = statusEmprestimo;
	}

	@JsonCreator
	public static StatusLocacao fromValueString(String statusEmprestimo) {
		if (statusEmprestimo == null) {
			throw new IllegalArgumentException();
		}
		for (StatusLocacao statusEmprestimoSalved : values()) {
			if (statusEmprestimo.equals(statusEmprestimoSalved.getstatusEmprestimo())) {
				return statusEmprestimoSalved;
			}
		}
		throw new IllegalArgumentException();
	}

}
