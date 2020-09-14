package com.system.bibliotec.service;

import com.system.bibliotec.service.mapper.MapeadorReserva;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

	/*
	 * public void updatePropertyUsuario(Long idReserva, String email) {
	 * operacao.updatePropertyUsuario(idReserva, email); }
	 * 
	 * public void updatePropertyLivro(Long idReserva, Livro livro) {
	 * operacao.updatePropertyLivro(idReserva, livro); }
	 * 
	 * public void mudarStatusReserva(Long idReserva, Status statusReserva) {
	 * operacao.mudarStatusReserva(idReserva, statusReserva); }
	 */

}
