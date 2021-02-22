package com.system.bibliotec.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatilhosVO {

    Date ultimaExecucao;

    Date proximaExecucao;

    Date agendamentoDoTrabalho;

    boolean emExecucao;

    String status;


}
