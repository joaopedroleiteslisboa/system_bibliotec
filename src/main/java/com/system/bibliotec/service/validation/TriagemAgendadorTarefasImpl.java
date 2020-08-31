package com.system.bibliotec.service.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class TriagemAgendadorTarefasImpl implements ITriagemAgendadorTarefas {


	
	@Override
	public boolean isContainsSpecialChar(String msg) {
		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

		return (regex.matcher(msg).find()) ? true : false;
	}

	@Override
	public boolean qtdEspacosValidos(String texto) {
		return (texto.length() - texto.replaceAll(" ", "").length() > 1) ? false : true;
	}

	@Override
	public boolean jobNameIsCamelCaseValid(String jobName) {
		// TODO Auto-generated method stub

		return (jobName != null && !jobName.isEmpty() && jobName.contains(" ")) ? true : false;
	}

}
