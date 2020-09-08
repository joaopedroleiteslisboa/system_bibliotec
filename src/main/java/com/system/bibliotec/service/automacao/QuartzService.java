package com.system.bibliotec.service.automacao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.system.bibliotec.service.vm.AgendamentoVM;

public interface QuartzService {

	ResponseEntity<AgendamentoVM> scheduleDate(String jobName, Date jobScheduleTime) throws SchedulerException;

	public ResponseEntity<AgendamentoVM> scheduleCron(String jobName, Date jobScheduleTime, String cronExpression) throws SchedulerException;

	public boolean deleteJob(String jobName) throws SchedulerException;
		
	ResponseEntity<AgendamentoVM> updateOneTimeJob(String jobName, Date date) throws Exception;

	ResponseEntity<AgendamentoVM> updateCronJob(String jobName, Date date, String cronExpression) throws SchedulerException;

	boolean unScheduleJob(String jobName);	

	ResponseEntity<AgendamentoVM> pauseJob(String jobName) throws SchedulerException;

	ResponseEntity<AgendamentoVM> resumeJob(String jobName) throws SchedulerException;

	boolean startJobNow(String jobName) throws SchedulerException;

	ResponseEntity<AgendamentoVM> isJobRunning(String jobName) throws SchedulerException;

	ResponseEntity<AgendamentoVM> getAllJobs() throws SchedulerException;

	ResponseEntity<AgendamentoVM> isJobWithNamePresent(String jobName) throws SchedulerException;

	String getJobState(String jobName);

	ResponseEntity<AgendamentoVM> stopJob(String jobName) throws SchedulerException;
}
