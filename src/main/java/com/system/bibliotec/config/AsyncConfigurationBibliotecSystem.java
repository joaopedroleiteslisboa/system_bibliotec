package com.system.bibliotec.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.system.bibliotec.config.async.ExceptionHandlingAsyncTaskExecutor;

/**
 * AsyncConfiguration
 */
@Configuration
@EnableAsync
public class AsyncConfigurationBibliotecSystem implements AsyncConfigurer, ApplicationContextAware {

    private final Logger log = LoggerFactory.getLogger(AsyncConfigurationBibliotecSystem.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TaskExecutionProperties taskExecutionProperties;


    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub

        this.applicationContext = applicationContext;
    }

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        log.debug("Criando Async Task Executor system");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(taskExecutionProperties.getPool().getCoreSize());
        executor.setMaxPoolSize(taskExecutionProperties.getPool().getMaxSize());
        executor.setQueueCapacity(taskExecutionProperties.getPool().getQueueCapacity());
        executor.setThreadNamePrefix(taskExecutionProperties.getThreadNamePrefix());
        return new ExceptionHandlingAsyncTaskExecutor(executor);
    }


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        CustomAsyncExceptionHandler demoHandler = new CustomAsyncExceptionHandler();
        demoHandler.setApplicationContext(applicationContext);
        return demoHandler;
    }


}