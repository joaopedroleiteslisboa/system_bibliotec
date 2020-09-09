package com.system.bibliotec.service.automacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.system.bibliotec.config.SpringSecurityAuditorAware;
import com.system.bibliotec.exception.TrabalhoException;
import com.system.bibliotec.exception.TrabalhoExistenteException;
import com.system.bibliotec.exception.TrabalhoInvalidoException;
import com.system.bibliotec.service.ServicoDoSistema;
import com.system.bibliotec.service.automacao.trabalhos.CronJob;
import com.system.bibliotec.service.mapper.MapeadorAgendamento;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;
import com.system.bibliotec.service.ultis.ConverterStringCamelCase;
import com.system.bibliotec.service.vm.AgendamentoVM;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService, IAuditorTokenDeUsuarioDoContexto {

	private final SchedulerFactoryBean schedulerFactoryBean;

	private final ApplicationContext context;

	private final ServicoDoSistema servicoSistema;

	private final SpringSecurityAuditorAware auditorAware;

	@Autowired
	public QuartzServiceImpl(@Lazy SchedulerFactoryBean schedulerFactoryBean, ApplicationContext context,
			ServicoDoSistema servicoSistema, SpringSecurityAuditorAware auditorAware) {
		super();
		this.schedulerFactoryBean = schedulerFactoryBean;
		this.context = context;
		this.servicoSistema = servicoSistema;
		this.auditorAware = auditorAware;

	}

	private static final String excecaoImplementacaoTarefaNaoEncontrada = "ClassNotFoundException: Exceção ao procurar implementação da Tarefa";

	private static final String trabalhoNaoEncontrado = "Tarefa Não localizada: ";

	private static final String excecaoNovoTrabalho = "SchedulerException: Exceção ao agendar o trabalho sob Nome: ";

	private static final String excecaoNovoTrabalhoCron = "SchedulerException: Exceção ao agendar o trabalho CRON sob Nome: ";

	private static final String excecaoAtualizarTrabalho = "Exception: Exceção na atualização do trabalho sob Nome: ";

	private static final String excecaoAtualizarTrabalhoCron = "Exception: Exceção na atualização do trabalho CRON sob Nome: ";

	private static final String excecaoCancelamentoTrabalho = "SchedulerException: Exceção no cancelamento do trabalho sob Nome: ";

	private static final String excecaoDeletarTrabalho = "SchedulerException: Exceção para deletar o trabalho sob Nome: ";

	private static final String excecaoPausarTrabalho = "SchedulerException: Exceção para Pausar o trabalho sob Nome: ";

	private static final String excecaoAoRetornarTrabalhoPausado = "SchedulerException: Exceção para Retornar o trabalho Pausado sob Nome: ";

	private static final String excecaoParaIniciarTrabalho = "SchedulerException: Exceção para Iniciar o trabalho sob Nome: ";

	private static final String excecaoParaChecarTrabalhoEmExcecucao = "SchedulerException: Exceção para checar trabalho em execucao sob Nome: ";

	private static final String excecaoParaChecarListaDeTrabalhos = "SchedulerException: Exceção para checar Lista de trabalhos. Mensagem: ";

	private static final String excecaoParaChecarSeOTrabalhoExiste = "SchedulerException: Exceção para checar se um determinado trabalho exsite. Nome do trabalho consultado: ";

	private static final String excecaoParaChecarStatusDoTrabalho = "SchedulerException: Exceção para checar o status de um determinado trabalho. Nome do trabalho consultado: ";

	private static final String excecaoParaPararUmTrabalho = "SchedulerException: Exceção para Parar um Trabalho. Nome do trabalho solicitado para Parar: ";

//===================================================DATE JOB =======================================================================	

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<AgendamentoVM> scheduleDate(String jobName, Date jobScheduleTime) throws SchedulerException {

		log.debug("Iniciando processo de criação de nova Tarefa do tipo Data e Hora de rotina da Aplicação."
				+ "Usuario solicitante: " + obterUsuarioDoContextoPeloToken());

		triagemNovaTarefa(jobName);

		@SuppressWarnings("rawtypes")
		Class classe = getClassForName(jobName);

		scheduleOneTimeJob(jobName, classe, jobScheduleTime);

		return MapeadorAgendamento.mapperResponse(getAllJobs(), HttpStatus.OK);

	}

	/**
	 * Criar trabalho com base em Data
	 * 
	 * @throws SchedulerException
	 */
	private boolean scheduleOneTimeJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date)
			throws SchedulerException {

		String jobKey = jobName;
		String groupKey = "SampleGroup";
		String triggerKey = jobName;

		JobDetail jobDetail = JobUtil.createJob(jobClass, true, context, jobKey, groupKey);

		Trigger cronTriggerBean = JobUtil.createSingleTrigger(triggerKey, date,
				SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		// Trigger cronTriggerBean = JobUtil.createSingleTrigger(triggerKey, date,
		// SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);

		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			Date dt = scheduler.scheduleJob(jobDetail, cronTriggerBean);
			System.out.println("Job with key jobKey :" + jobKey + " and group :" + groupKey
					+ " scheduled successfully for date :" + dt);
			return true;
		} catch (SchedulerException e) {

			log.error(excecaoNovoTrabalho + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoNovoTrabalho + jobKey);

			throw new SchedulerException(excecaoNovoTrabalho);

		}

	}
