package com.system.bibliotec.service;

import java.util.Optional;

import org.hibernate.validator.internal.constraintvalidators.hv.ISBNValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.EstoqueInsuficienteException;
import com.system.bibliotec.exception.LivroInvalidoOuInexistenteException;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.repository.ClienteRepository;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.service.operations.OperacaoLivro;
import com.system.bibliotec.service.ultis.RandomUtils;
import com.system.bibliotec.service.validation.ValidaLivro;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
