package com.system.bibliotec.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.system.bibliotec.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSystem extends Usuario implements UserDetails {
	

	private static final long serialVersionUID = 1L;



	private String email;
	private String password;
	private String username;


	private Collection<? extends GrantedAuthority> authorities;

	public UserSystem(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario);

		this.email = usuario.getEmail().toLowerCase();
		this.password = usuario.getSenha();
		this.username = usuario.getEmail();
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}