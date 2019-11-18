package com.system.bibliotec.service;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.*;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Reserva;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.model.enums.StatusReserva;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.service.ultis.CpfUtilsValidator;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.validation.ValidaLivro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

//TODO: Precisa desenvolvedor sobrecarga de metodos para validação ficar mais coerente com um determinado contexto solicitado...
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReservaService {

	private final ReservaRepository repository;

	private final ClienteService clienteService;

	private final LivroService livroService;
	
	private final ValidaLivro validadorLivro;

	// RESERVANDO UM LIVRO.....

	/**
	 * Metodo para criar uma Reserva caso prosseguir com todos os requisitos
	 * requeridos pelo {@link #validaReserva(Long)}
	 *
	 * @param reserva {@link Reserva} para ser analisado e persistido
	 *
	 */
	@Transactional
	public Reserva save(Reserva reserva) {
		log.info("Iniciando Processo de reserva de livro");
		validadorLivro.validaLivro(reserva.getLivro().getId(), reserva.getLivro().getStatusLivro(), reserva.getLivro().getQuantidade());

		clienteService.validandoClienteExistente(reserva.getCliente());

		reserva.setHoraReserva(HoraDiasDataLocalService.horaLocal());

		reserva.setDataReserva(HoraDiasDataLocalService.dataLocal());

		reserva.setDataLimite(HoraDiasDataLocalService.dataReservaLimite());

		reserva.setStatusReserva(ConstantsUtils.DEFAULT_VALUE_STATUSRESERVA);
		
		livroService.updateStatusLivro(reserva.getLivro().getId(), StatusLivro.RESERVADO);
		
		log.info("Livro Reservado:" + reserva.getLivro());
		
		return repository.save(reserva);

	}

	@Transactional
	public void updatePropertyLivro(Long idReserva, Livro livro) {
		Optional<Reserva> reservaSalva = findByIdReserva(idReserva);
		log.info("Iniciando processo de Atualização de atributo livro da Reserva:" + reservaSalva.get());
		validaReservaExistente(reservaSalva.get());

		validadorLivro.validaLivro(livro.getId(), livro.getStatusLivro(), livro.getQuantidade());

		livroService.updateStatusLivro(reservaSalva.get().getLivro().getId(), StatusLivro.LIVRE);

		livroService.updateStatusLivro(livro.getId(), StatusLivro.RESERVADO);

		reservaSalva.get().setLivro(livro);

		repository.save(reservaSalva.get());
		log.info("Reserva Atualizada: " + reservaSalva.get());
	}

	@Transactional
	public void updatePropertyCliente(Long idReserva, Cliente cliente) {
		Optional<Reserva> reservaSalva = findByIdReserva(idReserva);
		validaReservaExistente(reservaSalva.get());
		log.info("Iniciando processo de Atualização de atributo Cliente da Reserva:" + reservaSalva.get());
		clienteService.validandoClienteExistente(cliente);

		reservaSalva.get().setCliente(cliente);

		repository.save(reservaSalva.get());
		log.info("Reserva Atualizada: " + reservaSalva.get());
	}

	@Transactional
	public void deleteReserva(Long id) {

		Optional<Reserva> reservaSalva = findByIdReserva(id);
		log.info("Iniciando processo de remoção de uma Reserva: " + reservaSalva.get());
		// ATUALIZANDO O STATUS DO LIVRO RESERVADO...
		livroService.updateStatusLivro(reservaSalva.get().getLivro().getId(), StatusLivro.LIVRE);
		repository.deleteById(id);
		log.info("Reserva Deletada: " + reservaSalva.get());
	}

	public Optional<Reserva> findByIdReserva(Long id) {
		Optional<Reserva> reservaSalva = findByIdReserva(id);
		if (!reservaSalva.isPresent()) {
			throw new ReservaInexistenteException("Reserva Selecionada Invalida ou Inexistente");
		}
		return reservaSalva;
	}


	public void validaReservaExistente(Reserva reserva) {

		if (reserva.getId() == null) {
			throw new ReservaInexistenteException("Reserva Selecionada Invalida ou Inexistente");
		}

		if (reserva.getLivro() == null) {
			throw new LivroInvalidoOuInexistenteException("Operação não Realizada. Livro Selecionado Invalido");
		}
		if (!CpfUtilsValidator.isCPF(reserva.getCliente().getCpf())) {
			throw new DocumentoInvalidoException("Operação não Realizada.  Documentação do Cliente Invalida");
		}
		if (reserva.getStatusReserva() == StatusReserva.CANCELADA) {
			throw new ReservaCanceladaException("Operação não Realizada.  Reserva Cancelada ou Encerrada");
		}
		if (reserva.getStatusReserva() == StatusReserva.ALUGADA) {
			throw new ReservaLocadaException(
					"Operação não Realizada.  O Intem reservado já estar sob processo de Locação");
		}
		if (reserva.getDataLimite().isBefore(LocalDate.now())) {
			throw new ReservaUpdateException("Operação não Realizada. Data limite de Reserva Ultrapassada");
		}

	}
	
	public void existsByIdReserva(Reserva idReserva) {

		if (!repository.existsById(idReserva.getId())) {

			throw new ReservaInexistenteException("Reserva Inexistente");
		}

	}

}
