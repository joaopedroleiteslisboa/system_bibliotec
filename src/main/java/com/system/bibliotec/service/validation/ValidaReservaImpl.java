package com.system.bibliotec.service.validation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.bibliotec.exception.ReservaCanceladaException;
import com.system.bibliotec.exception.ReservaInexistenteException;
import com.system.bibliotec.exception.ReservaLocadaException;
import com.system.bibliotec.exception.ReservaUpdateException;
import com.system.bibliotec.model.Reserva;
import com.system.bibliotec.model.enums.StatusReserva;

public class ValidaReservaImpl implements IValidaReserva{

	@Autowired
	private IValidaLivro validaLivro;
	
	@Autowired
	private IValidaCliente validaCliente;
	
	@Override
	public void validaReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
		if (reserva.getId() == null) {
			throw new ReservaInexistenteException("Reserva Selecionada Invalida ou Inexistente");
		}
		
		if (reserva.getStatusReserva() == StatusReserva.CANCELADA) {
			throw new ReservaCanceladaException("Operação não Realizada.  Reserva Cancelada ou Encerrada");
		}
		
		if (reserva.getStatusReserva() == StatusReserva.ALUGADA) {
			throw new ReservaLocadaException(
					"Operação não Realizada.  O Intem reservado já estar sob processo de Locação");
		}
		
		if (reserva.getDataLimite().isBefore(LocalDate.now())) {
			throw new ReservaUpdateException("Operação não Realizada. Data limite de Reserva Ultrapassada");
		}

				
		validaReservaLivro(reserva);
		
		validaReservaCliente(reserva);
	}

	private void validaReservaCliente(Reserva reserva) {
		validaCliente.validaCliente(reserva.getCliente());
	}

	private void validaReservaLivro(Reserva reserva) {
		validaLivro.validaLivro(reserva.getLivro());
	}
	
	
	

}
