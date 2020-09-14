package com.system.bibliotec.service.operacoes;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.exceptions.OperationCancelledException;
import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.CancelamentoLocacaoException;
import com.system.bibliotec.exception.CancelamentoOperacaoLocacaoInvalida;
import com.system.bibliotec.exception.EncerramentoOperacaoLocacaoException;
import com.system.bibliotec.exception.LocacaoInvalidaOuInexistenteException;
import com.system.bibliotec.exception.LocacaoUpdateException;
import com.system.bibliotec.exception.OperacaoCanceladaException;
import com.system.bibliotec.exception.QuantidadeRenovacaoLocacaoLimiteException;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.TipoSolicitacao;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.repository.SolicitacaoRepository;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.service.LivroService;
import com.system.bibliotec.service.SolicitacaoService;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.dto.AtendimentoLocacaoDTO;
import com.system.bibliotec.service.dto.CancelamentoLocacaoDTO;
import com.system.bibliotec.service.dto.DevolucaoLocacaoDTO;
import com.system.bibliotec.service.dto.DespachoSolicitacaoLocacaoDTO;
import com.system.bibliotec.service.mapper.MapeadorLocacao;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.validation.ITriagemReservaELocacao;
import com.system.bibliotec.service.validation.IValidaLivro;
import com.system.bibliotec.service.validation.IValidaPessoa;
import com.system.bibliotec.service.validation.IvalidaUsuarioTriagemInicial;
import com.system.bibliotec.service.vm.LocacaoCancelamentoVM;
import com.system.bibliotec.service.vm.LocacaoDevolucaoVM;
import com.system.bibliotec.service.vm.LocacaoVM;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OperacaoLocacaoImpl implements IOperacaoLocacao {

	private final IvalidaUsuarioTriagemInicial validaUsuarioPessoa;

	private final ITriagemReservaELocacao<Locacoes, Livro, Long, Usuario> triagemInicialLocacao;

	private final LivroService livroService;

	private final LivroRepository livroRepository;

	private final IValidaLivro validadorLivro;

	private final LocacaoRepository locacaoRepository;

	private final UserService userService;

	private final UsuarioRepository userRepository;

	private final IValidaPessoa validadorCliente;

	private final MapeadorLocacao mapper;

	private final ReservaRepository reservaRepository;

	private final SolicitacaoService solicitacaoService;

	@Autowired
	public OperacaoLocacaoImpl(IvalidaUsuarioTriagemInicial validaUsuarioPessoa,
			ITriagemReservaELocacao<Locacoes, Livro, Long, Usuario> triagemInicialLocacao, LivroService livroService,
			LivroRepository livroRepository, IValidaLivro validadorLivro, LocacaoRepository locacaoRepository,
			UserService userService, UsuarioRepository userRepository, IValidaPessoa validadorCliente,
			MapeadorLocacao mapper, ReservaRepository reservaRepository, SolicitacaoService solicitacaoService) {

		this.validaUsuarioPessoa = validaUsuarioPessoa;
		this.triagemInicialLocacao = triagemInicialLocacao;
		this.livroService = livroService;
		this.livroRepository = livroRepository;
		this.validadorLivro = validadorLivro;
		this.locacaoRepository = locacaoRepository;
		this.userService = userService;
		this.userRepository = userRepository;
		this.validadorCliente = validadorCliente;
		this.mapper = mapper;
		this.reservaRepository = reservaRepository;
		this.solicitacaoService = solicitacaoService;
	}

	@Transactional
	@Override
	public LocacaoVM despacharPedidoLocacao(DespachoSolicitacaoLocacaoDTO dto) {

		Usuario funcionario = userService.findOneByUsuarioContexto();

		Usuario cliente = userService.findByIdCliente(dto.getIdClienteSolicitante());

		Solicitacoes solicitacaoUsuario = solicitacaoService.findByIdSolicitacao(dto.getIdSolicitacao());

		Livro l = livroService.findByIdLivro(dto.getIdLivro());

		log.info("Usuario " + cliente.getEmail() + " Iniciando Processo de triagem para Locação do livro: "
				+ l.getNome());

		try {
			validadorCliente.validacaoFisicaEJuridica(cliente);
		} catch (Exception ex) { // ex é validações de negocios
			// Encerrando processo de locação por conter inconsistência no cadastro do
			// Cliente
			solicitacaoService.updateStatusAndDescricao(Status.RECUSADA, dto.getIdSolicitacao(), ex.getMessage(), true);

			throw new OperacaoCanceladaException("Triagem de Documentação. " + ex.getMessage());
		}

		try {
			validadorLivro.validaLivro(l);
		} catch (Exception ex) { // ex é validações de negocios

			solicitacaoService.updateStatusAndDescricao(Status.RECUSADA, dto.getIdSolicitacao(), ex.getMessage(), true);
			throw new OperacaoCanceladaException(ex.getMessage());
		}

		Locacoes lo = new Locacoes().builder().withHoraLocacao(HoraDiasDataLocalService.horaLocal())
				.withDataLocacao(HoraDiasDataLocalService.dataLocal())
				.withDataPrevisaoTerminoLocacao(HoraDiasDataLocalService.dataLocacaoDevolucao())
				.withStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_ATIVA)
				.withQuantidadeDeRenovacao(ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_LOCACAO_INICIAL).withUsuario(cliente)
				.withLivro(l)
				.withObservacoesEntrega(
						(dto.getObservacoesEntregaLivro() != null && !dto.getObservacoesEntregaLivro().isEmpty())
								? dto.getObservacoesEntregaLivro()
								: ConstantsUtils.N_A)
				.build();

		try {
			triagemInicialLocacao.triagemReservaELocacao(lo, l, l.getId(), cliente);
		} catch (Exception ex) { // ex é validações de negocios
			solicitacaoService.updateStatusAndDescricao(Status.RECUSADA, dto.getIdSolicitacao(), ex.getMessage(), true);
			throw new OperacaoCanceladaException(ex.getMessage());
		}

		if (!isReservado(dto.getIdLivro(), dto.getIdClienteSolicitante())) {
			livroService.decrescentarEstoque(dto.getIdLivro(), 1);
		}

		solicitacaoService.updateStatus(Status.HOMOLOGADA, dto.getIdSolicitacao()); // Atualizando que a solicitação foi
																					// deferida

		locacaoRepository.save(lo);
		locacaoRepository.flush();

		log.info("Operação realizada com sucesso: ");

		return mapper.locacaoParaLocacaoVM(lo);

	}

	@Override
	public LocacaoVM atenderLocacao(AtendimentoLocacaoDTO dto) {
		Usuario funcionario = userService.findOneByUsuarioContexto();

		Usuario cliente = userService.findByIdCliente(dto.getIdClienteSolicitante());
		
		Livro l = livroService.findByIdLivro(dto.getIdLivro());

		Solicitacoes s = new Solicitacoes(); // historico de propostas

		s.setTipo(TipoSolicitacao.LOCACAO);
		s.setIdExemplar(dto.getIdLivro());
		s.setDataRetiradaExemplar(HoraDiasDataLocalService.dataLocal());
		s.setHoraRetiradaExemplar(HoraDiasDataLocalService.horaLocal());
		s.setUsuario(cliente);

		log.info("Usuario " + cliente.getEmail() + " Iniciando Processo de triagem para Locação do livro: "
				+ l.getNome());

		try {
			validadorCliente.validacaoFisicaEJuridica(cliente);
		} catch (Exception ex) { // ex é validações de negocios
			// Encerrando processo de locação por conter inconsistência no cadastro do
			// Cliente
			solicitacaoService.updateStatusAndDescricao(Status.RECUSADA, dto.getIdSolicitacao(), ex.getMessage(), true);

			throw new OperacaoCanceladaException("Triagem de Documentação. " + ex.getMessage());
		}

		try {
			validadorLivro.validaLivro(l);
		} catch (Exception ex) { // ex é validações de negocios

			solicitacaoService.updateStatusAndDescricao(Status.RECUSADA, dto.getIdSolicitacao(), ex.getMessage(), true);
			throw new OperacaoCanceladaException(ex.getMessage());
		}

		Locacoes lo = new Locacoes().builder().withHoraLocacao(HoraDiasDataLocalService.horaLocal())
				.withDataLocacao(HoraDiasDataLocalService.dataLocal())
				.withDataPrevisaoTerminoLocacao(HoraDiasDataLocalService.dataLocacaoDevolucao())
				.withStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_ATIVA)
				.withQuantidadeDeRenovacao(ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_LOCACAO_INICIAL).withUsuario(cliente)
				.withLivro(l)
				.withObservacoesEntrega(
						(dto.getObservacoesEntregaLivro() != null && !dto.getObservacoesEntregaLivro().isEmpty())
								? dto.getObservacoesEntregaLivro()
								: ConstantsUtils.N_A)
				.build();

		try {
			triagemInicialLocacao.triagemReservaELocacao(lo, l, l.getId(), cliente);
		} catch (Exception ex) { // ex é validações de negocios
			solicitacaoService.updateStatusAndDescricao(Status.RECUSADA, dto.getIdSolicitacao(), ex.getMessage(), true);
			throw new OperacaoCanceladaException(ex.getMessage());
		}

		if (!isReservado(dto.getIdLivro(), dto.getIdClienteSolicitante())) {
			livroService.decrescentarEstoque(dto.getIdLivro(), 1);
		}

		solicitacaoService.updateStatus(Status.HOMOLOGADA, dto.getIdSolicitacao()); // Atualizando que a solicitação foi
																					// deferida

		locacaoRepository.save(lo);
		locacaoRepository.flush();

		log.info("Operação realizada com sucesso: ");

		return mapper.locacaoParaLocacaoVM(lo);
	}



	@Transactional
	@Override
	public LocacaoCancelamentoVM cancelarLocacao(CancelamentoLocacaoDTO dto) {

		return Optional.of(findByIdLocacaoAtivaParaUsuarioContexto(dto.getIdLocacao(), dto.getIdClienteSolicitante()))
				.map((Function<? super Locacoes, ? extends LocacaoCancelamentoVM>) l -> {
					log.info("Iniciando Cancelamento da Locação: " + l);

					if (dataLimiteAtingidaOuUltrapassada(l)) {
						throw new CancelamentoOperacaoLocacaoInvalida(
								"Não é possivel cancelar uma locação com o Prazo de Vencimento Ultrapassado. Acesse a pagina de Encerramento para efetivar a finalização do Contrato");
					} else {
						l.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_CANCELADA);

						l.setHoraCancelamento(HoraDiasDataLocalService.horaLocal());
						l.setDataCancelamento(HoraDiasDataLocalService.dataLocal());

						livroService.acrescentarEstoque(l.getLivro().getId(),
								ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);
					}

					// saveAndFlush para user, livro e locacao...
					return mapper.locacaoParaLocacaoCanceladaVM(locacaoRepository.saveAndFlush(l));

				}).orElseThrow(() -> new CancelamentoOperacaoLocacaoInvalida(
						"Operação não realizada. Não foi possivel cancelar a Locação."));

	}

	@Transactional
	@Override
	public LocacaoDevolucaoVM encerramento(DevolucaoLocacaoDTO dto) {

		return Optional.of(findByIdLocacaoAtivaParaUsuarioContexto(dto.getIdLocacao(), dto.getIdClienteSolicitante()))
				.map((Function<? super Locacoes, ? extends LocacaoDevolucaoVM>) l -> {
					log.info("Iniciando Encerramento da Locação: " + l);

					if (dataLimiteAtingidaOuUltrapassada(l) || l.getStatus().equals(Status.ATRASADA)) {

						l.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_ATRASADA);

						Optional.of(l.getUsuario()).map((Function<? super Usuario, ? extends Usuario>)

				u -> { // user - cliente da locacão

					if (!u.isFuncionario()) {
						u.setStatusPessoa(ConstantsUtils.DEFAULT_VALUE_STATUS_USUARIO_INADIMPLENTE);
						u.setBloqueado(true);
						u.setMotivoBloqueio("Prazo de devolução do Livro Ultrapassado");
					}

					return userRepository.save(u);

				}).orElseThrow(() -> new CancelamentoOperacaoLocacaoInvalida(
						"Usuario não localizado. Error Interno do Servidor"));

					} else {
						l.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_CANCELADA);
					}
					l.setObservacoesDevolucao(dto.getObservacoesDevolucao());
					l.setHoraEncerramento(HoraDiasDataLocalService.horaLocal());
					l.setDataEncerramento(HoraDiasDataLocalService.dataLocal());

					livroService.acrescentarEstoque(l.getLivro().getId(), 1);

					// saveAndFlush para user, livro e locacao...
					return mapper.locacaoParaLocacaoDevolucaoVM(locacaoRepository.saveAndFlush(l));

				}).orElseThrow(() -> new EncerramentoOperacaoLocacaoException("Não foi possivel Encerrar  a Locação "));

	}

	@Transactional
	private boolean isReservado(Long idLivro, long idCliente) {
		boolean isReservado = false;

		if (reservaRepository.isAtivaToUserContextAndlivro(idLivro, idCliente) >= 1) {

			isReservado = true;

			reservaRepository.findOneGenericObjectAtivoToUserAndLivro(idLivro, idCliente)
					.map((Function<? super Reservas, ? extends Reservas>) r -> {

						// Finalizando Reserva para Iniciar Processo de Locação do Exemplar

						r.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSRESERVA_FINALIZADA);

						log.debug(r.toString() + " Finalizando para Iniciar Processo de locação");

						return reservaRepository.saveAndFlush(r); // flush
					}).orElse(null);

		} else {
			isReservado = false;
		}

		return isReservado;
	}

	@Transactional
	@Override
	public void renovarLocacao(Long id) {

		Locacoes locacaoSalva = findByIdLocacaoAtivaParaUsuarioContexto(id); // anonymous user online (web)

		log.info("Iniciando Processo de Renovação da Locação do livro:" + locacaoSalva.getLivro().getNome());

		validaDataLimiteLocacao(locacaoSalva);

		validaUsuarioPessoa.validadorUsuarioCliente(locacaoSalva.getUsuario());

		int quantidadeRenovada = locacaoSalva.getQuantidadeDeRenovacao();

		if (quantidadeRenovada >= ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_RENOVACAO_MAXIMA_LOCACAO) { // maximo
																										// renovação 3
			throw new QuantidadeRenovacaoLocacaoLimiteException(
					"Quantidade de Renovação maxima atingida. Operação não realizada");
		}

		locacaoSalva.setQuantidadeDeRenovacao(quantidadeRenovada + 1);

		locacaoSalva.setDataPrevisaoTermino(
				HoraDiasDataLocalService.dataRenovacaoLocacao(locacaoSalva.getDataPrevisaoTermino()));

		locacaoRepository.save(locacaoSalva);

		log.info("Processo de Renovação de Locação de livro realizada:");
	}

	@Transactional
	@Override
	public void updatePropertyStatusLocacao(Long idLocacao, Status statusLocacao) {

		Optional.of(findByIdLocacaoAtivaParaUsuarioContexto(idLocacao))
				.map((Function<? super Locacoes, ? extends Locacoes>) l -> {
					log.info("Iniciando Processo de atualização de Status da locação:");
					validaDataLimiteLocacao(l);
					l.setStatus(statusLocacao);
					return locacaoRepository.save(l);
				}).orElseThrow(() -> new LocacaoUpdateException("Não foi possivel atualizar o Status da Locação"));

	}

	private Locacoes findByIdLocacaoAtivaParaUsuarioContexto(Long id) {

		return locacaoRepository.findOneGenericObjectAtivoToUser(id)
				.orElseThrow(() -> new LocacaoInvalidaOuInexistenteException(
						"Locação não localizada ou Já Cancelada em sua Base de dados"));

	}

	private Locacoes findByIdLocacaoAtivaParaUsuarioContexto(Long id, long idUsuario) {

		return locacaoRepository.findOneGenericObjectAtivoToUser(id, idUsuario)
				.orElseThrow(() -> new LocacaoInvalidaOuInexistenteException(
						"Locação não localizada ou Já Cancelada em sua Base de dados"));

	}


	

}
