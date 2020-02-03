package com.system.bibliotec.service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.system.bibliotec.exception.EmailAlreadyUsedException;
import com.system.bibliotec.exception.UsernameAlreadyUsedException;
import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.TipoUsuarioVO;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.PermissoesRepositorio;
import com.system.bibliotec.repository.TipoUsuarioVORepository;
import com.system.bibliotec.repository.UsuarioRepository;

import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.dto.UserSystemDTO;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.ultis.RandomUtils;



@Service
@Transactional
public class UserService {

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
	private PermissoesRepositorio permissaoRepositorio;
	
	@Autowired
	private TipoUsuarioVORepository tipoUsuarioVORepository;
	
	

	
	public Optional<Usuario> activateRegistration(String key) {
		log.debug("Ativação do usuario por chave de ativação key {}", key);
		return userRepository.findOneByChaveAtivacao(key).map(user -> {
			// activate given user for the registration key.
			user.setAtivo(DEFAULT_ATIVO);
			user.setChaveAtivacao(DEFAULT_CHAVE_ATIVACAO);
			log.debug("Usuario  user: {} ativado com sucesso", user);
			return user;
		});
	}

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

	public Optional<Usuario> requestPasswordReset(String mail) {
		return userRepository.findOneByEmailIgnoreCase(mail).filter(Usuario::isAtivo).map(user -> {
			user.setChaveRenovacao(RandomUtils.generateResetKey());
			user.setResetDate(Instant.now());
			return user;
		});
	}

