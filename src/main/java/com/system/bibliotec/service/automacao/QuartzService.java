package com.system.bibliotec.service.automacao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.system.bibliotec.service.vo.AgendamentoTarefaVO;
import com.system.bibliotec.service.vo.RelacaoTarefasVO;
import com.system.bibliotec.service.vo.TipoTrabalhoEnum;
import org.quartz.SchedulerException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.system.bibliotec.service.vm.AgendamentoVM;

public interface QuartzService {


    public AgendamentoTarefaVO criarTarefaPorData(Class<? extends QuartzJobBean> jobClass, String jobName, Date jobScheduleTime, TipoTrabalhoEnum tipoServicoTrabalho);

    public AgendamentoTarefaVO criarTarefaPorCron(Class<? extends QuartzJobBean> jobClass, String jobName, Date date, String cronExpression, TipoTrabalhoEnum tipoServicoTrabalho);

    public boolean deleteJob(String jobName);

    public AgendamentoTarefaVO updateOneTimeJob(String jobName, Date date);

    public AgendamentoTarefaVO updateCronJob(String jobName, Date date, String cronExpression);

    public boolean unScheduleJob(String jobName);

    public boolean pauseJob(String jobName);

    public AgendamentoTarefaVO resumeJob(String jobName);

    public boolean startJobNow(String jobName);

    public boolean isJobRunning(String jobName);

    public RelacaoTarefasVO getAllJobs();

    public boolean isJobWithNamePresent(String jobName);

    public String getJobState(String jobName);

    public boolean stopJob(String jobName);
}
