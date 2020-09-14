package com.system.bibliotec.service.operacoes;

import java.util.List;

import com.system.bibliotec.service.mapper.MapeadorReserva;

import com.system.bibliotec.service.validation.ITriagemReservaELocacao;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.ReservaInexistenteException;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.repository.SolicitacaoRepository;
import com.system.bibliotec.repository.TipoUsuarioVORepository;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.security.SecurityUtils;
import com.system.bibliotec.service.LivroService;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.dto.ReservaDTO;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;

import com.system.bibliotec.service.validation.IValidaLivro;
import com.system.bibliotec.service.validation.IValidaPessoa;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OperacaoReservaImpl implements IOperacaoReserva {

	private static final String USUARIO_NAO_LOCALIZADO = "Usuario Não Localizado. Error Interno do servidor";


	private final ReservaRepository repository;


	private final LivroService livroService;


	private final IValidaLivro validadorLivro;


	private final IValidaPessoa validadorCliente;

	private final UserService userService;


	private final TipoUsuarioVORepository tipoUsuarioVORepository;


	private final MapeadorReserva mapper;

	private final SolicitacaoRepository solicitacaoRepository;


	private final ITriagemReservaELocacao<Reservas, Livro, Long, Usuario> triagemInicialReserva;

	
	
	@Autowired
	public OperacaoReservaImpl(ReservaRepository repository, LivroService livroService, IValidaLivro validadorLivro,
			IValidaPessoa validadorCliente, UserService userService, TipoUsuarioVORepository tipoUsuarioVORepository,
			MapeadorReserva mapper, ITriagemReservaELocacao<Reservas, Livro, Long, Usuario> triagemInicialReserva,
			SolicitacaoRepository solicitacaoRepository ) {
		
		this.repository = repository;
		this.livroService = livroService;
		this.validadorLivro = validadorLivro;
		this.validadorCliente = validadorCliente;
		this.userService = userService;
		this.tipoUsuarioVORepository = tipoUsuarioVORepository;
		this.mapper = mapper;
		this.triagemInicialReserva = triagemInicialReserva;
		this.solicitacaoRepository = solicitacaoRepository;
	}

	@Transactional
	@Override
	public ReservaVM reservaLivro(ReservaDTO reservaDTO) {

		

		log.info("Iniciando Processo de reserva de livro: Usuario");

		Reservas r = new Reservas();

		Usuario u = null;
		
		if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_ADMIN) || 
				SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_USER_SYSTEM)){
				
					u = userService.findByIdCliente(reservaDTO.getIdUsuario())

		}

		validadorCliente.validacaoFisicaEJuridica(u);

		Livro l = livroService.findByIdLivro(reservaDTO.getIdLivro());

		triagemInicialReserva.triagemReservaELocacao(r, l, l.getId(), u);

		validadorLivro.validaLivro(l);

		r.setLivro(l);

		r.setUsuario(u);

		r.setHoraReserva(HoraDiasDataLocalService.horaLocal());

		r.setDataReserva(HoraDiasDataLocalService.dataLocal());

		r.setDataPrevisaoTermino(HoraDiasDataLocalService.dataReservaLimite());

		r.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSRESERVA_ATIVA);

		livroService.decrescentarEstoque(reservaDTO.getIdLivro(),
				ConstantsUtils.DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO);

		log.info("Livro Reservado:" + reservaDTO.getIdLivro());

		Reservas reservaRealizada = repository.save(r);

		repository.flush();

		return mapper.reservaParaReservaVM(reservaRealizada);
	}

	@Override
	@Transactional
	public ReservaCanceladaVM cancelarReserva(Long idReserva) {

		Reservas r = findByIdReservaAtivaParaUsuario(idReserva);

		r.setStatus(ConstantsUtils.DEFAULT_VALUE_STATUSRESERVA_CANCELADA);

		livroService.acrescentarEstoque(r.getLivro().getId(),
				ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);

		repository.save(r);
		repository.flush();

		return mapper.reservaParaReservaCanceladaVM(r);

	}

	@Override
	public List<ReservaVM> findAllByReservaDoUsuario(Pageable pageable) {

		return mapper.reservaParaReservaVM(repository.findAllGenericObjectToUser(pageable).orElseThrow(
				() -> new ReservaInexistenteException("Não foi localizada nenhum registro em nossa Base de dados")));

	}

	@Override
	public ReservaVM findByIdReserva(Long id) {

		return repository.findOneGenericObjectToUser(id).map(mapper::reservaParaReservaVM).orElseThrow(
				() -> new ReservaInexistenteException("Reserva não localizada em nossos registros"));

	}

	public Reservas findByIdReservaAtivaParaUsuario(Long id) {

		return repository.findOneGenericObjectAtivoToUser(id).orElseThrow(
				() -> new ReservaInexistenteException("Reserva não localizada ou Já Cancelada em sua Base de dados"));

	}

	public boolean existsByIdReserva(Long idReserva) {

		if (!repository.existsById(idReserva)) {

			return false;
		}
		return true;

	}

}
