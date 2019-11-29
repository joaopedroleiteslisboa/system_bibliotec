package com.system.bibliotec.service.validation;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;

/**
 * Contrato de Validação da Entidade/Modelo Livro {@link Livro}.
 */

public interface IValidaLivro {
	
	void validaLivro(Livro livro);
	
	void validaLivro(Long id);
			
	void validaLivro(StatusLivro statusLivro);
	
	void validaLivro(Integer estoque); 
	
	void validaLivro(Long id, StatusLivro status);
			
	void validaLivro(Long id, StatusLivro status, Integer estoque);

	
	
	

}

