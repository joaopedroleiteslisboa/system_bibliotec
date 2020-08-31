package com.system.bibliotec.service.operacoes;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;

public interface IOperacaoLivro extends IAuditorTokenDeUsuarioDoContexto{

	
	public Livro save(Livro livro);
	
	public Livro updateLivro(Long id, Livro livro);
	
	public void updatePropertyIsbn13Livro(Long id, String isbn13);
	
	public void deleteLivro(Long id);
	
	public Livro findByIdLivro(Long id);
	
	public void acrescentarEstoque(Long idLivro, int quantidade);
	
	public void decrescentarEstoque(Long idLivro, int quantidade);

	
}
