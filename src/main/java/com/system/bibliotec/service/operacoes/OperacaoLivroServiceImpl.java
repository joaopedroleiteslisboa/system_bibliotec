package com.system.bibliotec.service.operacoes;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.EstoqueInsuficienteException;
import com.system.bibliotec.exception.LivroInvalidoOuInexistenteException;
import com.system.bibliotec.exception.QuantidadeInvalidaException;
import com.system.bibliotec.model.Livro;
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
				
		livro.setImagenUrl(ConstantsUtils.DEFAULT_VALUE_URL_PHOTOS_BOOK + RandomUtils.randomIntForUrlPic()
				+ ConstantsUtils.PHOTOS_BOOK_LENGTH_WIDTH_200_X_300);
		livro.setCodBarras(RandomUtils.randomCodBarras()); // TODO: Modificar essa implementação em um ambiente de produção

		log.info("Iniciando processo de Persistencia de Livro: " + livro.getNome());
		return livroRepository.save(livro);
	}

	@Transactional
	@Override
	public Livro updateLivro(Long id, Livro livro) {
		
		Livro livroSalvo = findByIdLivro(id);
		validador.validaLivro(livroSalvo);

		BeanUtils.copyProperties(livro, livroSalvo, "id", "codBarras", "imagenUrl", "statusLivro");

		return livroRepository.save(livroSalvo);
	}

	/*
	 * @Transactional
	 * 
	 * @Override public void updateStatusLivro(Long id, StatusLivro statusLivro) {
	 *  validador.validaLivro(id); Livro
	 * livroSalvo = findByIdLivro(id); //livroSalvo.setStatusLivro(statusLivro);
	 * livroRepository.save(livroSalvo); }
	 */
	

	@Transactional
	@Override
	public void updatePropertyIsbn13Livro(Long id, String isbn13) {

		Livro livroSalvo = findByIdLivro(id);

		validador.validaLivro(livroSalvo);

		livroRepository.save(livroSalvo);
	}

	@Transactional
	@Override
	public void deleteLivro(Long id) {
		
		Livro livroSalvo = findByIdLivro(id);
		livroRepository.deleteById(id);
	}

	
	@Override
	public Livro findByIdLivro(Long id) {
		
		return livroRepository.findById(id)
				.orElseThrow(
						() -> new LivroInvalidoOuInexistenteException("Livro Invalido ou Inexistente. Informe um Livro Valido"));

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
		
		
		Livro livroSalvo = findByIdLivro(idLivro);

		int novaQuantidade = livroSalvo.getQuantidade() - quantidade;

		if (novaQuantidade <= 2) {
			throw new EstoqueInsuficienteException("Estoque insuficiente. Quantidade Limite de dispensa atingida. Operação não realizada");
		}
		livroSalvo.setQuantidade(novaQuantidade);
		
		livroRepository.save(livroSalvo);
	}

	
}
