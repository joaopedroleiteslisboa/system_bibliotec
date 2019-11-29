package com.system.bibliotec.service.validation;

import com.system.bibliotec.exception.*;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;
import org.springframework.stereotype.Component;
@Component
public class ValidaLivroImpl implements IValidaLivro {

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

	@Override
	public void validaLivro(Livro livro) {
		// TODO Auto-generated method stub
		validaLivro(livro.getId(), livro.getStatusLivro(), livro.getQuantidade());
		
	}

}
