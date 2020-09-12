package com.system.bibliotec.service.operacoes;


import java.util.List;

import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;
import org.springframework.data.domain.Pageable;


import com.system.bibliotec.service.dto.SolicitacaoReservaDTO;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;

public interface IOperacaoReserva extends IAuditorTokenDeUsuarioDoContexto {

	public ReservaVM reservaLivro(SolicitacaoReservaDTO reserva);

	public ReservaCanceladaVM cancelarReserva(Long idReserva);

	public ReservaVM findByIdReserva(Long idReserva);
	
	public List<ReservaVM>  findAllByReservaDoUsuario(Pageable pageable);
	
	
	
	
	
	
}
