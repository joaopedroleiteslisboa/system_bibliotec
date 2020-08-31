package com.system.bibliotec.config.quartz;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzSchedulerConfig {

	private final DataSource dataSource;

	private final ApplicationContext applicationContext;

	private final TriggerListner triggerListner;

	private final JobsListener jobsListener;

	@Autowired
	public QuartzSchedulerConfig(DataSource dataSource, ApplicationContext applicationContext,
			TriggerListner triggerListner, JobsListener jobsListener) {
		this.dataSource = dataSource;
		this.applicationContext = applicationContext;
		this.triggerListner = triggerListner;
		this.jobsListener = jobsListener;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		factory.setQuartzProperties(quartzProperties());
		factory.setGlobalTriggerListeners(triggerListner);
		factory.setGlobalJobListeners(jobsListener);
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		factory.setJobFactory(jobFactory);
		
		return factory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();

		return propertiesFactoryBean.getObject();
	}
}
