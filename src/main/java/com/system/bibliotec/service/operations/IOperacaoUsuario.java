package com.system.bibliotec.service.operations;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.dto.UserSystemDTO;

@Service
public interface IOperacaoUsuario {

	
	
	public Optional<Usuario> activateRegistration(String key);
	
	public Optional<Usuario> completePasswordReset(String newPassword, String key);
	
	public Optional<Usuario> requestPasswordReset(String mail);
	
	public Usuario registrarUsuarioAnonimo(UserAnonimoDTO userDTO, String password);
	
	public Usuario registrarUsuarioSistema(UserSystemDTO userDTO, String password);
	
	public Usuario criarUsuarioSistema(UserSystemDTO userDTO);
}
