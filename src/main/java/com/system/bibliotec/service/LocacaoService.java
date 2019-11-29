package com.system.bibliotec.service;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.*;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Locacao;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.model.enums.StatusLocacao;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.service.operations.IOperacaoLocacao;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.validation.IValidaCliente;
import com.system.bibliotec.service.validation.IValidaLivro;
import com.system.bibliotec.service.validation.ValidaLivro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LocacaoService {
	
	private static final Integer DEFAULT_COUNT = 1;
	
	
	@Autowired
	private IOperacaoLocacao operacao;
	
	public Locacao realizarLocacao(Locacao locacao) {
				
		return operacao.realizarLocacao(locacao);

	}

	
	public void renovarLocacao(Long id) {
		
	}

	@Transactional
	public void updatePropertyLivro(Long idLocacao, Long idLivro) {
		
	}

	public void updatePropertyCliente(Long idLocacao, Long idCliente) {
		
	}

//ADICIONAR @Scheduled para alterar o status do emprestimo caso o prazo de entrega seja atingido ou ultrapassado 
	public void checarDataLimiteAtingida(Long idEmprestimo, StatusLocacao statusLocacao) {

	}
	//TODO: Implementar serviço de disparo de email notificando o cliente...
	public void cancelarLocacao(Long id) {
		
	}
	//TODO: Implementar serviço de disparo de email notificando o cliente...
	public void devolucaoLivro(Long id) {
		
		
		
	}

	@Transactional
	private void updatePropertyStatusLocacao(Long id, StatusLocacao statusLocacao) {
		Optional<Locacao> locacaoSalva = findByIdLocacao(id);
		log.info("Iniciando Processo de Atualização de Locação: Atualizando propriedade Status:" + locacaoSalva.get());
		validaLocacaoExistente(locacaoSalva.get());

		locacaoSalva.get().setStatusLocacao(statusLocacao);

		locacaoRepository.save(locacaoSalva.get());
		log.info("Processo de Atualização de Locação de livro realizada:" + locacaoSalva.get());
	}

	public Optional<Locacao> findByIdLocacao(Long id) {

		Optional<Locacao> locacaoSalva = locacaoRepository.findById(id);
		if (!locacaoSalva.isPresent()) {
			throw new LocacaoInvalidaOuInexistenteException(
					"Locação Invalida ou Inexistente. Informe uma Locação Valida");
		}
		return locacaoSalva;
	}

	private void validarLocacao(Locacao locacao) {
	
		if (locacao.getId() == null) {

			throw new LocacaoInvalidaOuInexistenteException("Operação não realizada. Livro Invalido ou Inexistente");
		}

		if (locacao.getCliente() == null) {

			throw new ClienteInexistenteException("Operação não realizada. Cliente Inexistente");
		}
		if (locacao.getCliente().getStatusCliente() == StatusCliente.INADIMPLENTE) {

			throw new ClienteInadimplenteException("Operação não realizada devido Inadimplencia.");
		}

		if (locacao.getLivro().getStatusLivro() == StatusLivro.RESERVADO) {

			throw new LivroReservadoException();
		}
		if (locacao.getLivro().getStatusLivro() == StatusLivro.LOCADO) {

			throw new LivroLocadoException(
					"Operação não realizada. Livro selecionado estar Locado. Selecione outro Exemplar");
		}

	}

	private void validaLocacaoExistente(Locacao locacao) {



		if (locacao.getCliente().getStatusCliente() == StatusCliente.INADIMPLENTE) {

			throw new ClienteInadimplenteException("Operação não realizada. Cliente Inadinplente.");
		}
		if (locacao.getStatusLocacao() == StatusLocacao.CANCELADA) {

			throw new LocacaoUpdateException(
					"Operação não realizada. Locacao Cancelada ou Encerrada. Selecione outra Locação para Atualizar");
		}
		if (locacao.getQuantidadeDeRenovacao() == 3) {

			throw new LocacaoLimiteException(
					"Operação não realizada. Quantidade de Renovação Excedida. Selecione outro exemplar para prosseguir com tal Operação");
		}

		if (locacao.getDataTerminoLocacao().isBefore(LocalDate.now())) {

			throw new LocacaoLimiteDataException("Operação não realizada. Data de Termino da Locação ultrapassada.");
		}

	}	
		

}
