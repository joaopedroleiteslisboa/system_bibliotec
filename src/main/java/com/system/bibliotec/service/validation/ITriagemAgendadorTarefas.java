package com.system.bibliotec.service.validation;

public interface ITriagemAgendadorTarefas{

	
	boolean isContainsSpecialChar(String jobName);
	boolean jobNameIsCamelCaseValid(String jobName);

	
	default boolean isEmptyOrNull(String jobName) {
		return false;
	}
	
	default boolean qtdEspacosValidos(String jobName) {
		return true;
	}

}