//===================================================DATE JOB =======================================================================	

//===================================================CROM JOB =======================================================================	
	@Override
	public ResponseEntity<AgendamentoVM> scheduleCron(String jobName, Date jobScheduleTime, String cronExpression)
			throws SchedulerException {

		triagemNovaTarefa(jobName);

		Class classe = getClassForName(jobName);

		scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);

		return MapeadorAgendamento.mapperResponse(getAllJobs(), HttpStatus.OK);

	}

	/**
	 * Criar trabalho com base em CRON
	 * 
	 * @throws SchedulerException
	 */
	private boolean scheduleCronJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date,
			String cronExpression) throws SchedulerException {

		String jobKey = jobName;
		String groupKey = "SampleGroup";
		String triggerKey = jobName;

		JobDetail jobDetail = JobUtil.createJob(jobClass, false, context, jobKey, groupKey);

		Trigger cronTriggerBean = JobUtil.createCronTrigger(triggerKey, date, cronExpression,
				SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			Date dt = scheduler.scheduleJob(jobDetail, cronTriggerBean);

			return true;
		} catch (SchedulerException e) {

			log.error(excecaoNovoTrabalhoCron + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoNovoTrabalhoCron + jobKey);
			throw new SchedulerException(excecaoNovoTrabalhoCron);
		}

	}

