package com.system.bibliotec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Locacao;
import com.system.bibliotec.model.enums.StatusLocacao;
import com.system.bibliotec.service.operations.IOperacaoLocacao;

@Service
public class LocacaoService {

	@Autowired
	private IOperacaoLocacao operacao;

	public Locacao realizarLocacao(Locacao locacao) {

		return operacao.realizarLocacao(locacao);

	}

	public void renovarLocacao(Long id) {
		operacao.renovarLocacao(id);
	}

	public void updatePropertyLivro(Long idLocacao, Long idLivro) {

		operacao.updatePropertyLivro(idLocacao, idLivro);
	}

	public void updatePropertyCliente(Long idLocacao, String cpfNovoCliente) {
		operacao.updatePropertyCliente(idLocacao, cpfNovoCliente);
	}

	public void checarDataLimiteAtingida(Long idEmprestimo, StatusLocacao statusLocacao) {

	}

	public void cancelarLocacao(Long idLocacao){
		operacao.cancelarLocacao(idLocacao);
	}

	public void devolucaoLivro(Long idLocacao) {

		operacao.devolucaoLivro(idLocacao);
	}

	public void updatePropertyStatusLocacao(Long id, StatusLocacao statusLocacao) {
		operacao.updatePropertyStatusLocacao(id, statusLocacao);
	}

	public Locacao findByIdLocacao(Long id) {
		return operacao.findByIdLocacao(id);
	}

}
