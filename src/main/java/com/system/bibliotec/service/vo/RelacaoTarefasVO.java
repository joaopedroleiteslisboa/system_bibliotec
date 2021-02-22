package com.system.bibliotec.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RelacaoTarefasVO {

    List<TrabalhoVO> relacaoTrabalhos;
}
