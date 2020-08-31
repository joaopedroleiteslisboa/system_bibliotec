package com.system.bibliotec.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.system.bibliotec.model.Usuario;

public class UserSystem extends User {
	

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private String email;

	public UserSystem(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
		this.email = usuario.getEmail().toLowerCase();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	


}