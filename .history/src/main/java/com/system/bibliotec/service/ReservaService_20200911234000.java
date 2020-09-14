package com.system.bibliotec.service;

import com.system.bibliotec.service.mapper.MapeadorReserva;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.bibliotec.repository.filter.ReservaFilter;
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

		
		Specification<Reservaas> query = Specification.where(
		
		LocacaoSpecification.porID(filter.getIdLocacao()))

		.and(LocacaoSpecification.porUsuarioContexto(filter.getCreatedBy()))  // valor definido no construtor de LocacaoFilter para deixar esta consulta dinamica com base no usuario

		.and(LocacaoSpecification.porIntervaloDataCriacao(filter.getDataLocacaoInicio(), filter.getDataLocacaoFim()))
		.and(LocacaoSpecification.porIntervaloHoraCriacao(filter.getHoraLocacaoInicio(), filter.getHoraLocacaoFim()))

		.and(LocacaoSpecification.porIntervaloDataCancelamento(filter.getDataCancelamentoLocacaoInicio(), filter.getDataCancelamentoLocacaoFim()))
		.and(LocacaoSpecification.porIntervaloHoraCancelamento(filter.getHoraCancelamentoLocacaoInicio(), filter.getHoraCancelamentoLocacaoFim()))

		.and(LocacaoSpecification.porStatus(filter.getStatusLocacao()))

		.and(LocacaoSpecification.porLivroId(filter.getIdExemplar()))

		.and(LocacaoSpecification.porUsuarioId(filter.getIdUsuario()));
				//fim query
		

		return 	mapper.locacaoParaLocacaoVM(locacaoRepository.findAll(query));
	


	}

}
