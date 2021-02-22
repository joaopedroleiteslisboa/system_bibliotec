package com.system.bibliotec.service.automacao.trabalhos;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.system.bibliotec.model.AbstractAuditingEntity;
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.service.MailService;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.validation.IValidaDataOperacao;
import com.system.bibliotec.model.enums.Status;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TarefaAnaliseLocacaoReserva implements IValidaDataOperacao<AbstractAuditingEntity> {

    private final LocacaoRepository locacaoRepository;

    private final ReservaRepository reservaRepository;

    private final UserService userService;

    private final MailService servicoEmail;

    @Autowired
    public TarefaAnaliseLocacaoReserva(LocacaoRepository locacaoRepository, ReservaRepository reservaRepository,
                                       UserService userService, MailService servicoEmail) {

        this.locacaoRepository = locacaoRepository;
        this.reservaRepository = reservaRepository;
        this.userService = userService;
        this.servicoEmail = servicoEmail;
    }

    @Async
    public void checarLocacoesEReservasAtrasadas() {
        checarLocacoesAtrasada();
        checarReservasAtrasada();

    }

    @Async
    public void checarLocacoesAtrasada() {
        checarLocacoesAtrasadaSynchronized();
    }

    @Async
    public void checarReservasAtrasada() {
        checarReservasAtrasadaSynchronized();
    }

    private synchronized void checarLocacoesAtrasadaSynchronized() {

        final String DEFAULT_MESSAGE_TO_USER = "Data de Devolução do Livro atingida.";

        log.info("Iniciando Tarefa de analise de locações ativas com data Limite de Devolução ultrapassadas: ");

        locacaoRepository.findAllGenericObjectAtivo().ifPresent(o -> {

            o.stream().map((Function<? super Locacoes, ? extends Locacoes>) l -> {

                if (isPenultimoDia(l)) {

                    log.info("Penultimo dia Atingido para data de Renovação da Locação: " + l.toString());
                    servicoEmail.sendPenultimoDiaOperacao(l.getUsuario());
                    log.info("Notificando titular: " + l.getUsuario().saudacoes()
                            + " sobre Penultimo dia da data de Renovação da Locação atingido");

                } else if (isUltimoDia(l)) {

                    log.info("Ultimo dia Atingido para Locação: " + l.toString());
                    servicoEmail.sendUltimoDiaOperacao(l.getUsuario());
                    log.info("Notificando titular: " + l.getUsuario().saudacoes() + " sobre Ultimo dia atingido");

                } else if (dataLimiteAtingidaOuUltrapassada(l)) {

                    log.info("Data de renovação ou Devolução atingida para a Locação: " + l.toString());
                    servicoEmail.sendDataUltrapassadaOperacao(l.getUsuario());
                    log.info("Notificando titular: " + l.getUsuario().saudacoes()
                            + " sobre bloqueio de sua Conta pelo motivo " + DEFAULT_MESSAGE_TO_USER);

                    l.setStatus(Status.ATRASADA);

                    locacaoRepository.save(l);
                    locacaoRepository.flush();

                    userService.bloquearAcesso(true, DEFAULT_MESSAGE_TO_USER, l.getUsuario().getEmail());


                }
                return null;
            });
        });

    }

    private synchronized void checarReservasAtrasadaSynchronized() {

        log.info("Iniciando Tarefa de analise de Reservas ativas com data Limite para Locação ultrapassadas: ");

        reservaRepository.findAllGenericObjectAtivo().ifPresent(o -> {

            o.stream().map((Function<? super Reservas, ? extends Reservas>) r -> {

                if (isUltimoDia(r)) {

                    servicoEmail.sendUltimoDiaOperacao(r.getUsuario());
                    log.info("Notificando titular: " + r.getUsuario().saudacoes()
                            + " sobre Ultimo dia da Data da Reserva do Exemplar para Locação");

                } else if (dataLimiteAtingidaOuUltrapassada(r)) {

                    servicoEmail.sendDataUltrapassadaOperacao(r.getUsuario());
                    log.info("Notificando titular: " + r.getUsuario().saudacoes()
                            + " sobre cancelamento da Reserva devido a data Limite estar ultrapassada");
                    r.setStatus(Status.CANCELADA);
                    reservaRepository.saveAndFlush(r);
                }
                return null;
            });
        });

    }

}
