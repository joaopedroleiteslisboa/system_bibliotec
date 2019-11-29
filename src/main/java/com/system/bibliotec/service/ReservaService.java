package com.system.bibliotec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Reserva;
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

	
	public void deleteReserva(Long id) {

		operacao.deleteReserva(id);
	}

	

}
