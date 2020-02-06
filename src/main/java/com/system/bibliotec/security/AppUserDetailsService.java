package com.system.bibliotec.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.exception.UsuarioNaoAtivadoException;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.UsuarioRepository;


@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private final Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);
		// TODO Auto-generated method stub

	
	  @Override
	    @Transactional
	    public UserDetails loadUserByUsername(final String login) {
	        log.debug("Authenticating {}", login);

	        if (new EmailValidator().isValid(login, null)) {
	            return usuarioRepository.findOneByEmailIgnoreCase(login)
	                .map(user -> createSpringSecurityUser(login, user))
	                .orElseThrow(() -> new UsernameNotFoundException("Email " + login + " nao encontrado em nossa base de dados"));
	        }

	        String lowercaseLogin = login.toLowerCase(new Locale("pt", "BR"));  // << Ja ta na hora do Brasil ter o seu
	        return usuarioRepository.findOneByEmailIgnoreCase(lowercaseLogin)
	            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario " + lowercaseLogin + " nao encontrado em nossa base de dados"));

	    }

	    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, Usuario user) {
	        if (!user.isAtivo()) {
	            throw new UsuarioNaoAtivadoException("Usuario " + lowercaseLogin + " nao ativado");
	        }
	        List<GrantedAuthority> grantedAuthorities = user.getPermissoes().stream()
	            .map(authority -> new SimpleGrantedAuthority(authority.getDescricao()))
	            .collect(Collectors.toList());
	        return new org.springframework.security.core.userdetails.User(user.getEmail(),
	            user.getSenha(),
	            grantedAuthorities);
	    }
	
	
		private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
			return authorities;
		}
		
		
		
	
	
	
	
	
	
	
	
}