//===================================================CROM JOB =======================================================================

	@Override
	public ResponseEntity<AgendamentoVM> updateOneTimeJob(String jobName, Date date) throws Exception {

		String jobKey = jobName;

		try {
			// Trigger newTrigger = JobUtil.createSingleTrigger(jobKey, date,
			// SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
			Trigger newTrigger = JobUtil.createSingleTrigger(jobKey, date, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

			Date dt = schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobKey), newTrigger);

			return MapeadorAgendamento.mapperResponse(true, HttpStatus.OK);

		} catch (Exception e) {

			log.error(excecaoAtualizarTrabalho + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoAtualizarTrabalho + jobKey);

			throw new Exception(excecaoAtualizarTrabalho, e);
		}
	}

	/**
	 * deleta um trabalho
	 * 
	 * @throws SchedulerException
	 */
	@Override
	public boolean deleteJob(String jobName) throws SchedulerException {

		log.debug("Iniciando processo de exclusão de Tarefa de rotina da Aplicação." + "Usuario solicitante: "
				+ obterUsuarioDoContextoPeloToken());

		triagemExclusãoTarefa(jobName);

		String jobKey = jobName;
		String groupKey = "SampleGroup";

		JobKey jkey = new JobKey(jobKey, groupKey);

		try {
			return schedulerFactoryBean.getScheduler().deleteJob(jkey);

		} catch (SchedulerException e) {

			log.error(excecaoDeletarTrabalho + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoDeletarTrabalho + jobKey);

			throw new SchedulerException(excecaoDeletarTrabalho, e);
		}
	}

	/**
	 * Atualizar trabalhos definidos por CRON
	 * 
	 * @throws SchedulerException
	 */
	@Override
	public ResponseEntity<AgendamentoVM> updateCronJob(String jobName, Date date, String cronExpression)
			throws SchedulerException {

		String jobKey = jobName;

		try {
			// Trigger newTrigger = JobUtil.createSingleTrigger(jobKey, date,
			// SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
			Trigger newTrigger = JobUtil.createCronTrigger(jobKey, date, cronExpression,
					SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

			schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobKey), newTrigger);

			return MapeadorAgendamento.mapperResponse(true, HttpStatus.OK);
		} catch (Exception e) {

			log.error(excecaoAtualizarTrabalhoCron + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoAtualizarTrabalhoCron + jobKey);

			throw new SchedulerException(excecaoAtualizarTrabalhoCron, e);
		}
	}

	/**
	 * cancelar trabalho
	 */
	@Override
	public boolean unScheduleJob(String jobName) {

		String jobKey = jobName;

		TriggerKey tkey = new TriggerKey(jobKey);

		try {
			boolean status = schedulerFactoryBean.getScheduler().unscheduleJob(tkey);
			System.out.println("Trigger associated with jobKey :" + jobKey + " unscheduled with status :" + status);
			return status;
		} catch (SchedulerException e) {

			log.error(excecaoCancelamentoTrabalho + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoCancelamentoTrabalho + jobKey);

			return false;
		}
	}

	@Override
	public ResponseEntity<AgendamentoVM> pauseJob(String jobName) throws SchedulerException {

		if (jobExistente(jobName)) {

			String jobKey = jobName;
			String groupKey = "SampleGroup";
			JobKey jkey = new JobKey(jobKey, groupKey);
			System.out.println("Parameters received for pausing job : jobKey :" + jobKey + ", groupKey :" + groupKey);

			try {
				schedulerFactoryBean.getScheduler().pauseJob(jkey);
				System.out.println("Job with jobKey :" + jobKey + " paused succesfully.");

				return MapeadorAgendamento.mapperResponse(true, HttpStatus.OK);
			} catch (SchedulerException e) {

				log.error(excecaoPausarTrabalho + jobKey, e);

				servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
						auditorAware.getCurrentAuditor().get(), excecaoPausarTrabalho + jobKey);

				throw new SchedulerException("Não foi possivel Pausar o Trabalho. Error Interno da Aplicação.", e);
			}

		} else { // Caso não existir a tarefa...
			return MapeadorAgendamento.mapperResponse(trabalhoNaoEncontrado, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Retornar trabalho pausado
	 * 
	 * @throws SchedulerException
	 */
	@Override
	public ResponseEntity<AgendamentoVM> resumeJob(String jobName) throws SchedulerException {
		log.debug("Iniciando processo de reinicio de Tarefa Pausada" + jobName + "de rotina da Aplicação."
				+ "Usuario solicitante: " + obterUsuarioDoContextoPeloToken());

		if (jobExistente(jobName)) {

			String jobState = getJobState(jobName);

			if (TriggerState.PAUSED.equals(jobState)) {
				log.debug("Iniciando novamente Tarefa " + jobName
						+ "com estado em Pausa para Processamento. Usuario solicitante: "
						+ obterUsuarioDoContextoPeloToken());

				String jobKey = jobName;
				String groupKey = "SampleGroup";

				JobKey jKey = new JobKey(jobKey, groupKey);
				System.out.println("Parameters received for resuming job : jobKey :" + jobKey);

				try {
					schedulerFactoryBean.getScheduler().resumeJob(jKey);

					return MapeadorAgendamento.mapperResponse("Tarefa iniciada com Sucesso.", HttpStatus.OK);

				} catch (SchedulerException e) {

					log.error(excecaoAoRetornarTrabalhoPausado + jobKey, e);

					servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
							auditorAware.getCurrentAuditor().get(), excecaoAoRetornarTrabalhoPausado + jobKey);

					throw new SchedulerException(excecaoAoRetornarTrabalhoPausado, e);

				}

			} else {
				throw new TrabalhoException(
						" Operação não realizada. O trabalho não encontra-se em um estado de Pausa");
			}

		} else {
			return MapeadorAgendamento.mapperResponse(trabalhoNaoEncontrado, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Forçar inicio ou inciar trabalho
	 * 
	 * @throws SchedulerException
	 */
	@Override
	public boolean startJobNow(String jobName) throws SchedulerException {

		triagemSolicitacaoStart(jobName);

		String jobKey = jobName;
		String groupKey = "SampleGroup";

		JobKey jKey = new JobKey(jobKey, groupKey);

		try {
			schedulerFactoryBean.getScheduler().triggerJob(jKey);
			System.out.println("Job with jobKey :" + jobKey + " started now succesfully.");
			return true;
		} catch (SchedulerException e) {

			log.error(excecaoParaIniciarTrabalho + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoParaIniciarTrabalho + jobKey);
			throw new SchedulerException(excecaoParaIniciarTrabalho, e);
		}
	}

	@Override
	public ResponseEntity<AgendamentoVM> isJobRunning(String jobName) throws SchedulerException {

		boolean isJobRunning = false;
		String jobKey = jobName;
		String groupKey = "SampleGroup";

		try {

			List<JobExecutionContext> currentJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();

			if (currentJobs != null) {

				for (JobExecutionContext jobCtx : currentJobs) {
					String jobNameDB = jobCtx.getJobDetail().getKey().getName();
					String groupNameDB = jobCtx.getJobDetail().getKey().getGroup();

					if (jobKey.equalsIgnoreCase(jobNameDB) && groupKey.equalsIgnoreCase(groupNameDB)) {
						isJobRunning = true;
					}
				}

			} else {
				return MapeadorAgendamento.mapperResponse("Nenhuma atividade se encontra em Execução", HttpStatus.OK);
			}

		} catch (SchedulerException e) {

			log.error(excecaoParaChecarTrabalhoEmExcecucao + jobKey, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoParaChecarTrabalhoEmExcecucao + jobKey);

			throw new SchedulerException(excecaoParaChecarTrabalhoEmExcecucao, e);
		}

		return MapeadorAgendamento.mapperResponse(isJobRunning, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<AgendamentoVM> getAllJobs() throws SchedulerException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					
					Date scheduleTime = (triggers != null && triggers.size() > 0)?  triggers.get(0).getStartTime(): null;
					Date nextFireTime = (triggers != null && triggers.size() > 0)?  triggers.get(0).getNextFireTime(): null;
					Date lastFiredTime= (triggers != null && triggers.size() > 0)?  triggers.get(0).getPreviousFireTime(): null;

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("jobName", jobName);
					map.put("groupName", jobGroup);
					map.put("scheduleTime", scheduleTime);
					map.put("lastFiredTime", lastFiredTime);
					map.put("nextFireTime", nextFireTime);

					if (jobEmExecucao(jobName)) {
						map.put("jobStatus", "RUNNING");
					} else {
						String jobState = getJobState(jobName);
						map.put("jobStatus", jobState);
					}

					/*
					 * Date currentDate = new Date(); if (scheduleTime.compareTo(currentDate) > 0) {
					 * map.put("jobStatus", "scheduled"); } else if
					 * (scheduleTime.compareTo(currentDate) < 0) { map.put("jobStatus", "Running");
					 * } else if (scheduleTime.compareTo(currentDate) == 0) { map.put("jobStatus",
					 * "Running"); }
					 */

					list.add(map);
				}

			}
		} catch (SchedulerException e) {

			log.error(excecaoParaChecarTrabalhoEmExcecucao, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoParaChecarTrabalhoEmExcecucao);

			throw new SchedulerException(excecaoParaChecarListaDeTrabalhos, e);
		}
		return MapeadorAgendamento.mapperResponse(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AgendamentoVM> isJobWithNamePresent(String jobName) throws SchedulerException {

		boolean exists = false;

		if (jobName == null || jobName.trim().equals("")) {
			throw new TrabalhoException("Nome do trabalho Invalido");
		} else {

			try {
				String groupKey = "SampleGroup";
				JobKey jobKey = new JobKey(jobName, groupKey);
				Scheduler scheduler = schedulerFactoryBean.getScheduler();

				exists = (scheduler.checkExists(jobKey)) ? true : false;

			} catch (SchedulerException e) {

				log.error(excecaoParaChecarSeOTrabalhoExiste + jobName, e);

				servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
						auditorAware.getCurrentAuditor().get(), excecaoParaChecarSeOTrabalhoExiste);

				throw new SchedulerException(excecaoParaChecarSeOTrabalhoExiste, e);
			}
		}
		return MapeadorAgendamento.mapperResponse(exists, HttpStatus.OK);
	}

	/**
	 * Get the current state of job
	 */
	public String getJobState(String jobName) {

		try {
			String groupKey = "SampleGroup";
			JobKey jobKey = new JobKey(jobName, groupKey);

			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);

			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
			if (triggers != null && triggers.size() > 0) {
				for (Trigger trigger : triggers) {
					TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

					if (TriggerState.PAUSED.equals(triggerState)) {
						return "PAUSED";
					} else if (TriggerState.BLOCKED.equals(triggerState)) {
						return "BLOCKED";
					} else if (TriggerState.COMPLETE.equals(triggerState)) {
						return "COMPLETE";
					} else if (TriggerState.ERROR.equals(triggerState)) {
						return "ERROR";
					} else if (TriggerState.NONE.equals(triggerState)) {
						return "NONE";
					} else if (TriggerState.NORMAL.equals(triggerState)) {
						return "SCHEDULED";
					}
				}
			}
		} catch (SchedulerException e) {

			log.error(excecaoParaChecarStatusDoTrabalho + jobName, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoParaChecarStatusDoTrabalho);

		}
		return StringUtils.EMPTY;
	}

	@Override
	public ResponseEntity<AgendamentoVM> stopJob(String jobName) throws SchedulerException {

		boolean stop = false;

		triagemSolicitacaoStop(jobName);

		try {
			String jobKey = jobName;
			String groupKey = "SampleGroup";

			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jkey = new JobKey(jobKey, groupKey);

			stop = scheduler.interrupt(jkey);

		} catch (SchedulerException e) {

			log.error(excecaoParaPararUmTrabalho + jobName, e);

			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
					auditorAware.getCurrentAuditor().get(), excecaoParaPararUmTrabalho);

			throw new SchedulerException(excecaoParaPararUmTrabalho, e);
		}
		return MapeadorAgendamento.mapperResponse(stop, HttpStatus.OK);
	}

	// PRIVATES UTILS METHODOS >>>>>

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

	private void triagemSolicitacaoStart(String jobName) {

		if (jobExistente(jobName)) {

			if (jobEmExecucao(jobName)) {
				throw new TrabalhoException("Tarefa já estar em Estado de Execução");
			}

		}
	}

	private void triagemSolicitacaoStop(String jobName) {
		// TODO Auto-generated method stub

		if (jobExistente(jobName)) {

			if (jobEmExecucao(jobName)) {

			} else {
				throw new TrabalhoException("Tarefa não estar em estado de  Execução");
			}
		}
	}

	private void triagemExclusãoTarefa(String jobName) {

		if (isContainsSpecialChar(jobName) || !jobNameIsCamelCaseValid(jobName) || !qtdEspacosValidos(jobName)) {
			throw new TrabalhoInvalidoException(
					"Nome do trabalho invalido. Informar um nome valido e com espaçamento.");
		}

		if (jobExistente(jobName)) {

			if (jobEmExecucao(jobName)) {

				throw new TrabalhoExistenteException(
						"Operação não realizada. Trabalho em Execução no momento. Será necessario mudar o estado para solicitar esta operação.");
			} else {
				throw new TrabalhoExistenteException("Trabalho já cadastrado. Informar um outro valido e existente.");
			}

		} else {
			throw new TrabalhoException(excecaoImplementacaoTarefaNaoEncontrada);

		}
	}

	private void triagemNovaTarefa(String jobName) throws SchedulerException {

		if (isContainsSpecialChar(jobName) || !jobNameIsCamelCaseValid(jobName) || !qtdEspacosValidos(jobName)) {
			throw new TrabalhoInvalidoException(
					"Nome do trabalho invalido. Informar um nome valido,com espaçamento valido e Sem caractere especial.");
		}

		if (Boolean.parseBoolean(isJobWithNamePresent(jobName).getBody().toString())) {

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
		boolean exist = false;

		try {
			if (Boolean.parseBoolean(isJobWithNamePresent(jobName).getBody().getData().toString())) {
				exist = true;
			} else {
				throw new TrabalhoInvalidoException("Trabalho invalido ou inexistente");
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new TrabalhoInvalidoException("Exceção interna da Aplicação.", e);
		}
		return exist;
	}

	private boolean jobEmExecucao(String jobName) {
		// TODO Auto-generated method stub
		boolean emExecucao = false;

		try {
			emExecucao = Boolean.parseBoolean(isJobRunning(jobName).getBody().getData().toString());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emExecucao;
	}

}
