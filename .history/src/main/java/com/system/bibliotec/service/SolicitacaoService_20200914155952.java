package com.system.bibliotec.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.system.bibliotec.exception.RecursoNaoEncontradoException;
import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.TipoPessoa;
import com.system.bibliotec.model.enums.TipoSolicitacao;
import com.system.bibliotec.repository.SolicitacaoRepository;
import com.system.bibliotec.repository.filter.SolicitacaoFilter;
import com.system.bibliotec.repository.specification.SolicitacaoSpecification;
import com.system.bibliotec.service.vm.SolicitacaoVM;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository repository;

    public SolicitacaoService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public Solicitacoes findByIdSolicitacao(Long id) {

        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException(
                "Operação Não realizada. Solicitação do Cliente não foi Localizada"));
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

        repository.save(contexto); // flush

    }

    @Transactional
    public void updateStatusAndDescricao(Status status, Long id, String descricao) {

        Solicitacoes contexto = findByIdSolicitacao(id);

        contexto.setStatus(status);
        contexto.setDescricao(descricao);
        repository.saveAndFlush(contexto); // flush

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStatusAndDescricao(Solicitacoes entityContext, Status status, boolean isRejeitado) {

        entityContext.setStatus(status);
        entityContext.setRejeitado(isRejeitado);
        repository.saveAndFlush(entityContext); // flush

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStatusAndDescricao(Solicitacoes entityContext, Status status, String descricao,
            boolean isRejeitado) {

        entityContext.setStatus(status);
        entityContext.setDescricao(descricao);
        entityContext.setRejeitado(isRejeitado);

        repository.saveAndFlush(entityContext); // flush

    }

    @Transactional
    public void updateStatusAndDescricao(Status status, Long id, String descricao, boolean isRejeitado) {

        Solicitacoes contexto = findByIdSolicitacao(id);

        contexto.setStatus(status);
        contexto.setDescricao(descricao);
        contexto.setRejeitado(isRejeitado);
        repository.saveAndFlush(contexto); // flush

    }


    public List<SolicitacaoVM> filterQuery(SolicitacaoFilter filter) {

		

		Specification<Solicitacoes> query = Specification.where(SolicitacaoSpecification.porID(filter.getIdSolicitacao()))

				.and(SolicitacaoSpecification.porUsuarioContexto(filter.getCreatedBy())) // valor definido no construtor de
																						// LocacaoFilter para deixar
																						// esta consulta dinamica com
																						// base no usuario

				.and(SolicitacaoSpecification.porIntervaloDataSolicitacao(filter.getDataSolicitacaoInicio(),
                        filter.getDataSolicitacaoFim()))
                        
				.and(SolicitacaoSpecification.porIntervaloHoraSolicitacao(filter.getHoraSolicitacaoInicio(),
						filter.getHoraSolicitacaoFim()))

                .and(SolicitacaoSpecification.porStatus(filter.getStatus()))
                
                .and(SolicitacaoSpecification.porTipo(filter.getTipo()))

				.and(SolicitacaoSpecification.porLivroId(filter.getIdExemplar()))

				.and(SolicitacaoSpecification.porUsuarioId(filter.getIdUsuario()));
		// fim query

		return mapper.reservaParaReservaVM(repository.findAll(query));

	}







}