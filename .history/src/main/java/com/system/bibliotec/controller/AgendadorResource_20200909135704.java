package com.system.bibliotec.controller;

import java.util.Date;

import org.quartz.SchedulerException;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.service.automacao.QuartzService;
import com.system.bibliotec.service.mapper.MapeadorAgendamento;
import com.system.bibliotec.service.vm.AgendamentoVM;

@RestController
@RequestMapping("/schedule")
public class AgendadorResource {

	private final QuartzService apiQuartz;

	@Autowired
	public AgendadorResource(@Lazy QuartzService apiQuartz) {

		this.apiQuartz = apiQuartz;
	}

	@PostMapping("/scheduleDate")
	public ResponseEntity<AgendamentoVM> scheduleDate(@RequestParam("jobName") String jobName,

			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime)
			throws SchedulerException {

		return apiQuartz.scheduleDate(jobName, jobScheduleTime);

	}

	@PostMapping("/scheduleCron")
	public ResponseEntity<AgendamentoVM> scheduleCron(@RequestParam("jobName") String jobName,

			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime,

			@RequestParam(name = "cronExpression", required = false) String cronExpression) throws SchedulerException {

		return apiQuartz.scheduleCron(jobName, jobScheduleTime, cronExpression);

	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/unschedule")
	public void unschedule(@RequestParam("jobName") String jobName) {

		apiQuartz.unScheduleJob(jobName);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<AgendamentoVM> delete(@RequestParam("jobName") String jobName) throws SchedulerException {
		return MapeadorAgendamento.mapperResponse(apiQuartz.deleteJob(jobName), HttpStatus.OK);

	}

	@RequestMapping("/pause")
	public ResponseEntity<AgendamentoVM> pause(@RequestParam("jobName") String jobName) throws SchedulerException {

		return apiQuartz.pauseJob(jobName);
	}

	@RequestMapping("/resume")
	public ResponseEntity<AgendamentoVM> resume(@RequestParam("jobName") String jobName) throws SchedulerException {
		return apiQuartz.resumeJob(jobName);
	}

	@RequestMapping("update")
	public ResponseEntity<AgendamentoVM> updateJob(@RequestParam("jobName") String jobName,
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime,
			@RequestParam("cronExpression") String cronExpression) throws Exception {

		if (cronExpression == null || cronExpression.trim().equals("")) {
			// Single Trigger
			return apiQuartz.updateOneTimeJob(jobName, jobScheduleTime);

		} else {
			// Cron Trigger
			return apiQuartz.updateCronJob(jobName, jobScheduleTime, cronExpression);

		}
	}

	@RequestMapping("jobs")
	public ResponseEntity<AgendamentoVM> getAllJobs() throws SchedulerException {

		return apiQuartz.getAllJobs();
	}

	@RequestMapping("checkJobName")
	public ResponseEntity<AgendamentoVM> checkJobName(@RequestParam("jobName") String jobName)
			throws SchedulerException {

		return apiQuartz.isJobWithNamePresent(jobName);

	}

	@RequestMapping("isJobRunning")
	public ResponseEntity<AgendamentoVM> isJobRunning(@RequestParam("jobName") String jobName)
			throws SchedulerException {

		return apiQuartz.isJobRunning(jobName);

	}

	@RequestMapping("jobState")
	public ResponseEntity<AgendamentoVM> getJobState(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.getJobState()");

		return MapeadorAgendamento.mapperResponse(apiQuartz.getJobState(jobName), HttpStatus.OK);

	}

	@RequestMapping("stop")
	public ResponseEntity<AgendamentoVM> stopJob(@RequestParam("jobName") String jobName) throws SchedulerException {

		return apiQuartz.stopJob(jobName);

	}

	@RequestMapping("start")
	public ResponseEntity<AgendamentoVM> startJobNow(@RequestParam("jobName") String jobName)
			throws SchedulerException {

		return MapeadorAgendamento.mapperResponse(apiQuartz.startJobNow(jobName), HttpStatus.OK);

	}

}