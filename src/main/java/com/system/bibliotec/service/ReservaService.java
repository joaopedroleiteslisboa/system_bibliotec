package com.system.bibliotec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Reserva;
import com.system.bibliotec.model.enums.StatusReserva;
import com.system.bibliotec.service.operations.IOperacaoReserva;


@Service
public class ReservaService {

	@Autowired
	private IOperacaoReserva operacao;
	
	public Reserva save(Reserva reserva) {
		
		return operacao.save(reserva);
	}

	
	public void updatePropertyLivro(Long idReserva, Livro livro) {
		operacao.updatePropertyLivro(idReserva, livro);
	}

	
	public void updatePropertyCliente(Long idReserva, Cliente cliente) {
		operacao.updatePropertyCliente(idReserva, cliente);
	}

	public void cancelarReserva(Long idReserva) {
		
		operacao.cancelarReserva(idReserva);
	}
	
	public void mudarStatusReserva(Long idReserva, StatusReserva statusReserva) {
		operacao.mudarStatusReserva(idReserva, statusReserva);
	}

	
	public void deleteReserva(Long id) {

		operacao.deleteReserva(id);
	}


	public Reserva findByIdReserva(Long id) {
		// TODO Auto-generated method stub
		
		return operacao.findByIdReserva(id);
			
	}

	

}
