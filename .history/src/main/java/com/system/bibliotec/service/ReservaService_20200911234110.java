package com.system.bibliotec.service;

import com.system.bibliotec.service.mapper.MapeadorReserva;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.system.bibliotec.repository.filter.ReservaFilter;
import com.system.bibliotec.repository.specification.ReservaSpecification;
import com.system.bibliotec.service.dto.ReservaDTO;
import com.system.bibliotec.service.operacoes.IOperacaoReserva;

@Service
public class ReservaService {

	@Autowired
	private IOperacaoReserva operacao;

	@Autowired
	private MapeadorReserva mapper;

	
	
	
	public ReservaVM reservaLivro(ReservaDTO reservaDTO) {
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

		
		Specification<Reservas> query = Specification.where(
		
		ReservaSpecification.porID(filter.getIdLocacao()))

		.and(ReservaSpecification.porUsuarioContexto(filter.getCreatedBy()))  // valor definido no construtor de LocacaoFilter para deixar esta consulta dinamica com base no usuario

		.and(ReservaSpecification.porIntervaloDataCriacao(filter.getDataLocacaoInicio(), filter.getDataLocacaoFim()))
		.and(ReservaSpecification.porIntervaloHoraCriacao(filter.getHoraLocacaoInicio(), filter.getHoraLocacaoFim()))

		.and(ReservaSpecification.porIntervaloDataCancelamento(filter.getDataCancelamentoLocacaoInicio(), filter.getDataCancelamentoLocacaoFim()))
		.and(ReservaSpecification.porIntervaloHoraCancelamento(filter.getHoraCancelamentoLocacaoInicio(), filter.getHoraCancelamentoLocacaoFim()))

		.and(ReservaSpecification.porStatus(filter.getStatusLocacao()))

		.and(ReservaSpecification.porLivroId(filter.getIdExemplar()))

		.and(ReservaSpecification.porUsuarioId(filter.getIdUsuario()));
				//fim query
		

		return 	mapper.locacaoParaLocacaoVM(locacaoRepository.findAll(query));
	


	}

}
