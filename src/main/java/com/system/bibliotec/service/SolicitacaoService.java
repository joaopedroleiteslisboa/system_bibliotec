package com.system.bibliotec.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.system.bibliotec.exception.OperacaoCanceladaException;
import com.system.bibliotec.exception.RecursoNaoEncontradoException;
import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.StatusProcessamento;
import com.system.bibliotec.model.enums.TipoPessoa;
import com.system.bibliotec.model.enums.TipoSolicitacao;
import com.system.bibliotec.repository.SolicitacaoRepository;
import com.system.bibliotec.repository.filter.SolicitacaoFilter;
import com.system.bibliotec.repository.specification.SolicitacaoSpecification;
import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.security.SecurityUtils;
import com.system.bibliotec.security.UserSystem;
import com.system.bibliotec.service.dto.FormCancelamentoSolicitacaoLocacao;
import com.system.bibliotec.service.dto.SolicitacaoLocacaoDTO;
import com.system.bibliotec.service.mapper.MapeadorSolicitacao;
import com.system.bibliotec.service.vm.SolicitacaoVM;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository repository;

    private final MapeadorSolicitacao mapper;


    public SolicitacaoService(SolicitacaoRepository repository, MapeadorSolicitacao mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Solicitacoes findByIdSolicitacao(Long id) {

        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(
                "Solicitação não foi Localizada em nossos registros"));
    }


    @Transactional
    public SolicitacaoVM solicitarLocacao(SolicitacaoLocacaoDTO dto) {

        Solicitacoes entity = mapper.dtoLocacaoParaEntidade(dto);

        return mapper.entytiParaEntidadeVM(repository.saveAndFlush(entity));
    }


    @Transactional
    public SolicitacaoVM solicitarCancelamentoDeSolicitacaoLocacao(FormCancelamentoSolicitacaoLocacao dto) {

        Solicitacoes entity = findByIdSolicitacao(dto.getIdSolicitacao());

        triagemCancelamentoSolicitacaoLocacao(entity);

        entity.setStatus(Status.CANCELADA);

        entity.setDescricao(dto.getMotivoCancelamento());

        return mapper.entytiParaEntidadeVM(repository.saveAndFlush(entity));
    }


    @Transactional
    public void gravarHistorico(Solicitacoes entity) {

        repository.save(entity);
        repository.flush();
    }

    @Transactional
    public void updateStatus(Status status, Long id) {

        Solicitacoes contexto = findByIdSolicitacao(id);

        contexto.setStatus(status);

        repository.save(contexto);

    }

    @Transactional
    public void updateStatusAndDescricao(Status status, Long id, String descricao) {

        Solicitacoes contexto = findByIdSolicitacao(id);

        contexto.setStatus(status);
        contexto.setDescricao(descricao);
        repository.saveAndFlush(contexto);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStatusAndDescricao(Solicitacoes entityContext, Status status, boolean isRejeitado) {

        entityContext.setStatus(status);
        entityContext.setRejeitado(isRejeitado);
        repository.saveAndFlush(entityContext);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStatusAndDescricao(Solicitacoes entityContext, Status status, String descricao,
                                         boolean isRejeitado) {

        entityContext.setStatus(status);
        entityContext.setDescricao(descricao);
        entityContext.setRejeitado(isRejeitado);

        repository.saveAndFlush(entityContext);

    }

    @Transactional
    public void updateStatusAndDescricao(Status status, Long id, String descricao, boolean isRejeitado) {

        Solicitacoes contexto = findByIdSolicitacao(id);

        contexto.setStatus(status);
        contexto.setDescricao(descricao);
        contexto.setRejeitado(isRejeitado);
        repository.saveAndFlush(contexto);

    }


    public List<SolicitacaoVM> filterQuery(SolicitacaoFilter filter) {

        Optional<UserSystem> usuarioAnonimo = Optional.empty();


        if (!SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_ADMIN) || //caso não for ADM ou USER SYSTEM listar apenas os dados vinculado ao principal online
                !SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_USER_SYSTEM)) {

            usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

            if (usuarioAnonimo.isPresent()) {

                filter.setIdUsuario(usuarioAnonimo.get().getId());
            }

        }


        Specification<Solicitacoes> query = Specification.where(SolicitacaoSpecification.porID(filter.getIdSolicitacao()))

                .and(SolicitacaoSpecification.porUsuarioContexto(filter.getCreatedBy()))

                .and(SolicitacaoSpecification.porIntervaloDataSolicitacao(filter.getDataSolicitacaoInicio(),
                        filter.getDataSolicitacaoFim()))

                .and(SolicitacaoSpecification.porIntervaloHoraSolicitacao(filter.getHoraSolicitacaoInicio(),
                        filter.getHoraSolicitacaoFim()))

                .and(SolicitacaoSpecification.porStatus(filter.getStatus()))

                .and(SolicitacaoSpecification.porTipo(filter.getTipo()))

                .and(SolicitacaoSpecification.porLivroId(filter.getIdExemplar()))

                .and(SolicitacaoSpecification.porUsuarioId(filter.getIdUsuario()));
        // fim query

        return mapper.entytiParaEntidadeVM(repository.findAll(query));

    }


    private void triagemCancelamentoSolicitacaoLocacao(Solicitacoes entity) {

        if (entity.getStatus() == Status.CANCELADA) {

            throw new OperacaoCanceladaException("Operação não permitida. Esta Solicitação já se encontra em estado de Cancelamento");
        }

        if (entity.getStatusProcessamento() == StatusProcessamento.EM_PROCESSAMENTO) {

            throw new OperacaoCanceladaException("Operação não permitida. Esta  Solicitação já se encontra em Processamento para homologação. Aguarde mais alguns instantes que poderá cancelar a Locação em sequida");
        }

    }


}