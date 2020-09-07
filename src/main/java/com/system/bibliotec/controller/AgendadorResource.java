package com.system.bibliotec.controller;

import java.util.Date;

import org.quartz.SchedulerException;
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
import com.system.bibliotec.service.mapper.MapeadorAgendamento;
import com.system.bibliotec.service.vm.AgendamentoVM;

@RestController@RequestMapping("/agendador")
public class AgendadorResource {

  private final QuartzService apiQuartz;

  private final AgendadorService agendadorService;

  public AgendadorResource(AgendadorService agendadorService, @Lazy QuartzService apiQuartz) {
    this.agendadorService = agendadorService;
    this.apiQuartz = apiQuartz;
  }

  @RequestMapping("/scheduleDate")
  public ResponseEntity < AgendamentoVM > scheduleDate(@RequestParam("jobName") String jobName,

  @RequestParam("jobScheduleTime")@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime)
  throws SchedulerException {

    return apiQuartz.scheduleDate(jobName, jobScheduleTime);

  }

  @RequestMapping("/scheduleCron")
  public ResponseEntity < AgendamentoVM > scheduleCron(@RequestParam("jobName") String jobName,

  @RequestParam("jobScheduleTime")@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime,

  @RequestParam(name = "cronExpression", required = false) String cronExpression) throws SchedulerException {

    return apiQuartz.scheduleCron(jobName, jobScheduleTime, cronExpression);

  }

  @ResponseStatus(HttpStatus.OK)@RequestMapping("/unschedule")
  public void unschedule(@RequestParam("jobName") String jobName) {

    apiQuartz.unScheduleJob(jobName);
  }

  @RequestMapping("/delete")
  public ResponseEntity < AgendamentoVM > delete(@RequestParam("jobName") String jobName) throws SchedulerException {

    return apiQuartz.deleteJob(jobName);
  }

  @RequestMapping("/pause")
  public ResponseEntity < AgendamentoVM > pause(@RequestParam("jobName") String jobName) throws SchedulerException {

    return apiQuartz.pauseJob(jobName);
  }

  @RequestMapping("/resume")
  public ResponseEntity < AgendamentoVM > resume(@RequestParam("jobName") String jobName) throws SchedulerException {
    return apiQuartz.resumeJob(jobName);
  }

  @RequestMapping("update")
  public ResponseEntity <AgendamentoVM>  updateJob(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression) throws Exception{
			
			
			if(cronExpression == null || cronExpression.trim().equals("")){
				//Single Trigger
				return apiQuartz.updateOneTimeJob(jobName, jobScheduleTime);
				
							
			}else{
				//Cron Trigger
				return apiQuartz.updateCronJob(jobName, jobScheduleTime, cronExpression);
								
			}
	}

	@RequestMapping("jobs")
	public ResponseEntity <AgendamentoVM> getAllJobs() throws SchedulerException{
		System.out.println("JobController.getAllJobs()");

		return apiQuartz.getAllJobs();		
	}

	@RequestMapping("checkJobName")
	public ServerResponse checkJobName(@RequestParam("jobName") String jobName){
		System.out.println("JobController.checkJobName()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}
		
		boolean status = jobService.isJobWithNamePresent(jobName);
		return getServerResponse(ServerResponseCode.SUCCESS, status);
	}

	@RequestMapping("isJobRunning")
	public ServerResponse isJobRunning(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.isJobRunning()");

		boolean status = jobService.isJobRunning(jobName);
		return getServerResponse(ServerResponseCode.SUCCESS, status);
	}

	@RequestMapping("jobState")
	public ServerResponse getJobState(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.getJobState()");

		String jobState = jobService.getJobState(jobName);
		return getServerResponse(ServerResponseCode.SUCCESS, jobState);
	}

	@RequestMapping("stop")
	public ServerResponse stopJob(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.stopJob()");

		if(jobService.isJobWithNamePresent(jobName)){

			if(jobService.isJobRunning(jobName)){
				boolean status = jobService.stopJob(jobName);
				if(status){
					return getServerResponse(ServerResponseCode.SUCCESS, true);
				}else{
					//Server error
					return getServerResponse(ServerResponseCode.ERROR, false);
				}

			}else{
				//Job not in running state
				return getServerResponse(ServerResponseCode.JOB_NOT_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("start")
	public ServerResponse startJobNow(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.startJobNow()");

		if(jobService.isJobWithNamePresent(jobName)){

			if(!jobService.isJobRunning(jobName)){
				boolean status = jobService.startJobNow(jobName);

				if(status){
					//Success
					return getServerResponse(ServerResponseCode.SUCCESS, true);

				}else{
					//Server error
					return getServerResponse(ServerResponseCode.ERROR, false);
				}

			}else{
				//Job already running
				return getServerResponse(ServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(ServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}


}