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
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.service.ultis.RandomUtils;
import com.system.bibliotec.service.validation.ValidaLivro;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private ValidaLivro validador;

	/**
	 * Metodo para Registrar um novo Livro.
	 * 
	 * @param livro {@link Livro} Objeto com as novas Informações a ser persistida e
	 *              analisada {@link #validaLivroNovo1()}
	 */

	public Livro save(Livro livro) {

		livro.setStatusLivro(ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO);
		livro.setImagenUrl(ConstantsUtils.DEFAULT_VALUE_URL_PHOTOS_BOOK + RandomUtils.randomIntForUrlPic()
				+ ConstantsUtils.PHOTOS_BOOK_LENGTH_WIDTH_200_X_300);
		livro.setCodBarras(RandomUtils.randomCodBarras()); // TODO: Modificar essa implementação em um ambiente de
															// produção...
		log.info("Iniciando processo de Persistencia de Livro: " + livro);
		return livroRepository.save(livro);

	}

	/**
	 * Metodo para Atualizar um Livro.
	 *
	 * @param id {@link Long} id do Livro Existente para ser buscada e analisada
	 *           pelo metodo {@link #validaLivroExistente()}
	 */

	public Livro updateLivro(Long id, Livro livro) {

		Livro livroSalvo = findByIdLivro(id);
		validador.validaLivro(livroSalvo.getId(), livroSalvo.getStatusLivro(), livroSalvo.getQuantidade());

		BeanUtils.copyProperties(livro, livroSalvo, "id", "codBarras", "imagenUrl", "statusLivro");

		return livroRepository.save(livroSalvo);
	}

	public void updateStatusLivro(Long id, StatusLivro statusLivro) {
		validador.validaLivro(id);

		Livro livroSalvo = findByIdLivro(id);

		livroSalvo.setStatusLivro(statusLivro);

		livroRepository.save(livroSalvo);
	}

	public void updatePropertyIsbn13Livro(Long id, String isbn13) {
		// TODO: Estar retornando null... verificar depois
		/*
		 * System.out.println(isbn13); if (!isbnValidator.isValid(isbn13, null)) { throw
		 * new IsbnInvalidoException("ISBN 13 Invalido. Informe outro valido"); }
		 */
		Livro livroSalvo = findByIdLivro(id);

		validador.validaLivro(livroSalvo.getId(), livroSalvo.getStatusLivro());

		livroSalvo.setIsbn13(isbn13);

		livroRepository.save(livroSalvo);

	}

	public void baixarEstoque(Integer quantidade, Long idLivro) {
		Livro livroSalvo = findByIdLivro(idLivro);

		int novaQuantidade = livroSalvo.getQuantidade() - quantidade;

		if (novaQuantidade < 0) {
			throw new EstoqueInsuficienteException();
		}
		livroSalvo.setQuantidade(novaQuantidade);
		livroRepository.save(livroSalvo);
	}

	public void adicionarEstoque(Integer quantidade, Long idLivro) {
		Livro livroSalvo = findByIdLivro(idLivro);

		livroSalvo.setQuantidade(livroSalvo.getQuantidade() + quantidade);
	}

	public void deleteLivro(Long id) {
		Livro livroSalvo = findByIdLivro(id);
		validador.validaLivro(id, livroSalvo.getStatusLivro());
		livroRepository.deleteById(id);

	}

	public Livro findByIdLivro(Long id) {
		Optional<Livro> livroSalvo = livroRepository.findById(id);
		if (!livroSalvo.isPresent()) {
			throw new LivroInvalidoOuInexistenteException("Livro Invalido ou Inexistente. Informe um Livro Valido");
		}
		return livroSalvo.get();
	}



}
