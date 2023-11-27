package com.system.bibliotec.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.system.bibliotec.exception.AgendadorException;
import com.system.bibliotec.service.automacao.trabalhos.CronJob;
import com.system.bibliotec.service.automacao.trabalhos.SimpleJob;
import com.system.bibliotec.service.dto.ExemploJoDataDTO;
import com.system.bibliotec.service.dto.ExemploJobCronDTO;
import com.system.bibliotec.service.vo.AgendamentoTarefaVO;
import com.system.bibliotec.service.vo.RelacaoTarefasVO;
import com.system.bibliotec.service.vo.TipoTrabalhoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.system.bibliotec.service.automacao.QuartzService;


@RestController
@RequestMapping("/schedule")
public class AgendadorResource {

    private final QuartzService apiQuartz;

    @Autowired
    public AgendadorResource(@Lazy QuartzService apiQuartz) {

        this.apiQuartz = apiQuartz;
    }


    @GetMapping  // retorna todos os jobs salvos no quartz
    public ResponseEntity<RelacaoTarefasVO> getAllJobs() {

        return ResponseEntity.ok(apiQuartz.getAllJobs());
    }


    @PostMapping("/job-exemplo-data")
    public AgendamentoTarefaVO exemploJobPorData(@RequestBody ExemploJoDataDTO dto) throws ParseException {

        if (dto.getDataAgendamento() == null || dto.getDataAgendamento().isEmpty())
            throw new AgendadorException("Não é possivel agendar um job sem data para execução");


        Date dataConvertida = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dto.getDataAgendamento());

        return apiQuartz.criarTarefaPorData(SimpleJob.class, dto.getNomeJob(), dataConvertida, TipoTrabalhoEnum.PROCESSO_GENERICO);
    }


    @PostMapping("/job-exemplo-cron")
    public AgendamentoTarefaVO exemploJobPorCron(@RequestBody ExemploJobCronDTO dto) throws ParseException {

        Date dataConvertida = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dto.getDataAgendamento());

        return apiQuartz.criarTarefaPorCron(CronJob.class, dto.getNomeJob(), dataConvertida,
                dto.getCron_expression(), TipoTrabalhoEnum.PROCESSO_GENERICO);

    }


    @PutMapping("/pause")
    public boolean pause(@RequestParam("jobName") String jobName) {

        return apiQuartz.pauseJob(jobName);
    }

    @PutMapping("/resumir")
    public ResponseEntity<AgendamentoTarefaVO> resume(@RequestParam("jobName") String jobName) {
        return ResponseEntity.ok(apiQuartz.resumeJob(jobName));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<AgendamentoTarefaVO> updateJob(@RequestParam("jobName") String jobName,
                                                         @RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime,
                                                         @RequestParam("cronExpression") String cronExpression
    ) {

        if (cronExpression == null || cronExpression.trim().equals("")) {
            // Single Trigger
            return ResponseEntity.ok(apiQuartz.updateOneTimeJob(jobName, jobScheduleTime));

        } else {
            // Cron Trigger
            return ResponseEntity
                    .ok(apiQuartz.updateCronJob(jobName, jobScheduleTime, cronExpression));

        }
    }

    @GetMapping("/checar")
    public ResponseEntity<?> checkJobName(@RequestParam("jobName") String jobName
    ) {

        return ResponseEntity.ok(apiQuartz.isJobWithNamePresent(jobName));

    }

    @GetMapping("/em-execucao")
    public ResponseEntity<?> isJobRunning(@RequestParam("jobName") String jobName
    ) {

        return ResponseEntity.ok(apiQuartz.isJobRunning(jobName));

    }

    @GetMapping("/estado")
    public ResponseEntity<?> getJobState(@RequestParam("jobName") String jobName) {
        System.out.println("JobController.getJobState()");

        return ResponseEntity.ok(apiQuartz.getJobState(jobName));

    }

    @PutMapping("/parar")
    public ResponseEntity<?> stopJob(@RequestParam("jobName") String jobName) {

        return ResponseEntity.ok(apiQuartz.stopJob(jobName));

    }

    @PutMapping("/iniciar")
    public ResponseEntity<?> startJobNow(@RequestParam("jobName") String jobName) {

        return ResponseEntity.ok(apiQuartz.startJobNow(jobName));

    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("jobName") String jobName) {
        return ResponseEntity.ok(apiQuartz.deleteJob(jobName));

    }

    @PutMapping("/remover-agendamento")
    public ResponseEntity<?> unschedule(@RequestParam("jobName") String jobName) {

        return ResponseEntity.ok(apiQuartz.unScheduleJob(jobName));
    }
}