package com.system.bibliotec.service.automacao.trabalhos;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.system.bibliotec.service.automacao.QuartzService;
import com.system.bibliotec.service.validation.ICronJob;



public class CronJob extends QuartzJobBean implements InterruptableJob, ICronJob{
	
	private volatile boolean toStopFlag = true;
	
	@Autowired
	QuartzService jobService;
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		JobKey key = jobExecutionContext.getJobDetail().getKey();
		System.out.println("Cron Job started with key :" + key.getName() + ", Group :"+key.getGroup() + " , Thread Name :"+Thread.currentThread().getName() + " ,Time now :"+new Date());
		
		System.out.println("======================================");
		
		List<Map<String, Object>> list = null;
		try {
			list = (List<Map<String, Object>>) jobService.getAllJobs().getBody();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Job list :"+list);
		System.out.println("======================================");
		
		//*********** For retrieving stored key-value pairs ***********/
		JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
		String myValue = dataMap.getString("myKey");
		System.out.println("Value:" + myValue);

		System.out.println("Thread: "+ Thread.currentThread().getName() +" stopped.");
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		System.out.println("Stopping thread... ");
		toStopFlag = false;
	}

}