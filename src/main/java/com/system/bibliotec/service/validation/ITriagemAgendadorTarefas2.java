package com.system.bibliotec.service.validation;

import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;

public interface ITriagemAgendadorTarefas2 extends IAuditorTokenDeUsuarioDoContexto {

	boolean jobExistente(String jobName);

	default boolean isRunning(String jobName) {

		return false;
	}

}
