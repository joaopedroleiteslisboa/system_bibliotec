package com.system.bibliotec.service.operacoes.auditor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.system.bibliotec.exception.UsuarioNaoEncontrado;
import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.security.UserSystem;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public interface IAuditorTokenDeUsuarioDoContexto{
	

	default String obterUsuarioDoContextoPeloToken() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.of(securityContext.getAuthentication()).map(authentication -> {
			if (authentication.getPrincipal() instanceof UserSystem) {
				UserSystem springSecurityUser = (UserSystem) authentication.getPrincipal();
				return springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				return (String) authentication.getPrincipal();
			}
			return null;
		}).orElseThrow(() -> new UsuarioNaoEncontrado("Usuario não encontrado. Error Interno da Aplicação"));
	}

	 default Optional<String> obterUsuarioDoContextoPeloTokenOptional() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
			if (authentication.getPrincipal() instanceof UserSystem) {
				UserSystem springSecurityUser = (UserSystem) authentication.getPrincipal();
				return springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				return (String) authentication.getPrincipal();
			}
			return null;
		});
	}

	public static Optional<String> getCurrentTokenJWT() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication())
				.filter(authentication -> authentication.getCredentials() instanceof String)
				.map(authentication -> (String) authentication.getCredentials());
	}

	default boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null
				&& getAuthorities(authentication).noneMatch(AuthoritiesConstantsUltis.ROLE_USER_ANONIMO::equalsIgnoreCase);
	}

	default boolean isCurrentUserInRole(String authority) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && getAuthorities(authentication).anyMatch(authority::equals);
	}

	default Stream<String> getAuthorities(Authentication authentication) {
		return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
	}

	default List<String> getAuthorities() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}


}
