package com.system.bibliotec.service.operations;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.EmailAlreadyUsedException;
import com.system.bibliotec.exception.UsernameAlreadyUsedException;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.dto.UserSystemDTO;
import com.system.bibliotec.service.mapper.UserMapperServiceAdpter;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.ultis.RandomUtils;

@Component
public class OperacaoUsuarioImpl implements IOperacaoUsuario {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	private static String DEFAULT_CHAVE_ATIVACAO = null;

	private static final String DEFAULT_LANGUAGE = "pt-br";

	private static Instant DEFAULT_INSTANTE_TIME = Instant.now().minusSeconds(86400);

	private static final String DEFAULT_TIPO_USUARIO = "ANONIMO";

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapperServiceAdpter userMapperServiceAdpter;

	@Override
	public Optional<Usuario> activateRegistration(String key) {
		log.debug("Ativação do usuario por chave de ativação key {}", key);
		return userRepository.findOneByChaveAtivacao(key).map(user -> {
			// activate given user for the registration key.
			user.setAtivo(true);
			user.setChaveAtivacao(DEFAULT_CHAVE_ATIVACAO);
			log.debug("Usuario  user: {} ativado com sucesso", user);
			return user;
		});
	}

	@Override
	public Optional<Usuario> completePasswordReset(String newPassword, String key) {
		log.debug("Resete de Senha key {} do usuario ", key);
		return userRepository.findOneByChaveRenovacao(key)
				.filter(user -> user.getResetDate().isAfter(DEFAULT_INSTANTE_TIME)).map(user -> {
					user.setSenha(passwordEncoder.encode(newPassword));
					user.setChaveRenovacao(null);
					user.setResetDate(null);
					return user;
				});
	}

	@Override
	public Optional<Usuario> requestPasswordReset(String mail) {
		return userRepository.findOneByEmailIgnoreCase(mail).filter(Usuario::isAtivo).map(user -> {
			user.setChaveRenovacao(RandomUtils.generateResetKey());
			user.setResetDate(Instant.now());
			return user;
		});
	}

	@Override
	public Usuario registrarUsuarioAnonimo(UserAnonimoDTO userDTO, String password) {

		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail().toLowerCase()).ifPresent(existingUser -> {

			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new UsernameAlreadyUsedException();
			}
		});
		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new EmailAlreadyUsedException();
			}
		});

		return userMapperServiceAdpter.criadorUsuarioAnonimoPorDTO(userDTO);

	}

	@Override
	public Usuario registrarUsuarioSistema(UserSystemDTO userDTO, String password) {

		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail().toLowerCase()).ifPresent(existingUser -> {

			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new UsernameAlreadyUsedException();
			}
		});
		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new EmailAlreadyUsedException();
			}
		});

		return userMapperServiceAdpter.criadorUsuarioSistemaPorDTO(userDTO);

	}

	@Override
	public Usuario criarUsuarioSistema(UserSystemDTO userDTO) {
		Usuario user = userMapperServiceAdpter.criadorUsuarioSistemaPorDTO(userDTO);
		userRepository.save(user);
		return user;
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

}
