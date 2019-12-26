package com.system.bibliotec.service.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.CancelamentoOperacaoLocacaoInvalida;
import com.system.bibliotec.model.Locacao;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;

@Component
public class ValidaDataLocacaoImpl implements IValidaDataLocacao{

	
	@Override
	public void validaDataLocacao(Locacao locacao) {
		// TODO Auto-generated method stub
			

		LocalDate dataCorrente = HoraDiasDataLocalService.dataLocal();
		
		if(dataCorrente.isAfter(locacao.getDataEncerramento())) {
			throw new CancelamentoOperacaoLocacaoInvalida("Operacão não realizada. Livro com prazo ultrapassado. Necessita encerrar a locacão para prosseguir.");
		}
		
		
	}
	
	
	
	

}
