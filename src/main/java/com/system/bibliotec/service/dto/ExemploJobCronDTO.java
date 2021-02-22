package com.system.bibliotec.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExemploJobCronDTO {


    private String nomeJob;

    private String dataAgendamento;

    private String cron_expression;


}
