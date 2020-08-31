package com.system.bibliotec.service.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.service.ServicoDoSistema;
import com.system.bibliotec.service.automacao.QuartzService;

@Component
public class TriagemAgendadorTarefas2 implements ITriagemAgendadorTarefas2{

	private final QuartzService quartzService;
	
	
	
	@Autowired
	public TriagemAgendadorTarefas2(QuartzService quartzService) {
		
		this.quartzService = quartzService;
	

	}

		
	@Override
	public boolean jobExistente(String jobName) {
		// TODO Auto-generated method stub
		return (quartzService.isJobWithNamePresent(jobName)) ? true : false;
	}

	@Override
	public boolean isRunning(String jobName) {

		return (quartzService.isJobRunning(jobName));
	}

	

}
