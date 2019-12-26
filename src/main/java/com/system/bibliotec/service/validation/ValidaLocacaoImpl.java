package com.system.bibliotec.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.LocacaoInvalidaOuInexistenteException;
import com.system.bibliotec.model.Locacao;

@Component
public class ValidaLocacaoImpl implements IvalidaLocacao {

	@Autowired
	private IValidaLivro validaLivro;

	@Autowired
	private IValidaCliente validaCliente;

	@Override
	public void validaLocacao(Locacao locacao) {
		// TODO Auto-generated method stub

		if (locacao.getId() == null) {

			throw new LocacaoInvalidaOuInexistenteException("Operação não realizada. Locacao Invalido ou Inexistente");
		}

		validaCliente.validaCliente(locacao.getCliente());

		validaLivro.validaLivro(locacao.getLivro());

	}

}