package com.system.bibliotec.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CancelamentoReservaDTO {


    @NotNull(message = "É necessario informa uma Reserva para iniciar o processo de cancelamento")
    private Long idReserva;
}
