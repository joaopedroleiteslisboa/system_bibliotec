package com.system.bibliotec.service.operacoes;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.system.bibliotec.service.validation.IvalidaUsuarioTriagemInicial;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.exception.AccountResourceException;
import com.system.bibliotec.exception.CodigoAtivaCaoInvalidoOuInexistenteException;
import com.system.bibliotec.exception.EmailAlreadyUsedException;
import com.system.bibliotec.exception.ErrorInternoException;
import com.system.bibliotec.exception.SenhaInvalidaException;
import com.system.bibliotec.exception.UsernameAlreadyUsedException;
import com.system.bibliotec.exception.UsuarioNaoEncontrado;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.dto.ManagedUserDTO;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.mapper.MapeadorEcriadorUsuario;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.ultis.RandomUtils;

@Component
public class OperacaoUsuarioImpl implements IOperacaoUsuario {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	private static final String DEFAULT_LANGUAGE = "pt-br";

	private static Instant DEFAULT_INSTANTE_TIME = Instant.now().minusSeconds(86400);

	private static final String DEFAULT_TIPO_USUARIO = "ANONIMO";

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MapeadorEcriadorUsuario userMapperServiceAdpter;

	@Autowired
	private IvalidaUsuarioTriagemInicial triagemInicialValidaCaoUsuario;

	@Override
	public Usuario activateRegistration(String key) {
		log.debug("Ativação do usuario por chave de ativação key {}", key);

		return userRepository.findOneByChaveAtivacao(key).map(user -> {

			user.setAtivo(true);
			user.setChaveAtivacao(null);
			log.debug("Usuario  user: {} ativado com sucesso", user);

			userRepository.save(user);

			return user;
		}).orElseThrow(() -> new CodigoAtivaCaoInvalidoOuInexistenteException("Codigo de Ativacao Invalido"));
	}

	@Override
	public Optional<Usuario> completePasswordReset(String newPassword, String confirmPassword, String key) {
		log.debug("Resete de Senha {} do usuario ", key);
		if (!newPassword.equalsIgnoreCase(confirmPassword)) {
			throw new IllegalArgumentException("Senha informada nao confirmada");
		}
		return userRepository.findOneByChaveRenovacao(key)
				.filter(user -> user.getResetDate().isAfter(DEFAULT_INSTANTE_TIME)).map(user -> {
					user.setSenha(passwordEncoder.encode(newPassword));
					user.setChaveRenovacao(null);
					user.setResetDate(null);
					userRepository.save(user);
					return user;
				});
	}

	@Override
	public Optional<Usuario> requestPasswordReset(String mail) {
		return userRepository.findOneByEmailIgnoreCase(mail).filter(Usuario::isAtivo).map(user -> {
			user.setChaveRenovacao(RandomUtils.generateResetKey());
			user.setResetDate(Instant.now());
			userRepository.save(user);
			return user;
		});
	}

