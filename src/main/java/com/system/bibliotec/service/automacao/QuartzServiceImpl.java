package com.system.bibliotec.service.automacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
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
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.system.bibliotec.config.SpringSecurityAuditorAware;
import com.system.bibliotec.service.ServicoDoSistema;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService, IAuditorTokenDeUsuarioDoContexto {

	@Autowired
	@Lazy
	SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private ServicoDoSistema servicoSistema;
	
	@Autowired
	private SpringSecurityAuditorAware auditorAware;

	
	
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
	
	
	
	
	
	/**
	 * Criar trabalho com base em Data
	 */
	@Override
	public boolean scheduleOneTimeJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date) {
		System.out.println("Request received to scheduleJob");

		String jobKey = jobName;
		String groupKey = "SampleGroup";
		String triggerKey = jobName;

		JobDetail jobDetail = JobUtil.createJob(jobClass, false, context, jobKey, groupKey);

		System.out.println("creating trigger for key :" + jobKey + " at date :" + date);
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
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoNovoTrabalho + jobKey);
					
			
		}

		return false;
	}

	/**
	 * Criar trabalho com base em CRON
	 */
	@Override
	public boolean scheduleCronJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date,
			String cronExpression) {
		System.out.println("Request received to scheduleJob");

		String jobKey = jobName;
		String groupKey = "SampleGroup";
		String triggerKey = jobName;

		JobDetail jobDetail = JobUtil.createJob(jobClass, false, context, jobKey, groupKey);

		System.out.println("creating trigger for key :" + jobKey + " at date :" + date);
		Trigger cronTriggerBean = JobUtil.createCronTrigger(triggerKey, date, cronExpression,
				SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			Date dt = scheduler.scheduleJob(jobDetail, cronTriggerBean);
			System.out.println("Job with key jobKey :" + jobKey + " and group :" + groupKey
					+ " scheduled successfully for date :" + dt);
			return true;
		} catch (SchedulerException e) {
						
			log.error(excecaoNovoTrabalhoCron + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoNovoTrabalhoCron + jobKey);
		}

		return false;
	}

	
	@Override
	public boolean updateOneTimeJob(String jobName, Date date) {
		System.out.println("Request received for updating one time job.");

		String jobKey = jobName;

		System.out.println("Parameters received for updating one time job : jobKey :" + jobKey + ", date: " + date);
		try {
			// Trigger newTrigger = JobUtil.createSingleTrigger(jobKey, date,
			// SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
			Trigger newTrigger = JobUtil.createSingleTrigger(jobKey, date, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

			Date dt = schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobKey), newTrigger);
			System.out
					.println("Trigger associated with jobKey :" + jobKey + " rescheduled successfully for date :" + dt);
			return true;
		} catch (Exception e) {
						
			log.error(excecaoAtualizarTrabalho + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoAtualizarTrabalho + jobKey);
			
			return false;
		}
	}

	/**
	 * Atualizar trabalhos definidos por CRON
	 */
	@Override
	public boolean updateCronJob(String jobName, Date date, String cronExpression) {
		System.out.println("Request received for updating cron job.");

		String jobKey = jobName;

		System.out.println("Parameters received for updating cron job : jobKey :" + jobKey + ", date: " + date);
		try {
			// Trigger newTrigger = JobUtil.createSingleTrigger(jobKey, date,
			// SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
			Trigger newTrigger = JobUtil.createCronTrigger(jobKey, date, cronExpression,
					SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

			Date dt = schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobKey), newTrigger);
			System.out
					.println("Trigger associated with jobKey :" + jobKey + " rescheduled successfully for date :" + dt);
			return true;
		} catch (Exception e) {
			
			log.error(excecaoAtualizarTrabalhoCron + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoAtualizarTrabalhoCron + jobKey);
			
			return false;
		}
	}

	/**
	 * cancelar trabalho 	
	 */
	@Override
	public boolean unScheduleJob(String jobName) {
		System.out.println("Request received for Unscheduleding job.");

		String jobKey = jobName;

		TriggerKey tkey = new TriggerKey(jobKey);
		System.out.println("Parameters received for unscheduling job : tkey :" + jobKey);
		try {
			boolean status = schedulerFactoryBean.getScheduler().unscheduleJob(tkey);
			System.out.println("Trigger associated with jobKey :" + jobKey + " unscheduled with status :" + status);
			return status;
		} catch (SchedulerException e) {
			
			log.error(excecaoCancelamentoTrabalho + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoCancelamentoTrabalho + jobKey);
						
			
			return false;
		}
	}

	
	@Override
	public boolean deleteJob(String jobName) {
		System.out.println("Request received for deleting job.");

		String jobKey = jobName;
		String groupKey = "SampleGroup";

		JobKey jkey = new JobKey(jobKey, groupKey);
		System.out.println("Parameters received for deleting job : jobKey :" + jobKey);

		try {
			boolean status = schedulerFactoryBean.getScheduler().deleteJob(jkey);
			System.out.println("Job with jobKey :" + jobKey + " deleted with status :" + status);
			return status;
		} catch (SchedulerException e) {
			
			log.error(excecaoDeletarTrabalho + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoDeletarTrabalho + jobKey);
			
			
			return false;
		}
	}

	
	@Override
	public boolean pauseJob(String jobName) {
		System.out.println("Request received for pausing job.");

		String jobKey = jobName;
		String groupKey = "SampleGroup";
		JobKey jkey = new JobKey(jobKey, groupKey);
		System.out.println("Parameters received for pausing job : jobKey :" + jobKey + ", groupKey :" + groupKey);

		try {
			schedulerFactoryBean.getScheduler().pauseJob(jkey);
			System.out.println("Job with jobKey :" + jobKey + " paused succesfully.");
			return true;
		} catch (SchedulerException e) {
			
			log.error(excecaoPausarTrabalho + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoPausarTrabalho + jobKey);
			
			
			return false;
		}
	}

	/**
	 * Retornar trabalho pausado
	 */
	@Override
	public boolean resumeJob(String jobName) {
		System.out.println("Request received for resuming job.");

		String jobKey = jobName;
		String groupKey = "SampleGroup";

		JobKey jKey = new JobKey(jobKey, groupKey);
		System.out.println("Parameters received for resuming job : jobKey :" + jobKey);
		try {
			schedulerFactoryBean.getScheduler().resumeJob(jKey);
			System.out.println("Job with jobKey :" + jobKey + " resumed succesfully.");
			return true;
		} catch (SchedulerException e) {
			
			log.error(excecaoAoRetornarTrabalhoPausado + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoAoRetornarTrabalhoPausado + jobKey);
			return false;
		}
	}

	/**
	 * Forçar inicio ou inciar trabalho
	 */
	@Override
	public boolean startJobNow(String jobName) {
		System.out.println("Request received for starting job now.");

		String jobKey = jobName;
		String groupKey = "SampleGroup";

		JobKey jKey = new JobKey(jobKey, groupKey);
		System.out.println("Parameters received for starting job now : jobKey :" + jobKey);
		try {
			schedulerFactoryBean.getScheduler().triggerJob(jKey);
			System.out.println("Job with jobKey :" + jobKey + " started now succesfully.");
			return true;
		} catch (SchedulerException e) {

			log.error(excecaoParaIniciarTrabalho + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoParaIniciarTrabalho + jobKey);
			return false;
		}
	}

	
	@Override
	public boolean isJobRunning(String jobName) {
		System.out.println("Request received to check if job is running");

		String jobKey = jobName;
		String groupKey = "SampleGroup";

		System.out.println("Parameters received for checking job is running now : jobKey :" + jobKey);
		try {

			List<JobExecutionContext> currentJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();
			if (currentJobs != null) {
				for (JobExecutionContext jobCtx : currentJobs) {
					String jobNameDB = jobCtx.getJobDetail().getKey().getName();
					String groupNameDB = jobCtx.getJobDetail().getKey().getGroup();
					if (jobKey.equalsIgnoreCase(jobNameDB) && groupKey.equalsIgnoreCase(groupNameDB)) {
						return true;
					}
				}
			}
		} catch (SchedulerException e) {
			
			log.error(excecaoParaChecarTrabalhoEmExcecucao + jobKey, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoParaChecarTrabalhoEmExcecucao + jobKey);
			
			
			return false;
		}
		return false;
	}

	
	@Override
	public List<Map<String, Object>> getAllJobs() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					Date scheduleTime = triggers.get(0).getStartTime();
					Date nextFireTime = triggers.get(0).getNextFireTime();
					Date lastFiredTime = triggers.get(0).getPreviousFireTime();

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("jobName", jobName);
					map.put("groupName", jobGroup);
					map.put("scheduleTime", scheduleTime);
					map.put("lastFiredTime", lastFiredTime);
					map.put("nextFireTime", nextFireTime);

					if (isJobRunning(jobName)) {
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
					System.out.println("Job details:");
					System.out.println(
							"Job Name:" + jobName + ", Group Name:" + groupName + ", Schedule Time:" + scheduleTime);
				}

			}
		} catch (SchedulerException e) {
			
			log.error(excecaoParaChecarTrabalhoEmExcecucao, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoParaChecarTrabalhoEmExcecucao);
			
		}
		return list;
	}

	
	@Override
	public boolean isJobWithNamePresent(String jobName) {
		try {
			String groupKey = "SampleGroup";
			JobKey jobKey = new JobKey(jobName, groupKey);
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			if (scheduler.checkExists(jobKey)) {
				return true;
			}
		} catch (SchedulerException e) {
			
			log.error(excecaoParaChecarSeOTrabalhoExiste + jobName, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoParaChecarSeOTrabalhoExiste);
		}
		return false;
	}

	/**
	 * Get the current state of job
	 */
	public String getJobState(String jobName) {
		System.out.println("JobServiceImpl.getJobState()");

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
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoParaChecarStatusDoTrabalho);
			
			
		}
		return null;
	}

	
	@Override
	public boolean stopJob(String jobName) {
		System.out.println("JobServiceImpl.stopJob()");
		try {
			String jobKey = jobName;
			String groupKey = "SampleGroup";

			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jkey = new JobKey(jobKey, groupKey);

			return scheduler.interrupt(jkey);

		} catch (SchedulerException e) {
			
			log.error(excecaoParaPararUmTrabalho + jobName, e);				
			
			servicoSistema.saveContextErrorDefault(e, Thread.currentThread().getStackTrace()[1].getMethodName(), auditorAware.getCurrentAuditor().get()
					,excecaoParaPararUmTrabalho);
		}
		return false;
	}
}
