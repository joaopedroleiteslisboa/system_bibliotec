package com.system.bibliotec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.dto.UserSystemDTO;
import com.system.bibliotec.service.operations.IOperacaoUsuario;

@Service
@Transactional
public class UserService {

	@Autowired
	private IOperacaoUsuario operacaoUsuario;

	public Optional<Usuario> activateRegistration(String key) {

		return operacaoUsuario.activateRegistration(key);

	}

	public Optional<Usuario> completePasswordReset(String newPassword, String key) {

		return operacaoUsuario.completePasswordReset(newPassword, key);

	}

	public Optional<Usuario> requestPasswordReset(String mail) {
		return operacaoUsuario.requestPasswordReset(mail);

	}

	public Usuario registrarUsuarioAnonimo(UserAnonimoDTO userDTO, String password) {

		return operacaoUsuario.registrarUsuarioAnonimo(userDTO, password);

	}

	public Usuario registrarUsuarioSistema(UserSystemDTO userDTO, String password) {

		return operacaoUsuario.registrarUsuarioSistema(userDTO, password);

	}

	public Usuario criarUsuarioSistema(UserSystemDTO userDTO) {

		return operacaoUsuario.criarUsuarioSistema(userDTO);
	}

}
