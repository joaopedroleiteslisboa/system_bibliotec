package com.system.bibliotec.service.operations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.ReservaInexistenteException;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Reserva;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.service.LivroService;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.validation.IValidaCliente;
import com.system.bibliotec.service.validation.IValidaLivro;
import com.system.bibliotec.service.validation.IValidaReserva;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperacaoReservaImpl implements IOperacaoReserva {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	private LivroService livroService;

	@Autowired
	private IValidaLivro validadorLivro;

	@Autowired
	private IValidaCliente validadorCliente;

	@Autowired
	private IValidaReserva validadorReserva;

	@Transactional
	@Override
	public Reserva save(Reserva reserva) {
		// TODO Auto-generated method stub
		log.info("Iniciando Processo de reserva de livro");

		validadorLivro.validaLivro(reserva.getLivro());

		validadorCliente.validaCliente(reserva.getCliente());

		reserva.setHoraReserva(HoraDiasDataLocalService.horaLocal());

		reserva.setDataReserva(HoraDiasDataLocalService.dataLocal());

		reserva.setDataLimite(HoraDiasDataLocalService.dataReservaLimite());

		reserva.setStatusReserva(ConstantsUtils.DEFAULT_VALUE_STATUSRESERVA_ATIVA);
		
		livroService.updateStatusLivro(reserva.getLivro().getId(), StatusLivro.RESERVADO);
		
		livroService.decrescentarEstoque(reserva.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO);

		log.info("Livro Reservado:" + reserva.getLivro());

		return repository.save(reserva);
	}

	@Transactional
	@Override
	public void updatePropertyLivro(Long idReserva, Livro livro) {
		// TODO Auto-generated method stub

		Reserva reservaSalva = findByIdReserva(idReserva);
		
		log.info("Iniciando processo de Atualização de atributo livro da Reserva:" + reservaSalva);
		
		validadorReserva.validaReserva(reservaSalva);

		validadorLivro.validaLivro(livro);

		livroService.acrescentarEstoque(reservaSalva.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);

		// Atualizando status do livro da corrente reserva para Status livre...
		livroService.updateStatusLivro(reservaSalva.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LIVRE);

		livroService.acrescentarEstoque(livro.getId(), ConstantsUtils.DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO);
		
		// Atualizando status do livro a ser reservado Status reservado...
		livroService.updateStatusLivro(livro.getId(), ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_RESERVADO);

		reservaSalva.setLivro(livro);

		repository.save(reservaSalva);

		log.info("Reserva Atualizada: " + reservaSalva);
	}

	@Transactional
	@Override
	public void updatePropertyCliente(Long idReserva, Cliente cliente) {
		// TODO Auto-generated method stub
		Reserva reservaSalva = findByIdReserva(idReserva);

		log.info("Iniciando processo de Atualização de atributo Cliente da Reserva:" + reservaSalva);

		validadorCliente.validaCliente(cliente);

		reservaSalva.setCliente(cliente);

		repository.save(reservaSalva);

		log.info("Reserva Atualizada: " + reservaSalva);
	}

	@Transactional
	@Override
	public void deleteReserva(Long id) {
		// TODO Auto-generated method stub

		Reserva reservaSalva = findByIdReserva(id);

		log.info("Iniciando processo de remoção de uma Reserva: " + reservaSalva);

		livroService.acrescentarEstoque(reservaSalva.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);
		
		livroService.updateStatusLivro(reservaSalva.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LIVRE);

		repository.deleteById(id);

		log.info("Reserva Deletada: " + reservaSalva);
	}

	private Reserva findByIdReserva(Long id) {
		Optional<Reserva> reservaSalva = repository.findById(id);
		if (!reservaSalva.isPresent()) {
			throw new ReservaInexistenteException("Reserva Selecionada Invalida ou Inexistente");
		}
		return reservaSalva.get();
	}

	public boolean existsByIdReserva(Reserva idReserva) {

		if (!repository.existsById(idReserva.getId())) {

			return false;
		}
		return true;

	}

}
