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
	public void validaLivro(Integer estoque) {
		// TODO Auto-generated method stub
		if (estoque <= 3) {
			throw new EstoqueInsuficienteException(
					"Estoque insuficiente. Quantidade Limite de dispensa atingida. Operação não realizada");
		}
	}



	@Override
	public void validaLivro(Livro livro) {
		// TODO Auto-generated method stub
		validaLivro(livro.getId());
		validaLivro(livro.getQuantidade());
		
	}



	
	

}
