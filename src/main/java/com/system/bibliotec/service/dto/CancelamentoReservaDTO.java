package com.system.bibliotec.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CancelamentoReservaDTO {


    @NotNull(message = "É necessario informa uma Reserva para iniciar o processo de cancelamento")
    private Long idReserva;
}
