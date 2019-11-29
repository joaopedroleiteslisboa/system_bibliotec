package com.system.bibliotec.service.operations;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;

public interface IOperacaoLivro {

	
	public Livro save(Livro livro);
	
	public Livro updateLivro(Long id, Livro livro);
	
	public void updateStatusLivro(Long id, StatusLivro statusLivro);
	
	public void updatePropertyIsbn13Livro(Long id, String isbn13);
	
	public void deleteLivro(Long id);
	
	public Livro findByIdLivro(Long id);
	
	public void acrescentarEstoque(Long idLivro, int quantidade);
	
	public void decrescentarEstoque(Long idLivro, int quantidade);

	
}
