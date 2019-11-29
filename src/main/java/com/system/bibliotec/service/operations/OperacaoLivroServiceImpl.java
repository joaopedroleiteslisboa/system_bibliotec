package com.system.bibliotec.service.operations;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.EstoqueInsuficienteException;
import com.system.bibliotec.exception.LivroInvalidoOuInexistenteException;
import com.system.bibliotec.exception.QuantidadeInvalidaException;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.service.ultis.RandomUtils;
import com.system.bibliotec.service.validation.IValidaLivro;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OperacaoLivroServiceImpl implements IOperacaoLivro {

	@Autowired
	private IValidaLivro validador;

	@Autowired
	private LivroRepository livroRepository;

	@Transactional
	@Override
	public Livro save(Livro livro) {
		// TODO Auto-generated method stub

		livro.setStatusLivro(ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LIVRE);
		livro.setImagenUrl(ConstantsUtils.DEFAULT_VALUE_URL_PHOTOS_BOOK + RandomUtils.randomIntForUrlPic()
				+ ConstantsUtils.PHOTOS_BOOK_LENGTH_WIDTH_200_X_300);
		livro.setCodBarras(RandomUtils.randomCodBarras()); // TODO: Modificar essa implementação em um ambiente de
															// produção...
		log.info("Iniciando processo de Persistencia de Livro: " + livro);
		return livroRepository.save(livro);
	}

	@Transactional
	@Override
	public Livro updateLivro(Long id, Livro livro) {
		// TODO Auto-generated method stub
		Livro livroSalvo = findByIdLivro(id);
		validador.validaLivro(livroSalvo.getId(), livroSalvo.getStatusLivro(), livroSalvo.getQuantidade());

		BeanUtils.copyProperties(livro, livroSalvo, "id", "codBarras", "imagenUrl", "statusLivro");

		return livroRepository.save(livroSalvo);
	}

	@Transactional
	@Override
	public void updateStatusLivro(Long id, StatusLivro statusLivro) {
		// TODO Auto-generated method stub
		validador.validaLivro(id);

		Livro livroSalvo = findByIdLivro(id);

		livroSalvo.setStatusLivro(statusLivro);

		livroRepository.save(livroSalvo);
	}

	@Transactional
	@Override
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

	@Transactional
	@Override
	public void deleteLivro(Long id) {
		// TODO Auto-generated method stub

		Livro livroSalvo = findByIdLivro(id);
		validador.validaLivro(id, livroSalvo.getStatusLivro());
		livroRepository.deleteById(id);
	}

	
	@Override
	public Livro findByIdLivro(Long id) {
		// TODO Auto-generated method stub
		Optional<Livro> livroSalvo = livroRepository.findById(id);
		if (!livroSalvo.isPresent()) {
			throw new LivroInvalidoOuInexistenteException("Livro Invalido ou Inexistente. Informe um Livro Valido");
		}
		return livroSalvo.get();
	}

	@Transactional
	@Override
	public void acrescentarEstoque(Long idLivro, int quantidade) {
		
		if(quantidade > 999) {
			throw new QuantidadeInvalidaException("Quantidade Invalida! Informe uma quantidade valida");
		}
		
		Livro livroSalvo = findByIdLivro(idLivro);

		livroSalvo.setQuantidade(livroSalvo.getQuantidade() + quantidade);
		
		livroRepository.save(livroSalvo);
	}

	@Transactional
	@Override
	public void decrescentarEstoque(Long idLivro, int quantidade) {
		// TODO Auto-generated method stub
		
		Livro livroSalvo = findByIdLivro(idLivro);

		int novaQuantidade = livroSalvo.getQuantidade() - quantidade;

		if (novaQuantidade < 2) {
			throw new EstoqueInsuficienteException("Estoque insuficiente. Não foi possivel realizar esta operação");
		}
		livroSalvo.setQuantidade(novaQuantidade);
		
		livroRepository.save(livroSalvo);
	}

	
}
