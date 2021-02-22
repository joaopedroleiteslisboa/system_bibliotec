package com.system.bibliotec.service;

import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.security.SecurityUtils;
import com.system.bibliotec.security.UserSystem;
import com.system.bibliotec.service.mapper.MapeadorReserva;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.repository.filter.ReservaFilter;
import com.system.bibliotec.repository.specification.ReservaSpecification;

import com.system.bibliotec.service.dto.SolicitacaoReservaDTO;
import com.system.bibliotec.service.operacoes.IOperacaoReserva;

@Service
public class ReservaService {

    private final IOperacaoReserva operacao;

    private final MapeadorReserva mapper;

    private final ReservaRepository repository;

    @Autowired
    public ReservaService(IOperacaoReserva operacao, MapeadorReserva mapper, ReservaRepository repository) {
        this.operacao = operacao;
        this.mapper = mapper;
        this.repository = repository;
    }

    public ReservaVM reservaLivro(SolicitacaoReservaDTO reservaDTO) {
        return operacao.reservaLivro(reservaDTO);
    }

    public ReservaCanceladaVM cancelarReserva(Long idReserva) {
        return operacao.cancelarReserva(idReserva);

    }

    public ReservaVM findByIdReservaDoUsuario(Long id) {
        // TODO Auto-generated method stub

        return operacao.findByIdReserva(id);

    }

    public List<ReservaVM> findAllByReservaDoUsuario(Pageable pageable) {
        // TODO Auto-generated method stub

        return operacao.findAllByReservaDoUsuario(pageable);

    }

    public List<ReservaVM> filterQuery(ReservaFilter filter) {

        Optional<UserSystem> usuarioAnonimo = Optional.empty();


        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_ADMIN) || //caso for ADM ou USER SYSTEM listar todas solicitações/reservas...
                SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_USER_SYSTEM)) {

            usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

            if (usuarioAnonimo.isPresent()) {

                filter.setIdUsuario(usuarioAnonimo.get().getId());
            }

        }


        Specification<Reservas> query = Specification.where(ReservaSpecification.porID(filter.getIdReserva()))

                .and(ReservaSpecification.porIntervaloDataCriacao(filter.getDataReservaInicio(),
                        filter.getDataReservaFim()))
                .and(ReservaSpecification.porIntervaloHoraCriacao(filter.getHoraReservaInicio(),
                        filter.getHoraReservaFim()))

                .and(ReservaSpecification.porStatus(filter.getStatus()))

                .and(ReservaSpecification.porLivroId(filter.getIdExemplar()))

                .and(ReservaSpecification.porUsuarioId(filter.getIdUsuario()));
        // fim query

        return mapper.reservaParaReservaVM(repository.findAll(query));

    }

}
