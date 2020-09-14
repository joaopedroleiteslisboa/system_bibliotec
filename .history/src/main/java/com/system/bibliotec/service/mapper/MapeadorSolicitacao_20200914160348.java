package com.system.bibliotec.service.mapper;

import java.util.List;

import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.service.vm.SolicitacaoVM;

import org.springframework.stereotype.Component;

@Component
public class MapeadorSolicitacao {
    
    public SolicitacaoVM entytiParaEntidadeVM(Solicitacoes s) {
        return new SolicitacaoVM(s);
    }

    public SolicitacaoVM entytiParaEntidadeVM(Solicitacoes s, String nomeExemplar) {
        return new SolicitacaoVM(s, nomeExemplar);
    }

   
    public List<SolicitacaoVM> reservaParaReservaVM(List<Reservas> r) {
        return r.stream().filter(Objects::nonNull).map(this::reservaParaReservaVM).collect(Collectors.toList());
    }

    public List<ReservaCanceladaVM> reservaParaReservaCanceladaVM(List<Reservas> r) {
        return r.stream().filter(Objects::nonNull).map(this::reservaParaReservaCanceladaVM).collect(Collectors.toList());
    }



}