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

	public ResponseEntity<AgendamentoVM> scheduleDate(String jobName, Date jobScheduleTime) {
		log.debug("Iniciando processo de criação de nova Tarefa do tipo Data e Hora de rotina da Aplicação." + "Usuario solicitante: "
				+ obterUsuarioDoContextoPeloToken());
		triagemNovaTarefa(jobName);

		Class classe = getClassForName(jobName);

		boolean status = apiQuartzService.scheduleOneTimeJob(jobName, classe, jobScheduleTime);

		if (status) {
			
			return MapeadorAgendamento.mapperResponse(apiQuartzService.getAllJobs(), HttpStatus.OK);
		} else {
			return MapeadorAgendamento.mapperResponse(false, HttpStatus.NOT_MODIFIED);
		}

	}

	public ResponseEntity<AgendamentoVM> scheduleCron(String jobName, Date jobScheduleTime, String cronExpression) {

		triagemNovaTarefa(jobName);

		Class classe = getClassForName(jobName);

		boolean status = apiQuartzService.scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);

		if (status) {
			return MapeadorAgendamento.mapperResponse(apiQuartzService.getAllJobs(), HttpStatus.OK);
		} else {
			return MapeadorAgendamento.mapperResponse(false, HttpStatus.NOT_MODIFIED);
		}

	}

	public ResponseEntity<AgendamentoVM> deletarTarefa(String jobName) {

		log.debug("Iniciando processo de exclusão de Tarefa de rotina da Aplicação." + "Usuario solicitante: "
				+ obterUsuarioDoContextoPeloToken());

		if (jobExistente(jobName)) {
			boolean executando = jobEmExecucao(jobName);

			if (!executando) {
				boolean status = apiQuartzService.deleteJob(jobName);

				return (status) ? MapeadorAgendamento.mapperResponse(true, HttpStatus.OK)
						: MapeadorAgendamento.mapperResponse(false, HttpStatus.NOT_MODIFIED);

			} else {
				throw new TrabalhoException(
						"Operação Não realizada. Não é possivel excluir uma Tarefa em execução. Aguarde o Termino.");
			}
		} else { 
			return MapeadorAgendamento.mapperResponse("Tarefa Não Localizada", HttpStatus.NOT_FOUND);
		}
	}
	
		
	
	public ResponseEntity<AgendamentoVM> pause(String jobName) {
		log.debug("Iniciando processo de PAUSA de Tarefa " + jobName + "de rotina da Aplicação." + "Usuario solicitante: "
				+ obterUsuarioDoContextoPeloToken());

		if (jobExistente(jobName)) {

			boolean executando = jobEmExecucao(jobName);

			if (executando) {
				boolean status = apiQuartzService.pauseJob(jobName);
				if (status) {
					return MapeadorAgendamento.mapperResponse(true, HttpStatus.OK);
				} else {
					throw new TrabalhoException("Não foi possivel Pausar o Trabalho. Error Interno da Aplicação. Solicite consulta de LOGS para mais informações");
				}
			} else {
				return MapeadorAgendamento.mapperResponse("Tarefa já em estado de Pausa.", HttpStatus.NOT_MODIFIED);
			}

		} else { // Caso não existir a tarefa...
			return MapeadorAgendamento.mapperResponse("Tarefa não Localizada.", HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<AgendamentoVM> retornarProcessamento(String jobName) {
		log.debug("Iniciando processo de reinicio de Tarefa Pausada" + jobName + "de rotina da Aplicação." + "Usuario solicitante: "
				+ obterUsuarioDoContextoPeloToken());

		if (jobExistente(jobName)) {
			
			String jobState = apiQuartzService.getJobState(jobName);

			if (jobState.equalsIgnoreCase("PAUSED")) {
				log.debug("Iniciando novamente Tarefa " + jobName + "com estado em Pausa para Processamento. Usuario solicitante: "+ obterUsuarioDoContextoPeloToken());
			
				boolean status = apiQuartzService.resumeJob(jobName);

				if (status) {
					return MapeadorAgendamento.mapperResponse("Tarefa iniciada com Sucesso.", HttpStatus.OK);
				} else {
					return MapeadorAgendamento.mapperResponse("Não foi possivel iniciar a Tarefa" + jobName + "Exceção interna na Aplicação. Solicite a consulta de Logs para melhores informações", HttpStatus.NOT_MODIFIED);
				}
			} else {
				return MapeadorAgendamento.mapperResponse("Operação não realizada. Trabaho não estar em estado de Pausa. O corrente estado e: " + jobState , HttpStatus.NOT_MODIFIED);
			}

		} else { 
			return MapeadorAgendamento.mapperResponse("Tarefa não Localizada.", HttpStatus.NOT_FOUND);
		}
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
	
	///////////////////METODOS PRIVADOS...////////////////////
	
	
	private Class<?> getClassForName(String nameJob) {

		String camelCaseNameClass = ConverterStringCamelCase.convertStringToCamelCase(nameJob);

		String classPackage = "com.system.bibliotec.service.automacao.trabalhos.".concat(camelCaseNameClass);

		Class job = null;
		try {

			job = getClass().forName(classPackage);

			if (!job.getSuperclass().equals(QuartzJobBean.class)) {

				throw new TrabalhoExistenteException(
						"Exceção: Implementação do Trabalho inexistente ou invalida. Informa outra valida e existente.");
			}

		} catch (ClassNotFoundException e) {

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					obterUsuarioDoContextoPeloToken(), excecaoImplementacaoTarefaNaoEncontrada);

			throw new TrabalhoExistenteException(
					"Exceção Interna: Implementação do Trabalho inexistente ou invalida. Informa outra valida e existente.");
		}

		return job;

	}
	

	private void triagemExclusãoTarefa(String jobName) {

		if (triagemDoAgendador.isContainsSpecialChar(jobName) && !triagemDoAgendador.jobNameIsCamelCaseValid(jobName)
				&& !triagemDoAgendador.qtdEspacosValidos(jobName)) {
			throw new TrabalhoInvalidoException(
					"Nome do trabalho invalido. Informar um nome valido e com espaçamento.");
		}

		if (jobExistente(jobName)) {

			if (jobEmExecucao(jobName)) {

				throw new TrabalhoExistenteException(
						"Trabalho já cadastrado e em execução no momento. É necessario Informar outro valido e existente.");
			} else {
				throw new TrabalhoExistenteException("Trabalho já cadastrado. Informar um outro valido e existente.");
			}

		}

	}

	private void triagemNovaTarefa(String jobName) {

		if (triagemDoAgendador.isContainsSpecialChar(jobName) && !triagemDoAgendador.jobNameIsCamelCaseValid(jobName)
				&& !triagemDoAgendador.qtdEspacosValidos(jobName)) {
			throw new TrabalhoInvalidoException(
					"Nome do trabalho invalido. Informar um nome valido e com espaçamento.");
		}

		if (jobExistente(jobName)) {

			if (jobEmExecucao(jobName)) {

				throw new TrabalhoExistenteException(
						"Trabalho já cadastrado e em execução no momento. É necessario Informar outro valido e existente.");
			} else {
				throw new TrabalhoExistenteException("Trabalho já cadastrado. Informar um outro valido e existente.");
			}

		}

	}

	private boolean isContainsSpecialChar(String msg) {
		Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

		return (regex.matcher(msg).find()) ? true : false;
	}

	private boolean qtdEspacosValidos(String texto) {
		return (texto.length() - texto.replaceAll(" ", "").length() > 1) ? false : true;
	}

	private boolean jobNameIsCamelCaseValid(String jobName) {
		// TODO Auto-generated method stub

		return (jobName != null && !jobName.isEmpty() && jobName.contains(" ")) ? true : false;
	}

	private boolean jobExistente(String jobName) {
		// TODO Auto-generated method stub
		return (apiQuartzService.isJobWithNamePresent(jobName)) ? true : false;
	}

	private boolean jobEmExecucao(String jobName) {

		return (apiQuartzService.isJobRunning(jobName));
	}

	

}