	@Override
	@Transactional
	public Usuario registrarUsuarioAnonimo(ManagedUserDTO userDTO) {

		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail().toLowerCase()).ifPresent(existingUser -> {

			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new EmailAlreadyUsedException("Email já em uso");
			}
		});

		if (userDTO.getUserName() != null && !userDTO.getUserName().isEmpty()) {

			userRepository.findOneByUserName(userDTO.getUserName()).ifPresent(existingUser -> {

				boolean removed = removeNonActivatedUser(existingUser);
				if (!removed) {
					throw new UsernameAlreadyUsedException("Username já em uso no momento");
				}
			});

		}

		Usuario u = userMapperServiceAdpter.criadorUsuarioAnonimoPorDTO(userDTO);

		if (u == null) {
			throw new AccountResourceException("Não foi possivel cadastrar o Usuario por falta de informações");
		} else {
			triagemInicialValidaCaoUsuario.validadorUsuarioCliente(u);
			u = userRepository.save(u);
		}

		return u;

	}

	@Override
	public Usuario registrarUsuarioSistema(Object userDTO) {

		/*
		 * userRepository.findOneByEmailIgnoreCase(userDTO.getEmail().toLowerCase()).
		 * ifPresent(existingUser -> {
		 * 
		 * boolean removed = removeNonActivatedUser(existingUser); if (!removed) { throw
		 * new UsernameAlreadyUsedException(); } });
		 * userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(
		 * existingUser -> { boolean removed = removeNonActivatedUser(existingUser); if
		 * (!removed) { throw new EmailAlreadyUsedException(); } });
		 */
		// return userMapperServiceAdpter.criadorUsuarioSistemaPorDTO(userDTO);

		return null;

	}

	@Override
	@Transactional
	public void updateUserAnonimo(UserAnonimoDTO usuarioDTO) {

		// Todo: melhorar implementação para abranger todos os dados do usuario
		obterUsuarioDoContextoPeloTokenOptional().flatMap(userRepository::findOneByEmailIgnoreCase).ifPresent(user -> {
			user.setNome(usuarioDTO.getNome());

			if (usuarioDTO.getEmail() != null) {
				user.setEmail(usuarioDTO.getEmail().toLowerCase());

			}
			if (usuarioDTO.getNumero() != null) {

			}
			user.setLangKey(usuarioDTO.getLangKey());
			user.setImageUrl(usuarioDTO.getImageUrl());

			log.debug("Atualizando informações do user: {}", user);
		});
	}

	@Override
	public Usuario criarUsuarioSistema(Object userDTO) {
		/*
		 * Usuario user = userMapperServiceAdpter.criadorUsuarioSistemaPorDTO(userDTO);
		 * userRepository.save(user); return user; }
		 * 
		 * private boolean removeNonActivatedUser(Usuario usuarioExistente) { boolean
		 * condicao = false;
		 * 
		 * if (usuarioExistente.isAtivo()) { return condicao; } if
		 * (HoraDiasDataLocalService.processoAtivacaoUsuarioEAtivo(usuarioExistente.
		 * getCreatedDate())) { userRepository.delete(usuarioExistente);
		 * userRepository.flush(); condicao = true; } return condicao;
		 */

		return null;

	}

	@Override
	@Transactional
	public void changePassword(String passwordOld, String newPassword) {
		obterUsuarioDoContextoPeloTokenOptional().flatMap(userRepository::findOneByEmailIgnoreCase).ifPresent(user -> {
			String currentEncryptedPassword = user.getSenha();
			if (!passwordEncoder.matches(passwordOld, currentEncryptedPassword)) {
				throw new SenhaInvalidaException("Senha ja em uso. Informe outra senha");
			}
			String encryptedPassword = passwordEncoder.encode(newPassword);
			user.setSenha(encryptedPassword);

			userRepository.save(user);

			log.debug("Atualização de senha para User: {}", user);
		});
	}

	@Override
	public Object obterUsuarioCorrente() {

		Usuario u = null;

		if (new EmailValidator().isValid(obterUsuarioDoContextoPeloToken(), null)) {

			u = userRepository.findOneByEmailIgnoreCase(obterUsuarioDoContextoPeloToken())
					.orElseThrow(() -> new AccountResourceException("Usuario não encontrado em Nossa Base de dados"));

			if (u.isFuncionario()) {

				return userMapperServiceAdpter.userToUserSystemVM(u);
			} else {
				triagemInicialValidaCaoUsuario.validadorUsuarioCliente(u);
				return userMapperServiceAdpter.userToUserAnonimoVM(u);

			}

		}

		return null;

	}

	@Override
	public Usuario findOneByUsuarioContexto() {
		// TODO Auto-generated method stub

		return userRepository.findOneByEmailIgnoreCase(obterUsuarioDoContextoPeloToken())
				.orElseThrow(() -> new AccountResourceException("Usuario não encontrado na base de dados do Sistema"));

	}

	private boolean removeNonActivatedUser(Usuario usuarioExistente) {
		boolean condicao = false;
		if (usuarioExistente.isAtivo()) {
			return condicao;
		}
		if (HoraDiasDataLocalService.processoAtivacaoUsuarioEAtivo(usuarioExistente.getCreatedDate())) {
			userRepository.delete(usuarioExistente);
			userRepository.flush();
			condicao = true;
		}
		return condicao;

	}

	@Override
	@Transactional
	public void bloqueadorDeAcesso(boolean bool, String motivo, String username) {

		if (motivo == null || username == null) {
			throw new ErrorInternoException("Error interno na operação de Bloqueio de Conta do username: " + username
					+ "Campos obrigatorios não informado para esta operação");
		} else {
			log.info("Inciando processo de Bloqueio do username: " + username + " pelo motivo: " + motivo);
			userRepository.findOneByEmailIgnoreCase(username).ifPresent(u -> {

				u.setBloqueado(bool);
				u.setMotivoBloqueio(motivo);
				userRepository.save(u);
				userRepository.flush();

				log.info("O " + username + " foi bloqueado pelo motivo: " + motivo);
			});
		}

	}

	@Override
	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers() {
		userRepository.findAllByAtivoIsFalseAndChaveAtivacaoIsNotNullAndCreatedDateBefore(
				Instant.now().minus(3, ChronoUnit.DAYS)).forEach(user -> {
					log.debug("Iniciando processo de exclusão de usuarios não ativados {}", user.getEmail());
					userRepository.delete(user);
					// this.clearUserCaches(user);
				});
	}

	@Override
	public Usuario findById(Long id) {

		return userRepository.findById(id)
		.orElseThrow(() -> new AccountResourceException("Solicitação invalida. Usuario não encontrado na base de dados do Sistema"));

		
	}



	
	public Usuario findOneByEmailOrUsername(String emailOrUsername) {

		return userRepository.findOneByEmail(emailOrUsername)
		.orElseGet(
			userRepository.findOneByUserName(emailOrUsername).orElseThrow(
				() -> UsuarioNaoEncontrado("Usuario não encontrado em nosso Banco de dados")));

		
	}

}
