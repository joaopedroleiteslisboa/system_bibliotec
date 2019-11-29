package com.system.bibliotec.model.enums;

public enum StatusLocacao {

	ATRASADA("Atrasada"), ATIVA("Ativa"), CANCELADA("Cancelada"), FINALIZADA("Finalizada");

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
