package com.system.bibliotec.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.dto.UserSystemDTO;

public class UserMapper {

	
	
	 public List<UserSystemDTO> usersToUserSystemDTOs(List<Usuario> users) {
	        return users.stream()
	            .filter(Objects::nonNull)
	            .map(this::userToUserSystemDTO)
	            .collect(Collectors.toList());
	    }
	 
	 
	 public List<UserAnonimoDTO> usersToUserAnonimosDTOs(List<Usuario> users) {
	        return users.stream()
	            .filter(Objects::nonNull)
	            .map(this::userToUserAnonimoDTO)
	            .collect(Collectors.toList());
	    }

	    public UserSystemDTO userToUserSystemDTO(Usuario user) {
	        return new UserSystemDTO(user);
	    }

	    public UserAnonimoDTO userToUserAnonimoDTO(Usuario user) {
	        return new UserAnonimoDTO(user);
	    }
	    

	    public Usuario userDTOToUser(UserSystemDTO userDTO) {
	        if (userDTO == null) {
	            return null;
	        } else {
	        	
	        	Usuario user = new Usuario();

	            user.setEmail(userDTO.getEmail());
	            user.nome(userDTO.getNome()());
	            user.setLastName(userDTO.getLastName());
	            user.setEmail(userDTO.getEmail());
	            user.setImageUrl(userDTO.getImageUrl());
	            user.setActivated(userDTO.isActivated());
	            user.setLangKey(userDTO.getLangKey());
	            Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
	            user.setAuthorities(authorities);
	            return user;
	        }
	    }


	    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
	        Set<Authority> authorities = new HashSet<>();

	        if(authoritiesAsString != null){
	            authorities = authoritiesAsString.stream().map(string -> {
	                Authority auth = new Authority();
	                auth.setName(string);
	                return auth;
	            }).collect(Collectors.toSet());
	        }

	        return authorities;
	    }

	    public User userFromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        User user = new User();
	        user.setId(id);
	        return user;
	    }
}
