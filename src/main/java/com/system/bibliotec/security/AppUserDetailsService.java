package com.system.bibliotec.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import com.system.bibliotec.exception.EmailInvalidoException;
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
	    public UserDetails loadUserByUsername(final String login) {
	        log.debug("Authenticating {}", login);

	        if (new EmailValidator().isValid(login, null)) {
	            return usuarioRepository.findOneByEmailIgnoreCase(login)
	                .map(user -> createSpringSecurityUser(login, user))
	                .orElseThrow(() -> new EmailInvalidoException("Login " + login + " nao cadastrado em nossa base de dados."));
	        }

	        String lowercaseUserName = login.toLowerCase(new Locale("pt", "BR"));  
	        return usuarioRepository.findOneByUserName(lowercaseUserName)
	            .map(user -> createSpringSecurityUser(lowercaseUserName, user))
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario " + lowercaseUserName + " não encontrado em nossa base de dados"));

	    }

	    private UserSystem createSpringSecurityUser(String lowercaseLogin, Usuario user) {
	        if (!user.isAtivo()) {
	            throw new UsuarioNaoAtivadoException(user.saudacoes() + "Sua conta não estar ativada. É necessario ativa-la para realizar o login neste sistema. Realize o processo de recuperação de senha e ativa sua conta");
	        }else {

		        List<GrantedAuthority> grantedAuthorities = user.getPermissoes().stream()
		            .map(authority -> new SimpleGrantedAuthority(authority.getDescricao()))
		            .collect(Collectors.toList());

		        return new UserSystem(user, grantedAuthorities);

	        }	        
	       
	    }

	
	
	    private Collection<? extends GrantedAuthority> getPermissoesOLD(Usuario usuario) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
			return authorities;
		}
		
		
		
	
	
	
	
	
	
	
	
}
