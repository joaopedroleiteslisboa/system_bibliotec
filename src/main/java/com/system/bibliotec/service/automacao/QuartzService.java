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

	public ResponseEntity<AgendamentoVM> deleteJob(String jobName) throws SchedulerException;
		
	ResponseEntity<AgendamentoVM> updateOneTimeJob(String jobName, Date date) throws Exception;

	ResponseEntity<AgendamentoVM> updateCronJob(String jobName, Date date, String cronExpression) throws SchedulerException;

	boolean unScheduleJob(String jobName);	

	ResponseEntity<AgendamentoVM> pauseJob(String jobName) throws SchedulerException;

	ResponseEntity<AgendamentoVM> resumeJob(String jobName) throws SchedulerException;

	boolean startJobNow(String jobName);

	boolean isJobRunning(String jobName);

	ResponseEntity<AgendamentoVM> getAllJobs() throws SchedulerException;

	boolean isJobWithNamePresent(String jobName);

	String getJobState(String jobName);

	boolean stopJob(String jobName);
}
