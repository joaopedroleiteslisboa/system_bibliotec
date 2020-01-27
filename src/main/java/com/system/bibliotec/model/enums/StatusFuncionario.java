package com.system.bibliotec.model.enums;

public enum StatusFuncionario {

	ATIVO("Ativo"), INATIVO("Inativo"), FERIAS("Ferias"), DEMITIDO("Demitido");
	
	
	private String statusFuncionario;

	StatusFuncionario(String statusFuncionario) {

		this.statusFuncionario = statusFuncionario;
	}

	public String getStatusFuncionario() {
		return statusFuncionario;
	}

	public void setStatusFuncionario(String statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}

}
