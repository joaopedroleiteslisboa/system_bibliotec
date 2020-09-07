package com.system.bibliotec.service;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.system.bibliotec.exception.TrabalhoException;
import com.system.bibliotec.exception.TrabalhoExistenteException;
import com.system.bibliotec.exception.TrabalhoInvalidoException;
import com.system.bibliotec.service.automacao.QuartzService;
import com.system.bibliotec.service.automacao.trabalhos.CronJob;
import com.system.bibliotec.service.mapper.MapeadorAgendamento;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;
import com.system.bibliotec.service.ultis.ConverterStringCamelCase;
import com.system.bibliotec.service.validation.ITriagemAgendadorTarefas;
import com.system.bibliotec.service.vm.AgendamentoVM;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgendadorService implements IAuditorTokenDeUsuarioDoContexto {

	private final QuartzService apiQuartzService;

	private final ServicoDoSistema servicoSistema;

	private final ITriagemAgendadorTarefas triagemDoAgendador;

	private static final String excecaoImplementacaoTarefaNaoEncontrada = "ClassNotFoundException: Exceção ao procurar implementação da Tarefa";

	@Autowired
	public AgendadorService(QuartzService quartzService, ServicoDoSistema servicoSistema,
			ITriagemAgendadorTarefas triagemDoAgendador) {

		this.apiQuartzService = quartzService;
		this.servicoSistema = servicoSistema;
		this.triagemDoAgendador = triagemDoAgendador;

	}

	
		
		

	
	
	
	
	
	
	
	/*
	 * public ResponseEntity<AgendamentoVM> updateJob( String jobName, Date
	 * jobScheduleTime, String cronExpression){
	 * 
	 * 
	 * if(jobName == null || jobName.trim().equals("")){ throw new
	 * TrabalhoException("Operação não permitida. É Necessario informar o nome do Trabalho a ser atualizado"
	 * ); }
	 * 
	 * // if(jobExistente(jobName)){ //verifica se o trabalho é baseado em Data ...
	 * if(cronExpression == null || cronExpression.trim().equals("")){
	 * 
	 * //Single Trigger boolean status = apiQuartzService.updateOneTimeJob(jobName,
	 * jobScheduleTime); if(status){ return
	 * MapeadorAgendamento.mapperResponse(apiQuartzService.getAllJobs(),
	 * HttpStatus.OK); }else{ return
	 * MapeadorAgendamento.mapperResponse(apiQuartzService.getAllJobs(),
	 * HttpStatus.OK); }
	 * 
	 * }else{
	 * 
	 * //Detecta que o trabalho é baseado em CRON
	 * 
	 * //Cron Trigger boolean status = jobService.updateCronJob(jobName,
	 * jobScheduleTime, cronExpression); if(status){ return
	 * getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
	 * }else{ return getServerResponse(ServerResponseCode.ERROR, false); } }
	 * 
	 * 
	 * }else{ return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
	 * } }
	 */
	
	
	

}
