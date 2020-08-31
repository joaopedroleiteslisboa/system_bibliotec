package com.system.bibliotec.service.vm;

import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter /* View Model*/
public class ReservaCanceladaVM {

    private Long codigo;

    private String statusReserva;

    private String horaCancelamento;

    private String dataCancelamento;

    private String livro;

    private String titular;

    public ReservaCanceladaVM(Reservas entidade){{

        this.codigo = entidade.getId();
        this.statusReserva = entidade.getStatus().toString();
        this.horaCancelamento = HoraDiasDataLocalService.horaLocal().toString();
        this.dataCancelamento = HoraDiasDataLocalService.dataLocal().toString();
        this.livro = entidade.getLivro().getNome();
        this.titular = entidade.getUsuario().getNome();

    }

    }
}