	public Usuario registerUser(UserAnonimoDTO userDTO, String password) {
			
		
		userRepository.findOneByLogin(userDTO.getEmail().toLowerCase()).ifPresent(existingUser -> {

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

		Usuario novoUsuario = new Usuario();
		
		String senhaCriptografada = passwordEncoder.encode(password);

		// new user gets initially a generated password
		novoUsuario.setSenha(senhaCriptografada);
		novoUsuario.setNome(userDTO.getNome());
		
		if(userDTO.getTipoUsuario() == null) {
			tipoUsuarioVORepository.findOneByTipoIgnoreCase(DEFAULT_TIPO_USUARIO).ifPresent(novoUsuario::setTipo);
		}else {
			tipoUsuarioVORepository.
					findOneByTipoIgnoreCase(userDTO.getTipoUsuario().toUpperCase()).ifPresent(novoUsuario::setTipo);
		}

		if (userDTO.getEmail() != null && new EmailValidator().isValid(userDTO.getEmail(), null)) {
			novoUsuario.setEmail(userDTO.getEmail().toLowerCase());
		}
		novoUsuario.setImageUrl(userDTO.getImageUrl());
		if(userDTO.getLangKey() == null) {
			novoUsuario.setLangKey(DEFAULT_LANGUAGE);
		}else {
			novoUsuario.setLangKey(userDTO.getLangKey());
		}
		

		novoUsuario.setAtivo(DEFAULT_CRIACAO_USUARIO_INATIVO);


		novoUsuario.setChaveAtivacao(RandomUtils.generateActivationKey());

		Set<Permissao> permissoes = new HashSet<>();

		permissaoRepositorio.findById(AuthoritiesConstantsUltis.ROLE_USER_ANONIMO).ifPresent(permissoes::add);
		
		
		novoUsuario.setPermissoes(permissoes);
		userRepository.save(novoUsuario);

		log.debug("Criando registro de dados de novo usuario novoUsuario: {}", novoUsuario);
		return novoUsuario;
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
	
	
	 public Usuario createUser(UserAnonimoDTO userDTO) {
		 	Usuario user = new Usuario();
	        user.setEmail(userDTO.getEmail().toLowerCase());
	        user.setNome(userDTO.getNome().toLowerCase());
	        
	    	if(userDTO.getTipoUsuario() == null) {
				tipoUsuarioVORepository.findOneByTipoIgnoreCase(DEFAULT_TIPO_USUARIO).ifPresent(user::setTipo);
			}else {
				tipoUsuarioVORepository.
						findOneByTipoIgnoreCase(userDTO.getTipoUsuario().toUpperCase()).ifPresent(user::setTipo);
			}
	        
	        if (userDTO.getEmail() != null && new EmailValidator().isValid(userDTO.getEmail(), null)) {
	            user.setEmail(userDTO.getEmail().toLowerCase());
	        }
	        user.setImageUrl(userDTO.getImageUrl());
	        if (userDTO.getLangKey() == null) {
	            user.setLangKey(DEFAULT_LANGUAGE); // default language
	        } else {
	            user.setLangKey(userDTO.getLangKey());
	        }
	        String encryptedPassword = passwordEncoder.encode(RandomUtils.generatePassword());
	        user.setSenha(encryptedPassword);
	        user.setChaveRenovacao(RandomUtils.generateResetKey());
	        user.setResetDate(Instant.now());
	        user.setAtivo(DEFAULT_ATIVO);	        
	        if (userDTO.getPermissoes() != null) {
	            Set<Permissao> permissoes = userDTO.getPermissoes().stream()
	                .map(permissaoRepositorio::findById)
	                .filter(Optional::isPresent)
	                .map(Optional::get)
	                .collect(Collectors.toSet());
	            user.setPermissoes(permissoes);
	            //DEFAULT SYSTEM - USER CREATE
	            permissaoRepositorio.findById(AuthoritiesConstantsUltis.ROLE_USER_ANONIMO).ifPresent(permissoes::add);
	            
	        }
	        userRepository.save(user);
	        
	        log.debug("Criando Novo Usuario user: {}", user);
	        return user;
	    }
	 
	 public Usuario createUserSystem(UserSystemDTO userDTO) {
		 	Usuario user = new Usuario();
	        user.setEmail(userDTO.getEmail().toLowerCase());
	        user.setNome(userDTO.getNome().toLowerCase());
	        
	        if(userDTO.getTipoUsuario() == null) {
				tipoUsuarioVORepository.findOneByTipoIgnoreCase(DEFAULT_TIPO_USUARIO).ifPresent(user::setTipo);
			}else {
				tipoUsuarioVORepository.
						findOneByTipoIgnoreCase(userDTO.getTipoUsuario().toUpperCase()).ifPresent(user::setTipo);
			}
	        
	        
	        if (userDTO.getEmail() != null && new EmailValidator().isValid(userDTO.getEmail(), null)) {
	            user.setEmail(userDTO.getEmail().toLowerCase());
	        }
	        user.setImageUrl(userDTO.getImageUrl());
	        if (userDTO.getLangKey() == null) {
	            user.setLangKey(DEFAULT_LANGUAGE); // default language
	        } else {
	            user.setLangKey(userDTO.getLangKey());
	        }
	        String encryptedPassword = passwordEncoder.encode(RandomUtils.generatePassword());
	        user.setSenha(encryptedPassword);
	        user.setChaveRenovacao(RandomUtils.generateResetKey());
	        user.setResetDate(Instant.now());
	        user.setAtivo(DEFAULT_ATIVO);	        
	        if (userDTO.getPermissoes() != null) {
	            Set<Permissao> permissoes = userDTO.getPermissoes().stream()
	                .map(permissaoRepositorio::findById)
	                .filter(Optional::isPresent)
	                .map(Optional::get)
	                .collect(Collectors.toSet());
	            user.setPermissoes(permissoes);
	            //DEFAULT SYSTEM - USER CREATE
	            permissaoRepositorio.findById(AuthoritiesConstantsUltis.ROLE_USER_ANONIMO).ifPresent(permissoes::add);
	            
	        }
	        userRepository.save(user);
	        
	        log.debug("Criando Novo Usuario de sistema user: {}", user);
	        return user;
	    }
	 
	 
	 
	 

}
