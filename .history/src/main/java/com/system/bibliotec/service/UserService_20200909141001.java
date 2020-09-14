package com.system.bibliotec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.service.dto.ManagedUserDTO;
import com.system.bibliotec.service.dto.UserAnonimoDTO;

import com.system.bibliotec.service.operacoes.IOperacaoUsuario;


@Service
public class UserService {

	@Autowired
	private IOperacaoUsuario operacaoUsuario;

	public Usuario activateRegistration(String key) {

		return operacaoUsuario.activateRegistration(key);

	}

	public Optional<Usuario> completePasswordReset(String newPassword, String confirmPassword, String key) {

		return operacaoUsuario.completePasswordReset(newPassword, confirmPassword, key);

	}

	public Optional<Usuario> requestPasswordReset(String mail) {
		return operacaoUsuario.requestPasswordReset(mail);

	}

	public Usuario registrarUsuarioAnonimo(ManagedUserDTO userDTO) {

		return operacaoUsuario.registrarUsuarioAnonimo(userDTO);

	}
	//Todo: Mudar implementação para vincular a um usuario existente em vez de cadastrar do zero
	public Usuario registrarUsuarioSistema(Object userDTO) {

		return operacaoUsuario.registrarUsuarioSistema(userDTO);

	}
//Todo: Mudar implementação para vincular a um usuario existente em vez de cadastrar do zero
	public Usuario criarUsuarioSistema(Object userDTO) {

		return operacaoUsuario.criarUsuarioSistema(userDTO);
	}

	public Object obterUsuarioOnline() {

		return operacaoUsuario.obterUsuarioCorrente();
	}

	public void updateUserAnonimo(UserAnonimoDTO usuarioDTO) {
		operacaoUsuario.updateUserAnonimo(usuarioDTO);
	}
	
	public void changePassword(String currentClearTextPassword, String newPassword) {
		operacaoUsuario.changePassword(currentClearTextPassword, newPassword);
	}
	
	public Usuario findOneByUsuarioContexto() {
		return operacaoUsuario.findOneByUsuarioContexto();
	}
	
	public void bloquearAcesso(boolean bool, String motivo, String username) {
		
		operacaoUsuario.bloqueadorDeAcesso(bool, motivo, username);
	}

	public Usuario findByIdCliente(Long id){

		operacaoUsuario.
	}

}
