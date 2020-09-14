package com.system.bibliotec.service.operacoes;

import java.util.Optional;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.CancelamentoLocacaoException;
import com.system.bibliotec.exception.CancelamentoOperacaoLocacaoInvalida;
import com.system.bibliotec.exception.LocacaoInvalidaOuInexistenteException;
import com.system.bibliotec.exception.LocacaoUpdateException;
import com.system.bibliotec.exception.QuantidadeRenovacaoLocacaoLimiteException;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.service.LivroService;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.dto.CancelamentoLocacaoDTO;
import com.system.bibliotec.service.dto.DevolucaoLocacaoDTO;
import com.system.bibliotec.service.dto.LocacaoDTO;
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
	
	
	@Autowired
	public OperacaoLocacaoImpl(IvalidaUsuarioTriagemInicial validaUsuarioPessoa,
			ITriagemReservaELocacao<Locacoes, Livro, Long, Usuario> triagemInicialLocacao, LivroService livroService,
			LivroRepository livroRepository, IValidaLivro validadorLivro, LocacaoRepository locacaoRepository,
			UserService userService, UsuarioRepository userRepository, IValidaPessoa validadorCliente,
			MapeadorLocacao mapper, ReservaRepository reservaRepository) {
		
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
	}


	@Transactional
	@Override
	public LocacaoVM realizarLocacao(LocacaoDTO dto) {

		Usuario funcionario = userService.findOneByUsuarioContexto();

		Usuario cliente = userService.findByIdCliente(dto.getIdUsuarioSolicitante());

		validadorCliente.validacaoFisicaEJuridica(u);

		Livro l = livroService.findByIdLivro(dto.getIdLivro());

		log.info("Usuario " + u.getEmail() + " Iniciando Processo de Locação do livro: " + l.getNome());

		validadorLivro.validaLivro(l);

		Locacoes lo = new Locacoes().builder().withHoraLocacao(HoraDiasDataLocalService.horaLocal())
				.withDataLocacao(HoraDiasDataLocalService.dataLocal())
				.withDataPrevisaoTerminoLocacao(HoraDiasDataLocalService.dataLocacaoDevolucao())
				.withStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_ATIVA)
				.withQuantidadeDeRenovacao(ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_LOCACAO_INICIAL).withUsuario(u)
				.withLivro(l)
				.withObservacoesEntrega(
						(dto.getObservacoesEntregaLivro() != null && !dto.getObservacoesEntregaLivro().isEmpty())
								? dto.getObservacoesEntregaLivro()
								: ConstantsUtils.N_A)
				.build();

		triagemInicialLocacao.triagemReservaELocacao(lo, l, l.getId(), u);

		if (!isReservado(dto.getIdLivro())) {
			livroService.decrescentarEstoque(dto.getIdLivro(),
					ConstantsUtils.DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO);
		}

		locacaoRepository.save(lo);
		locacaoRepository.flush();

		log.info("Operação realizada com sucesso: " + lo.toString());

		return mapper.locacaoParaLocacaoVM(lo);

	}

	
	@Transactional
	@Override
	public LocacaoCancelamentoVM cancelarLocacao(CancelamentoLocacaoDTO dto) {

		return Optional.of(findByIdLocacaoAtivaParaUsuarioContexto(dto.getIdLocacao()))
		.map((Function<? super Locacoes, ? extends LocacaoCancelamentoVM>) l -> {
			log.info("Iniciando Cancelamento da Locação: " + l);

				if (dataLimiteAtingidaOuUltrapassada(l)) {
						throw new CancelamentoOperacaoLocacaoInvalida("Não é possivel cancelar uma locação com o Prazo de Vencimento Ultrapassado. Acesse a pagina de Encerramento para efetivar a finalização do Contrato");
				} else {
					l.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_CANCELADA);
					
					l.setHoraCancelamento(HoraDiasDataLocalService.horaLocal());
					l.setDataCancelamento(HoraDiasDataLocalService.dataLocal());

					livroService.acrescentarEstoque(l.getLivro().getId(),
							ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);
				}
				
			
			//saveAndFlush para user, livro e locacao...
			return mapper.locacaoParaLocacaoCanceladaVM(locacaoRepository.saveAndFlush(l));

		}).orElseThrow(() -> new CancelamentoOperacaoLocacaoInvalida("Não foi possivel cancelar a Locação "
				+ dto.getIdLocacao() + "Usuario " + obterUsuarioDoContextoPeloToken()));
			
}
	

	@Transactional
	@Override
	public LocacaoDevolucaoVM encerramento(DevolucaoLocacaoDTO dto) {

		return Optional.of(findByIdLocacaoAtivaParaUsuarioContexto(dto.getIdLocacao()))
			.map((Function<? super Locacoes, ? extends LocacaoDevolucaoVM>) l -> {
					log.info("Iniciando Encerramento da Locação: " + l);

						if (dataLimiteAtingidaOuUltrapassada(l) || l.getStatus().equals(Status.ATRASADA)) {
	
							l.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_ATRASADA);
	
							Optional.of(l.getUsuario()).map((Function<? super Usuario, ? extends Usuario>)
							
								u -> {	
									
									if(!u.isFuncionario()) {
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

					livroService.acrescentarEstoque(l.getLivro().getId(),
							ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);
					//saveAndFlush para user, livro e locacao...
					return mapper.locacaoParaLocacaoDevolucaoVM(locacaoRepository.saveAndFlush(l));

			}).orElseThrow(() -> new CancelamentoLocacaoException("Não foi possivel cancelar a Locação "
						+ dto.getIdLocacao() + " do Usuario " + obterUsuarioDoContextoPeloToken()));

	}

	@Transactional
	private boolean isReservado(Long idLivro) {
		boolean isReservado = false;

		if (reservaRepository.isAtivaToUserContextAndlivro(idLivro) >= 1) {
			isReservado = true;

			reservaRepository.findOneGenericObjectAtivoToUserAndLivro(idLivro)
					.map((Function<? super Reservas, ? extends Reservas>) r -> {
						r.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSRESERVA_FINALIZADA);

						log.debug(r.toString() + " Finalizando para Iniciar Processo de locação");

						return reservaRepository.save(r);
					}).orElse(null);

		} else {
			isReservado = false;
		}

		return isReservado;
	}

	@Transactional
	@Override
	public void renovarLocacao(Long id) {

		Locacoes locacaoSalva = findByIdLocacaoAtivaParaUsuarioContexto(id);

		log.info("Iniciando Processo de Renovação de Locação de livro:" + locacaoSalva);

		validaDataLimiteLocacao(locacaoSalva);

		validaUsuarioPessoa.validadorUsuarioCliente(locacaoSalva.getUsuario());

		int quantidadeRenovada = locacaoSalva.getQuantidadeDeRenovacao();

		if (quantidadeRenovada >= ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_RENOVACAO_MAXIMA_LOCACAO) {
			throw new QuantidadeRenovacaoLocacaoLimiteException(
					"Quantidade de Renovação maxima atingida. Operação não realizada");
		}

		locacaoSalva.setQuantidadeDeRenovacao(quantidadeRenovada + ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_LOCACAO);

		locacaoSalva.setDataPrevisaoTermino(
				HoraDiasDataLocalService.dataRenovacaoLocacao(locacaoSalva.getDataPrevisaoTermino()));

		locacaoRepository.save(locacaoSalva);

		log.info("Processo de Renovação de Locação de livro realizada:" + locacaoSalva);
	}
	

	@Transactional
	@Override
	public void updatePropertyStatusLocacao(Long idLocacao, Status statusLocacao) {

		Optional.of(findByIdLocacaoAtivaParaUsuarioContexto(idLocacao))
				.map((Function<? super Locacoes, ? extends Locacoes>) l -> {
					log.info("Iniciando Processo de atualização de Status da locação:" + l);
					validaDataLimiteLocacao(l);
					l.setStatus(statusLocacao);
					return locacaoRepository.save(l);
				}).orElseThrow(
						() -> new LocacaoUpdateException("Não foi possivel atualizar o Status da Locação" + idLocacao));

	}

	
	public Locacoes findByIdLocacaoAtivaParaUsuarioContexto(Long id) {

		return locacaoRepository.findOneGenericObjectAtivoToUser(id)
				.orElseThrow(() -> new LocacaoInvalidaOuInexistenteException(
						"Locação não localizada ou Já Cancelada em sua Base de dados"));

	}

	

}
