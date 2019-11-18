package com.system.bibliotec.service;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.service.operations.OperacaoLivro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LivroService {

	private final OperacaoLivro operacao;

	public Livro save(Livro livro) {

		return operacao.save(livro);

	}

	public Livro updateLivro(Long id, Livro livro) {

		return operacao.updateLivro(id, livro);
	}

	public void updateStatusLivro(Long id, StatusLivro statusLivro) {
		operacao.updateStatusLivro(id, statusLivro);
	}

	public void updatePropertyIsbn13Livro(Long id, String isbn13) {
		operacao.updatePropertyIsbn13Livro(id, isbn13);

	}

	public void decrescentarEstoque(Long idLivro, Integer quantidade) {
		operacao.decrescentarEstoque(idLivro, quantidade);
	}

	public void acrescentarEstoque(Long idLivro, Integer quantidade) {
		operacao.acrescentarEstoque(idLivro, quantidade);
	}

	public void deleteLivro(Long id) {
		operacao.deleteLivro(id);

	}

	public Livro findByIdLivro(Long id) {
		return operacao.findByIdLivro(id);
	}

}
