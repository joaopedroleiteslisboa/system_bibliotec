package com.system.bibliotec.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.PermissoesRepositorio;
import com.system.bibliotec.repository.UsuarioRepository;

import io.github.jhipster.sample.domain.User;



@Service
@Transactional
public class UserService {

	
	 private final Logger log = LoggerFactory.getLogger(UserService.class);
	
	 @Autowired
	 private UsuarioRepository userRepository;

	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private PermissoesRepositorio permissaoRepositorio;
	 
	 
	 
	 public Optional<Usuario> activateRegistration(String key) {
	        log.debug("Activating user for activation key {}", key);
	        return userRepository.findOneByActivationKey(key)
	            .map(user -> {
	                // activate given user for the registration key.
	                user.setActivated(true);
	                user.setActivationKey(null);
	                this.clearUserCaches(user);
	                log.debug("Activated user: {}", user);
	                return user;
	            });
	    }
	 
	 
	 
	 
}
