package com.system.bibliotec.service.validation;

import org.springframework.stereotype.Service;

import com.system.bibliotec.exception.EstoqueInsuficienteException;
import com.system.bibliotec.exception.LivroAvariadoException;
import com.system.bibliotec.exception.LivroInvalidoOuInexistenteException;
import com.system.bibliotec.exception.LivroLocadoException;
import com.system.bibliotec.exception.LivroReservadoException;
import com.system.bibliotec.model.enums.StatusLivro;
@Service
public class ValidaLivroImpl implements ValidaLivro {

	@Override
	public void validaLivro(Long id) {
		if (id == null) {
			throw new LivroInvalidoOuInexistenteException("Livro Invalido ou inexistente Exceptio");
		}
	}

	@Override
	public void validaLivro(StatusLivro statusLivro) {
		// TODO Auto-generated method stub

		if (statusLivro == StatusLivro.RESERVADO) {
			throw new LivroReservadoException("O livro selecionado estar Reservado. Operação não Realizada");
		}

		if (statusLivro == StatusLivro.LOCADO) {
			throw new LivroLocadoException("O livro selecionado estar Locado. Operação não Realizada");
		}

		if (statusLivro == StatusLivro.AVARIADO) {

			throw new LivroAvariadoException("O livro selecionado estar Avariado. Operação não Realizada");
		}
	}

	@Override
	public void validaLivro(Integer estoque) {
		// TODO Auto-generated method stub
		if (estoque == 3) {
			throw new EstoqueInsuficienteException(
					"Estoque insuficiente. Quantidade Limite de dispensa atingida. Operação não realizada");
		}
	}

	@Override
	public void validaLivro(Long id, StatusLivro status) {

		validaLivro(id);
		validaLivro(status);
	}

	@Override
	public void validaLivro(Long id, StatusLivro status, Integer estoque) {

		validaLivro(id);
		validaLivro(status);
		validaLivro(estoque);
	}

}
