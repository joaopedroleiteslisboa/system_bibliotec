package com.system.bibliotec.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExemploJoDataDTO {

    private String nomeSolicitante;

    private String emailSolicitante;

    private String nomeJob;

    private String dataAgendamento;

    private String idDadoParaconsulta;


}
