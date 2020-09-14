package com.system.bibliotec.service.mapper;

import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.service.vm.SolicitacaoVM;

import org.springframework.stereotype.Component;

@Component
public class MapeadorSolicitacao {
    
    public SolicitacaoVM reservaParaReservaVM(Solicitacoes s) {
        return new SolicitacaoVM(s);
    }

    public ReservaCanceladaVM reservaParaReservaCanceladaVM(Reservas r) {
        return new ReservaCanceladaVM(r);
    }

    public List<ReservaVM> reservaParaReservaVM(List<Reservas> r) {
        return r.stream().filter(Objects::nonNull).map(this::reservaParaReservaVM).collect(Collectors.toList());
    }

    public List<ReservaCanceladaVM> reservaParaReservaCanceladaVM(List<Reservas> r) {
        return r.stream().filter(Objects::nonNull).map(this::reservaParaReservaCanceladaVM).collect(Collectors.toList());
    }



}