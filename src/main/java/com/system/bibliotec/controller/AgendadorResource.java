package com.system.bibliotec.controller;

import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.service.AgendadorService;
import com.system.bibliotec.service.automacao.QuartzService;
import com.system.bibliotec.service.vm.AgendamentoVM;

@RestController
@RequestMapping("/agendador/")
public class AgendadorResource {

	private final QuartzService jobService;

	private final AgendadorService agendadorService;

	public AgendadorResource(AgendadorService agendadorService, @Lazy QuartzService jobService) {
		this.agendadorService = agendadorService;
		this.jobService = jobService;
	}

	@RequestMapping("scheduleDate")
	public ResponseEntity<AgendamentoVM> scheduleDate(@RequestParam("jobName") String jobName,

			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime) {

		return agendadorService.scheduleDate(jobName, jobScheduleTime);

	}

	@RequestMapping("scheduleCron")
	public ResponseEntity<AgendamentoVM> scheduleCron(@RequestParam("jobName") String jobName,

			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime,

			@RequestParam(name = "cronExpression", required = false) String cronExpression) {

		return agendadorService.scheduleCron(jobName, jobScheduleTime, cronExpression);

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping("unschedule")
	public void unschedule(@RequestParam("jobName") String jobName) {

		jobService.unScheduleJob(jobName);
	}

	@RequestMapping("delete")
	public ResponseEntity<AgendamentoVM> delete(@RequestParam("jobName") String jobName) {

		return agendadorService.deletarTarefa(jobName);
	}

	@RequestMapping("pause")
	public ResponseEntity<AgendamentoVM> pause(@RequestParam("jobName") String jobName) {

		return agendadorService.pause(jobName);
	}

	@RequestMapping("resume")
	public ResponseEntity<AgendamentoVM> resume(@RequestParam("jobName") String jobName) {
		return agendadorService.retornarProcessamento(jobName);
	}

	/*
	 * @RequestMapping("update") public ServerResponse
	 * updateJob(@RequestParam("jobName") String jobName,
	 * 
	 * @RequestParam("jobScheduleTime") @DateTimeFormat(pattern =
	 * "yyyy/MM/dd HH:mm") Date jobScheduleTime,
	 * 
	 * @RequestParam("cronExpression") String cronExpression){
	 * System.out.println("JobController.updateJob()");
	 * 
	 * //Job Name is mandatory if(jobName == null || jobName.trim().equals("")){
	 * return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false); }
	 * 
	 * //Edit Job if(jobService.isJobWithNamePresent(jobName)){
	 * 
	 * if(cronExpression == null || cronExpression.trim().equals("")){ //Single
	 * Trigger boolean status = jobService.updateOneTimeJob(jobName,
	 * jobScheduleTime); if(status){ return
	 * getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
	 * }else{ return getServerResponse(ServerResponseCode.ERROR, false); }
	 * 
	 * }else{ //Cron Trigger boolean status = jobService.updateCronJob(jobName,
	 * jobScheduleTime, cronExpression); if(status){ return
	 * getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
	 * }else{ return getServerResponse(ServerResponseCode.ERROR, false); } }
	 * 
	 * 
	 * }else{ return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
	 * } }
	 * 
	 * @RequestMapping("jobs") public ServerResponse getAllJobs(){
	 * System.out.println("JobController.getAllJobs()");
	 * 
	 * List<Map<String, Object>> list = jobService.getAllJobs(); return
	 * getServerResponse(ServerResponseCode.SUCCESS, list); }
	 * 
	 * @RequestMapping("checkJobName") public ServerResponse
	 * checkJobName(@RequestParam("jobName") String jobName){
	 * System.out.println("JobController.checkJobName()");
	 * 
	 * //Job Name is mandatory if(jobName == null || jobName.trim().equals("")){
	 * return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false); }
	 * 
	 * boolean status = jobService.isJobWithNamePresent(jobName); return
	 * getServerResponse(ServerResponseCode.SUCCESS, status); }
	 * 
	 * @RequestMapping("isJobRunning") public ServerResponse
	 * isJobRunning(@RequestParam("jobName") String jobName) {
	 * System.out.println("JobController.isJobRunning()");
	 * 
	 * boolean status = jobService.isJobRunning(jobName); return
	 * getServerResponse(ServerResponseCode.SUCCESS, status); }
	 * 
	 * @RequestMapping("jobState") public ServerResponse
	 * getJobState(@RequestParam("jobName") String jobName) {
	 * System.out.println("JobController.getJobState()");
	 * 
	 * String jobState = jobService.getJobState(jobName); return
	 * getServerResponse(ServerResponseCode.SUCCESS, jobState); }
	 * 
	 * @RequestMapping("stop") public ServerResponse
	 * stopJob(@RequestParam("jobName") String jobName) {
	 * System.out.println("JobController.stopJob()");
	 * 
	 * if(jobService.isJobWithNamePresent(jobName)){
	 * 
	 * if(jobService.isJobRunning(jobName)){ boolean status =
	 * jobService.stopJob(jobName); if(status){ return
	 * getServerResponse(ServerResponseCode.SUCCESS, true); }else{ //Server error
	 * return getServerResponse(ServerResponseCode.ERROR, false); }
	 * 
	 * }else{ //Job not in running state return
	 * getServerResponse(ServerResponseCode.JOB_NOT_IN_RUNNING_STATE, false); }
	 * 
	 * }else{ //Job doesn't exist return
	 * getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false); } }
	 * 
	 * @RequestMapping("start") public ServerResponse
	 * startJobNow(@RequestParam("jobName") String jobName) {
	 * System.out.println("JobController.startJobNow()");
	 * 
	 * if(jobService.isJobWithNamePresent(jobName)){
	 * 
	 * if(!jobService.isJobRunning(jobName)){ boolean status =
	 * jobService.startJobNow(jobName);
	 * 
	 * if(status){ //Success return getServerResponse(ServerResponseCode.SUCCESS,
	 * true);
	 * 
	 * }else{ //Server error return getServerResponse(ServerResponseCode.ERROR,
	 * false); }
	 * 
	 * }else{ //Job already running return
	 * getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false); }
	 * 
	 * }else{ //Job doesn't exist return
	 * getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false); } }
	 * 
	 * public ServerResponse getServerResponse(int responseCode, Object data){
	 * ServerResponse serverResponse = new ServerResponse();
	 * serverResponse.setStatusCode(responseCode); serverResponse.setData(data);
	 * return serverResponse; }
	 */
}