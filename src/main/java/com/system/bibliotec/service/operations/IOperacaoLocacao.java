package com.system.bibliotec.service.operations;

import com.system.bibliotec.model.Locacao;
import com.system.bibliotec.model.enums.StatusLocacao;

public interface IOperacaoLocacao {

	
	public Locacao realizarLocacao(Locacao locacao);
	
	public void renovarLocacao(Long id);
	
	public void updatePropertyLivro(Long idLocacao, Long idNovoLivro);
	
	public void updatePropertyCliente(Long idLocacao, String  cpfNovoCliente);
	
	public void checarDataLimiteAtingida();
	
	public void cancelarLocacao(Long id);
	
	public void devolucaoLivro(Long idLocacao);
	
	public void updatePropertyStatusLocacao(Long idLocacao, StatusLocacao statusLocacao);
	
	public Locacao findByIdLocacao(Long id);



	

	
	
	
	

	
}
