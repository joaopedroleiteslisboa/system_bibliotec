package com.system.bibliotec.service.automacao.trabalhos;

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
import com.system.bibliotec.service.validation.IDateJob;

public class SimpleJob extends QuartzJobBean implements InterruptableJob, IDateJob {

    private volatile boolean toStopFlag = true;

    @Autowired
    QuartzService jobService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("Simple Job started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + Thread.currentThread().getName());

        System.out.println("======================================");


        System.out.println("======================================");

        //*********** For retrieving stored key-value pairs ***********/
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        String myValue = dataMap.getString("myKey");
        System.out.println("Value:" + myValue);

        //*********** For retrieving stored object, It will try to deserialize the bytes Object. ***********/
		/*
		SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        YourClass yourClassObject = (YourClass) schedulerContext.get("storedObjectKey");
		 */

        while (toStopFlag) {
            try {
                System.out.println("Test Job Running... Thread Name :" + Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " stopped.");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("Stopping thread... ");
        toStopFlag = false;
    }

    @Override
    public String toString() {
        return " DEU CERTOOO ACHOU A CLASSE SimpleJob [toStopFlag=" + toStopFlag + ", jobService=" + jobService + "]";
    }


}