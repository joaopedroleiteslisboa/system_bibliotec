package com.system.bibliotec.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrabalhoVO {

    String nomeDoTrabalho;
    String nomeGrupo;
    TipoTrabalhoEnum tipoTrabalho;

    List<GatilhosVO> gatilhosDoJob;


}
