package com.system.bibliotec.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.bibliotec.config.ConstantsUtilsEndPoint;
import com.system.bibliotec.controller.util.EndPointUtil;
import com.system.bibliotec.exception.EmailInvalidoException;
import com.system.bibliotec.exception.SenhaInvalidaException;
import com.system.bibliotec.exception.UsuarioNaoEncontrado;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.service.MailService;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.dto.KeyAndPasswordDTO;
import com.system.bibliotec.service.dto.ManagedUserDTO;
import com.system.bibliotec.service.dto.PasswordChangeDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ConstantsUtilsEndPoint.END_POINT_RELATIVO_CONTA)
public class AccountResource {

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EndPointUtil endPointUtil;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@GetMapping
	public ResponseEntity<?> getAccount() {
		return endPointUtil.returnObjectOrNotFound(usuarioService.obterUsuarioOnline());

	}
	 	
	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerAccount(@Valid @RequestBody ManagedUserDTO managedUserVM, HttpServletResponse response) {
		if (!checkPasswordLength(managedUserVM.getPassword())) {
			throw new SenhaInvalidaException("Senha Invalida. Informe uma senha valida");
		}		
		Usuario Usuario = usuarioService.registrarUsuarioAnonimo(managedUserVM);		
		mailService.sendActivationEmail(Usuario, criarPathRelativo(ConstantsUtilsEndPoint.END_POINT_RELATIVO_ATIVACAO_CONTA));
	}

	@GetMapping("/activate")
	public void activateAccount(@RequestParam(value = "key") String key) {
		usuarioService.activateRegistration(key);

	//Todo: verificar implementação de status de confirmaççao de ativação...
	}

	@GetMapping("/authenticate")
	public String isAuthenticated(HttpServletRequest request) {
		log.debug("REST request to check if the current Usuario is authenticated");
		return request.getRemoteUser();
	}

	//Todo: melhorar implementação para abranger todos os dados do usuario
	/*@PostMapping
	public void saveAccount(@Valid @RequestBody UserAnonimoDTO usuarioDTO) {
		String usuarioLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new AccountResourceException("Login não encontrado"));
		Optional<Usuario> existingUsuario = usuarioRepository.findOneByEmailIgnoreCase(usuarioDTO.getEmail());
		if (existingUsuario.isPresent() && (!existingUsuario.get().getEmail().equalsIgnoreCase(usuarioLogin))) {
			throw new EmailAlreadyUsedException("Error. Email já em uso no momento");
		}
			//Todo: melhorar implementação para abranger todos os dados do usuario
		usuarioService.updateUserAnonimo(usuarioDTO);
	}*/
	
	@PostMapping(path = "/change-password")
	public void changePassword(@RequestBody  PasswordChangeDTO passwordChangeDto) {
		if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
			throw new SenhaInvalidaException("Senha informada Invalida");
		}
		usuarioService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
	}

	@PostMapping(path = "/reset-password/init")
	public void requestPasswordReset(@Valid @RequestBody String mail, HttpServletRequest req) {
		mailService.sendPasswordResetMail(usuarioService.requestPasswordReset(mail)
				.orElseThrow(() -> new EmailInvalidoException("Login invalido ou inexistente")));
	}

	@PostMapping(path = "/reset-password/finish")
	public void finishPasswordReset(@RequestBody KeyAndPasswordDTO keyAndPassword) {
		if (!checkPasswordLength(keyAndPassword.getNewPassword()) && !checkPasswordLength(keyAndPassword.getConfirmPassword())) {
			throw new SenhaInvalidaException("Senha invalida. Operação não realizada");
		}
		Optional<Usuario> Usuario = usuarioService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getConfirmPassword(),
				keyAndPassword.getKey());

		if (!Usuario.isPresent()) {
			throw new UsuarioNaoEncontrado("Nenhum usuario encontrado para este chave de redefinição");
		}
	}

	private static boolean checkPasswordLength(String password) {
		return !StringUtils.isEmpty(password) && password.length() >= ManagedUserDTO.PASSWORD_MIN_LENGTH
				&& password.length() <= ManagedUserDTO.PASSWORD_MAX_LENGTH;
	}
	
	private String criarPathRelativo(String pathAcao) {		
		return ServletUriComponentsBuilder.fromCurrentRequest().replacePath(pathAcao).toUriString();
	}

}
