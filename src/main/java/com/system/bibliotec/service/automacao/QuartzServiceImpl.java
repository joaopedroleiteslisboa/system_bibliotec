package com.system.bibliotec.service.automacao;

import java.util.*;
import java.util.regex.Pattern;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.AgendadorException;
import com.system.bibliotec.model.enums.TipoErrorSistema;
import com.system.bibliotec.security.SecurityUtils;
import com.system.bibliotec.security.UserSystem;
import com.system.bibliotec.service.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.system.bibliotec.config.SpringSecurityAuditorAware;
import com.system.bibliotec.exception.TrabalhoException;
import com.system.bibliotec.exception.TrabalhoExistenteException;
import com.system.bibliotec.exception.TrabalhoInvalidoException;
import com.system.bibliotec.service.ServicoDoSistema;
import com.system.bibliotec.service.automacao.trabalhos.CronJob;
import com.system.bibliotec.service.mapper.MapeadorAgendamento;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;
import com.system.bibliotec.service.ultis.ConverterStringCamelCase;
import com.system.bibliotec.service.vm.AgendamentoVM;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {

    private final SchedulerFactoryBean schedulerFactoryBean;

    private final ApplicationContext context;

    private final ServicoDoSistema servicoSistema;


    public QuartzServiceImpl(SchedulerFactoryBean schedulerFactoryBean, ApplicationContext context, ServicoDoSistema servicoSistema) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.context = context;
        this.servicoSistema = servicoSistema;

    }


    //===================================================DATE TIME JOB =======================================================================

    public AgendamentoTarefaVO criarTarefaPorData(Class<? extends QuartzJobBean> jobClass, String jobName, Date jobScheduleTime, TipoTrabalhoEnum tipoServicoTrabalho) {


        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;
        String nomeSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getNome() : null;

        log.info(
                "########################################################################################################################");

        log.debug("INICIANDO PROCESSO DE CRIAÇÃO DE NOVA TAREFA DE ", jobClass.getClass().getName().toString()
                + "USUARIO SOLICITANTE: " + emailSolicitante);

        log.info(
                "########################################################################################################################");

        triagemNegocio(jobName, emailSolicitante, tipoServicoTrabalho);

        triagemNovaTarefa(jobName);


        boolean agendado = internalScheduleOneTimeJob(jobName, jobClass, jobScheduleTime, emailSolicitante, nomeSolicitante, tipoServicoTrabalho);

        return AgendamentoTarefaVO.builder().agendado(agendado).dataRetorno(new Date()).build();

    }


    //===================================================CROM JOB =======================================================================


    /**
     * Criar trabalho com base em CRON
     *
     * @throws SchedulerException
     */

    public AgendamentoTarefaVO criarTarefaPorCron(Class<? extends QuartzJobBean> jobClass, String jobName, Date date, String cronExpression, TipoTrabalhoEnum tipoServicoTrabalho) {


        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;
        String nomeSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getNome() : null;


        log.info(
                "########################################################################################################################");

        log.info("INICIANDO PROCESSO DE CRIAÇÃO DE NOVA TAREFA DE ", jobClass.getClass().getName().toString()
                + "USUARIO SOLICITANTE: " + emailSolicitante);

        log.info(
                "########################################################################################################################");

        triagemNegocio(jobName, emailSolicitante, tipoServicoTrabalho);

        triagemNovaTarefa(jobName);

        Date agendado = internalScheduleCronJob(jobClass, jobName, date, cronExpression, emailSolicitante, nomeSolicitante, tipoServicoTrabalho);

        return AgendamentoTarefaVO.builder().agendado(true).dataRetorno(agendado).build();

    }


    private boolean internalScheduleOneTimeJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date, String emailSolicitante,
                                               String nomeSolicitante, TipoTrabalhoEnum tipoServicoTrabalho) {

        String jobKey = jobName;
        String groupKey = "SampleGroup"; // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...
        String triggerKey = jobName;

        JobDetail jobDetail = JobUtilQuartz.createJob(jobClass, true, context, jobKey, groupKey, emailSolicitante, nomeSolicitante, tipoServicoTrabalho);


        Trigger cronTriggerBean = JobUtilQuartz.createSingleTrigger(triggerKey, date, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);


        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Date dt = scheduler.scheduleJob(jobDetail, cronTriggerBean);

            log.info(
                    "########################################################################################################################");


            log.info("########################  JOB " + jobName + " CRIADO COM SUCESSO ########################");

            log.info(
                    "########################################################################################################################");


            return true;
        } catch (SchedulerException e) {

            log.info(
                    "########################################################################################################################");
            log.error(ConstantsUtils.excecaoNovoTrabalho.concat(jobName), e);

            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoNovoTrabalho.concat(jobName));

            throw new AgendadorException(ConstantsUtils.excecaoNovoTrabalho);

        }

    }

    private Date internalScheduleCronJob(Class<? extends QuartzJobBean> jobClass, String jobName, Date date,
                                         String cronExpression, String emailSolicitante, String nomeSolicitante,
                                         TipoTrabalhoEnum tipoServicoTrabalho) {

        String jobKey = jobName;
        String groupKey = "SampleGroup"; // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...
        String triggerKey = jobName;


        JobDetail jobDetail = JobUtilQuartz.createJob(jobClass, true, context, jobKey, groupKey, emailSolicitante, nomeSolicitante, tipoServicoTrabalho);


        Trigger cronTriggerBean = JobUtilQuartz.createCronTrigger(triggerKey, date, cronExpression, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

        try {


            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Date dt = scheduler.scheduleJob(jobDetail, cronTriggerBean);

            return dt;

        } catch (SchedulerException e) {

            log.info(
                    "########################################################################################################################");

            log.error(ConstantsUtils.excecaoNovoTrabalhoCron.concat(jobName), e);

            log.info(
                    "########################################################################################################################");


            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoNovoTrabalhoCron.concat(jobName));

            throw new AgendadorException(ConstantsUtils.excecaoNovoTrabalhoCron.concat(jobName));
        }

    }

    //===================================================CROM JOB =======================================================================


    /**
     * deleta um trabalho
     *
     * @throws SchedulerException
     */

    public boolean deleteJob(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        log.info(
                "########################################################################################################################");

        log.info("INICIANDO PROCESSO DE EXCLUSÃO DE TAREFA DE ROTINA DA APLICAÇÃO. " + "USUARIO SOLICITANTE: "
                + emailSolicitante);
        log.info(
                "########################################################################################################################");

        triagemExclusãoTarefa(jobName);

        String jobKey = jobName;
        String groupKey = "SampleGroup"; // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...

        JobKey jkey = new JobKey(jobKey, groupKey);

        try {
            return schedulerFactoryBean.getScheduler().deleteJob(jkey);

        } catch (SchedulerException e) {

            log.info(
                    "########################################################################################################################");

            log.error(ConstantsUtils.excecaoDeletarTrabalho + jobKey, e);

            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoDeletarTrabalho.concat(jobName));

            throw new AgendadorException(ConstantsUtils.excecaoDeletarTrabalho.concat(jobName));
        }
    }

    /**
     * Atualizar trabalhos definidos por DATETIME
     */

    public AgendamentoTarefaVO updateOneTimeJob(String jobName, Date date) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        String jobKey = jobName;

        try {

            Trigger newTrigger = JobUtilQuartz.createSingleTrigger(jobKey, date, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

            Date atualizado = schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobKey), newTrigger);

            return AgendamentoTarefaVO.builder().dataRetorno(atualizado).agendado(true).build();

        } catch (Exception e) {

            log.info(
                    "########################################################################################################################");
            log.error(ConstantsUtils.excecaoAtualizarTrabalho.concat(jobName), e);
            log.info(
                    "########################################################################################################################");
            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoAtualizarTrabalho.concat(jobName));

            throw new AgendadorException(ConstantsUtils.excecaoAtualizarTrabalho.concat(jobName));
        }
    }


    /**
     * Atualizar trabalhos definidos por CRON
     *
     * @throws SchedulerException
     */

    public AgendamentoTarefaVO updateCronJob(String jobName, Date date, String cronExpression) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        String jobKey = jobName;

        try {

            Trigger newTrigger = JobUtilQuartz.createCronTrigger(jobKey, date, cronExpression, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

            Date atualizado = schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobKey), newTrigger);

            return AgendamentoTarefaVO.builder().dataRetorno(atualizado).agendado(true).build();

        } catch (Exception e) {

            log.info(
                    "########################################################################################################################");

            log.error(ConstantsUtils.excecaoAtualizarTrabalhoCron.concat(jobName), e);

            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoAtualizarTrabalhoCron.concat(jobName));


            throw new AgendadorException(ConstantsUtils.excecaoAtualizarTrabalhoCron.concat(jobName));
        }
    }


    /**
     * cancelar trabalho agendado
     */

    public boolean unScheduleJob(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        boolean status = false;

        if (isJobWithNamePresent(jobName)) {

            String jobKey = jobName;

            TriggerKey tkey = new TriggerKey(jobKey);

            try {
                status = schedulerFactoryBean.getScheduler().unscheduleJob(tkey);

                return status;

            } catch (SchedulerException e) {
                log.info(
                        "########################################################################################################################");

                log.error(ConstantsUtils.excecaoCancelamentoTrabalho.concat(jobName), e);
                log.info(
                        "########################################################################################################################");
                servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                        TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoCancelamentoTrabalho.concat(jobName));

                throw new AgendadorException(ConstantsUtils.excecaoCancelamentoTrabalho.concat(jobName));

            }

        }

        return status;
    }


    public boolean pauseJob(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        if (jobExistente(jobName)) {

            String jobKey = jobName;
            String groupKey = "SampleGroup"; // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...
            JobKey jkey = new JobKey(jobKey, groupKey);

            try {
                schedulerFactoryBean.getScheduler().pauseJob(jkey);


                return true;

            } catch (SchedulerException e) {

                log.info(
                        "########################################################################################################################");
                log.error(ConstantsUtils.excecaoPausarTrabalho.concat(jobName), e);

                log.info(
                        "########################################################################################################################");

                servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                        TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoPausarTrabalho.concat(jobName));


                throw new AgendadorException("Não foi possivel Pausar o Trabalho. Error Interno da Aplicação.");
            }

        } else { // Caso não existir a tarefa...
            throw new AgendadorException("Operação não realizada. Não foi localizado o Job atribuido a esse nome: " + jobName);
        }
    }


    public AgendamentoTarefaVO resumeJob(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        log.debug("INICIANDO PROCESSO PAUSADO DO JOB SOB NOME : " + jobName + " USUARIO SOLICITANTE: " + emailSolicitante);

        if (jobExistente(jobName)) {

            String jobState = getJobState(jobName);

            if (Trigger.TriggerState.PAUSED.equals(jobState)) {


                String jobKey = jobName;
                String groupKey = "SampleGroup"; // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...

                JobKey jKey = new JobKey(jobKey, groupKey);


                try {
                    schedulerFactoryBean.getScheduler().resumeJob(jKey);

                    return AgendamentoTarefaVO.builder().agendado(true).dataRetorno(new Date()).build();

                } catch (SchedulerException e) {

                    log.info(
                            "########################################################################################################################");
                    log.error(ConstantsUtils.excecaoAoRetornarTrabalhoPausado + jobKey, e);

                    log.info(
                            "########################################################################################################################");
                    servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                            TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoAoRetornarTrabalhoPausado.concat(jobName));


                    throw new AgendadorException(ConstantsUtils.excecaoAoRetornarTrabalhoPausado);


                }

            } else {

                throw new AgendadorException(" Operação não realizada. O Job:  " + jobName + " não encontra-se em um estado de Pausa");
            }

        } else {
            throw new AgendadorException(" Operação não realizada. O Job:  " + jobName + " não encontrado");
        }
    }


    public boolean startJobNow(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        triagemSolicitacaoStart(jobName);

        String jobKey = jobName;
        String groupKey = "SampleGroup"; // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...

        JobKey jKey = new JobKey(jobKey, groupKey);

        try {

            schedulerFactoryBean.getScheduler().triggerJob(jKey);

            return true;

        } catch (SchedulerException e) {

            log.info(
                    "########################################################################################################################");
            log.error(ConstantsUtils.excecaoParaIniciarTrabalho + jobKey, e);

            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoParaIniciarTrabalho.concat(jobName));


            throw new AgendadorException(ConstantsUtils.excecaoParaIniciarTrabalho.concat(jobName));

        }
    }


    public boolean isJobRunning(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        boolean isJobRunning = false;
        String jobKey = jobName;
        String groupKey = "SampleGroup";   // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...

        try {

            List<JobExecutionContext> currentJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();

            if (currentJobs != null) {

                for (JobExecutionContext jobCtx : currentJobs) {
                    String jobNameDB = jobCtx.getJobDetail().getKey().getName();
                    String groupNameDB = jobCtx.getJobDetail().getKey().getGroup();

                    if (jobKey.equalsIgnoreCase(jobNameDB) && groupKey.equalsIgnoreCase(groupNameDB)) {
                        isJobRunning = true;
                    }
                }

            } else {
                return isJobRunning;
            }

        } catch (SchedulerException e) {

            log.info(
                    "########################################################################################################################");
            log.error(ConstantsUtils.excecaoParaChecarTrabalhoEmExcecucao + jobKey, e);

            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoParaChecarTrabalhoEmExcecucao.concat(jobName));

            throw new AgendadorException(ConstantsUtils.excecaoParaChecarTrabalhoEmExcecucao.concat(jobName));
        }

        return isJobRunning;

    }


    public RelacaoTarefasVO getAllJobs() {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        RelacaoTarefasVO relacaoTrabalhos = new RelacaoTarefasVO();

        try {

            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            for (String groupName : scheduler.getJobGroupNames()) {


                List<TrabalhoVO> relacaoDoGrupo = capturarTrabalhosDoGrupos(groupName, scheduler);

                relacaoTrabalhos.setRelacaoTrabalhos(relacaoDoGrupo);

            }

        } catch (SchedulerException e) {

            log.info(
                    "########################################################################################################################");
            log.error(ConstantsUtils.excecaoParaChecarTrabalhoEmExcecucao, e);

            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoParaChecarListaDeTrabalhos);

            throw new AgendadorException(ConstantsUtils.excecaoParaChecarListaDeTrabalhos);

        }

        return relacaoTrabalhos;
    }

    private List<TrabalhoVO> capturarTrabalhosDoGrupos(String nomeGrupo, Scheduler scheduler) throws SchedulerException {

        List<TrabalhoVO> subRetorno = new ArrayList<TrabalhoVO>();

        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(nomeGrupo))) {

            TrabalhoVO t = capturarTodosJobsAgrupadosPorTrigger(jobKey, scheduler);
            subRetorno.add(t);

        }
        return subRetorno;

    }


    private TrabalhoVO capturarTodosJobsAgrupadosPorTrigger(JobKey jobKey, Scheduler scheduler) throws SchedulerException {

        TrabalhoVO subRetorno = new TrabalhoVO();

        List<GatilhosVO> gatilhosViewList = new ArrayList<GatilhosVO>();

        String jobName = jobKey.getName();
        String jobGroup = jobKey.getGroup();

        boolean emExecucao = jobEmExecucao(jobName);

        String status = getJobState(jobName);

        @SuppressWarnings("unchecked")
        List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);

        subRetorno.setNomeDoTrabalho(jobName);
        subRetorno.setNomeGrupo(jobGroup);

        for (Trigger t : triggers) {

            Date scheduleTime = t.getStartTime();
            Date nextFireTime = t.getNextFireTime();
            Date lastFiredTime = t.getPreviousFireTime();


            GatilhosVO g = GatilhosVO.builder()
                    .ultimaExecucao(lastFiredTime)
                    .proximaExecucao(nextFireTime)
                    .agendamentoDoTrabalho(scheduleTime)
                    .status(status)
                    .emExecucao(emExecucao)
                    .build();

            gatilhosViewList.add(g);
        }
        subRetorno.setGatilhosDoJob(gatilhosViewList);

        return subRetorno;
    }


    public boolean isJobWithNamePresent(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        boolean exists = false;

        if (jobName == null || jobName.trim().equals("")) {
            throw new AgendadorException("Nome do trabalho Informado é Invalido");
        } else {

            try {
                String groupKey = "SampleGroup";  // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...
                JobKey jobKey = new JobKey(jobName, groupKey);
                Scheduler scheduler = schedulerFactoryBean.getScheduler();

                exists = (scheduler.checkExists(jobKey)) ? true : false;

            } catch (SchedulerException e) {

                log.info(
                        "########################################################################################################################");
                log.error(ConstantsUtils.excecaoParaChecarSeOTrabalhoExiste + jobName, e);

                log.info(
                        "########################################################################################################################");

                servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                        TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoParaChecarSeOTrabalhoExiste.concat(jobName));


                throw new AgendadorException(ConstantsUtils.excecaoParaChecarSeOTrabalhoExiste.concat(jobName));
            }
        }

        return exists;
    }


    public String getJobState(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;


        try {
            String groupKey = "SampleGroup";  // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...
            JobKey jobKey = new JobKey(jobName, groupKey);

            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());

            if (triggers != null && triggers.size() > 0) {

                for (Trigger trigger : triggers) {

                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

                    if (Trigger.TriggerState.PAUSED.equals(triggerState)) {
                        return "PAUSADO";
                    } else if (Trigger.TriggerState.BLOCKED.equals(triggerState)) {
                        return "BLOQUEADO";
                    } else if (Trigger.TriggerState.COMPLETE.equals(triggerState)) {
                        return "COMPLETO";
                    } else if (Trigger.TriggerState.ERROR.equals(triggerState)) {
                        return "COM ERROR";
                    } else if (Trigger.TriggerState.NONE.equals(triggerState)) {
                        return "NAO LOCALIZADO";
                    } else if (Trigger.TriggerState.NORMAL.equals(triggerState)) {
                        return "AGENDADO";
                    }
                }
            } else {

                return "COMPLETO OU SEM TRIGGER DE EXECUÇÃO";
            }


        } catch (SchedulerException e) {


            log.info(
                    "########################################################################################################################");
            log.error(ConstantsUtils.excecaoParaChecarStatusDoTrabalho.concat(jobName), e);


            log.info(
                    "########################################################################################################################");


            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoParaChecarStatusDoTrabalho.concat(jobName));

            throw new AgendadorException(ConstantsUtils.excecaoParaChecarStatusDoTrabalho.concat(jobName));
        }
        return org.apache.commons.lang3.StringUtils.EMPTY;
    }


    public boolean stopJob(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        boolean stop = false;

        triagemSolicitacaoStop(jobName);

        try {
            String jobKey = jobName;
            String groupKey = "SampleGroup"; // valor padrão adotado pela comunidade quatz em caso de não definição de grupo de trabalhos para a Trigger...

            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jkey = new JobKey(jobKey, groupKey);

            stop = scheduler.interrupt(jkey);

        } catch (SchedulerException e) {


            log.info(
                    "########################################################################################################################");
            log.error(ConstantsUtils.excecaoParaPararUmTrabalho + jobName, e);


            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, emailSolicitante, ConstantsUtils.excecaoParaChecarStatusDoTrabalho.concat(jobName));

            throw new AgendadorException(ConstantsUtils.excecaoParaPararUmTrabalho.concat(jobName));
        }

        return stop;
    }


    private Class<?> getClassForName(String nameJob) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        String camelCaseNameClass = convertStringToCamelCase(nameJob);

        String classPackage = "com.agendador.tarefas.service.jobs.".concat(camelCaseNameClass);

        Class job = null;
        try {

            job = getClass().forName(classPackage);

            if (!job.getSuperclass().equals(QuartzJobBean.class)) {

                throw new AgendadorException(
                        "Exceção: Implementação do Trabalho inexistente ou invalida. Informa outra valida e existente.");
            }

        } catch (ClassNotFoundException e) {

            log.info(
                    "########################################################################################################################");

            log.error("EXCEÇÃO PARA CAPTURAR A CLASSE DE IMPLEMENTAÇÃO DO JOB" + nameJob, e);

            log.info(
                    "########################################################################################################################");

            servicoSistema.salvarErrorContextoGenerico(e, Thread.currentThread().getStackTrace()[1].getMethodName(),
                    TipoErrorSistema.TAREFA_SERVICE, "CAPTURA DE CLASSE DE TRABALHO", "EXCEÇÃO PARA CAPTURAR A CLASSE DE IMPLEMENTAÇÃO DO JOB" + nameJob);


            throw new AgendadorException(
                    "Exceção Interna: Implementação do Trabalho " + nameJob + "inexistente ou invalida. Informa outra valida e existente.");
        }

        return job;

    }

    private void triagemSolicitacaoStart(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        if (jobExistente(jobName)) {

            if (jobEmExecucao(jobName)) {
                throw new AgendadorException("Tarefa já estar em Estado de Execução");
            }

        }
    }

    private void triagemSolicitacaoStop(String jobName) {
        // TODO Auto-generated method stub

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        if (jobExistente(jobName)) {

            if (jobEmExecucao(jobName)) {

            } else {
                throw new AgendadorException("Tarefa não estar em estado de  Execução");
            }
        }
    }

    private void triagemExclusãoTarefa(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;

        if (isContainsSpecialChar(jobName) || !jobNameIsCamelCaseValid(jobName) || !qtdEspacosValidos(jobName)) {
            throw new AgendadorException(
                    "Nome do trabalho invalido. Informar um nome valido e com espaçamento.");
        }

        if (jobExistente(jobName)) {

            if (jobEmExecucao(jobName)) {

                throw new AgendadorException(
                        "Operação não realizada. Trabalho em Execução no momento. Será necessario mudar o estado para solicitar esta operação.");
            } else {
                throw new AgendadorException("Trabalho já cadastrado. Informar um outro valido e existente.");
            }

        } else {
            throw new AgendadorException(ConstantsUtils.excecaoImplementacaoTarefaNaoEncontrada);

        }
    }


    private void triagemNovaTarefa(String jobName) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;


        if (isJobWithNamePresent(jobName)) {

            if (jobEmExecucao(jobName)) {

                throw new AgendadorException(
                        "Trabalho já cadastrado e em execução no momento. É necessario Informar outro valido e existente.");
            } else {
                throw new AgendadorException("Trabalho já cadastrado. Informar um outro nome valido.");
            }

        }

    }

    private boolean isContainsSpecialChar(String msg) {

        Optional<UserSystem> usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

        String emailSolicitante = usuarioAnonimo.isPresent() ? usuarioAnonimo.get().getEmail() : null;


        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

        return (regex.matcher(msg).find()) ? true : false;
    }

    private boolean qtdEspacosValidos(String texto) {
        return (texto.length() - texto.replaceAll(" ", "").length() > 1) ? false : true;
    }

    private boolean jobNameIsCamelCaseValid(String jobName) {
        // TODO Auto-generated method stub

        return (jobName != null && !jobName.isEmpty() && jobName.contains(" ")) ? true : false;
    }

    private boolean jobExistente(String jobName) {
        // TODO Auto-generated method stub
        boolean exist = false;

        if (isJobWithNamePresent(jobName)) {
            exist = true;
        } else {
            return exist;
        }
        return exist;
    }

    private boolean jobEmExecucao(String jobName) {
        // TODO Auto-generated method stub
        boolean emExecucao = false;

        emExecucao = isJobRunning(jobName);
        return emExecucao;
    }


    private void triagemNegocio(String jobName, String emailSolicitante,
                                TipoTrabalhoEnum tipoServicoTrabalho) {


        if (emailSolicitante == null || emailSolicitante.isEmpty())
            throw new AgendadorException("Não é possivel agendar um trabalho sem informar o Email do Solicitante. Este recurso é necessario para acompanhamento do status da Tarefa");

        if (tipoServicoTrabalho == null)
            throw new AgendadorException("Não é possivel agendar um trabalho de Relatorio sem informar o Tipo de Serviço a ser utilizado pelo Trabalho agendado");


        if (jobName == null)
            throw new AgendadorException("Não é possivel agendar um trabalho de Relatorio sem informar o nome");


    }


    private String convertStringToCamelCase(String text) {
        return org.apache.commons.lang3.StringUtils.remove(WordUtils.capitalizeFully(text, ' '), " ");
    }
}
