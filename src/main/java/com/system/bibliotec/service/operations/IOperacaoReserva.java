package com.system.bibliotec.service.operations;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Reserva;

public interface IOperacaoReserva {

	public Reserva save(Reserva reserva);
	
	public void updatePropertyLivro(Long idReserva, Livro livro);
	
	public void updatePropertyCliente(Long idReserva, Cliente cliente);
	
	public void deleteReserva(Long id);
	
	public boolean existsByIdReserva(Reserva idReserva);
	
	
}
