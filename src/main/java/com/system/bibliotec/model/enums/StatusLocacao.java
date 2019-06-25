package com.system.bibliotec.model.enums;

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


}
