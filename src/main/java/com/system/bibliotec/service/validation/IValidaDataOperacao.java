package com.system.bibliotec.service.validation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.system.bibliotec.model.AbstractAuditingEntity;
import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.CancelamentoOperacaoLocacaoInvalida;
import com.system.bibliotec.exception.LocacaoLimiteDataException;
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;


public interface IValidaDataOperacao<E extends AbstractAuditingEntity> {


    default void validaDataLimiteLocacao(E e) {

        LocalDate dataCorrente = HoraDiasDataLocalService.dataLocal();

        if (dataCorrente.isAfter(e.getDataPrevisaoTermino())) {
            throw new LocacaoLimiteDataException("Operacão não realizada. "
                    + "Livro com prazo de devolução ultrapassado."
                    + " Necessita-se encerrar a locacão para pode prosseguir com esta operação.");
        }

    }

    default boolean dataLimiteAtingidaOuUltrapassada(E e) {
        return (HoraDiasDataLocalService.dataLocal().isAfter(e.getDataPrevisaoTermino())) ? true : false;

    }

    default boolean isPenultimoDia(E e) {
        return (ChronoUnit.DAYS.between(HoraDiasDataLocalService.dataLocal(), e.getDataPrevisaoTermino()) == 1) ? true : false;

    }

    default boolean isUltimoDia(E e) {
        return (ChronoUnit.DAYS.between(HoraDiasDataLocalService.dataLocal(), e.getDataPrevisaoTermino()) == 0) ? true : false;

    }
}
