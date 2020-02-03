package com.system.bibliotec.service.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.CargoInexistenteException;
import com.system.bibliotec.model.Cargo;
import com.system.bibliotec.model.Funcionario;
import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.CargoRepository;
import com.system.bibliotec.repository.PermissoesRepositorio;
import com.system.bibliotec.repository.TipoUsuarioVORepository;
import com.system.bibliotec.service.dto.UserAnonimoDTO;
import com.system.bibliotec.service.dto.UserSystemDTO;

@Service
public class UserMapperServiceAdpter {

	private static final String DEFAULT_TIPO_USUARIO_SISTEMA= "FUNCIONARIO-GERAL";
	
	private static final String DEFAULT_TIPO_USUARIO_ANONIMO= "ANONIMO";
	
	private static final Set<String> DEFAULT_PERMISSOES_USUARIO_SISTEMA =  Stream.of("ROLE_CADASTRAR_LIVRO","ROLE_PESQUISAR_LIVRO","ROLE_CADASTRAR_PESSOA",
								"ROLE_PESQUISAR_PESSOA", "ROLE_PESQUISAR_LOCACAO", "ROLE_PESQUISAR_RESERVA", "ROLE_PESQUISAR_CLIENT_APP", "ROLE_USER_SYSTEM").collect(Collectors.toSet());
	
	private static final boolean DEFAULT_ATIVO = true;

	private static final boolean DEFAULT_CRIACAO_USUARIO_INATIVO = false;
	
	@Autowired
	private PermissoesRepositorio permissaoRepositorio;
	
	@Autowired
	private TipoUsuarioVORepository tipoUsuarioVORepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
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
	    

	    public Usuario userSystemDTOToUser(UserSystemDTO userDTO) {
	        if (userDTO == null) {
	            return null;
	        } else {
	        	
	        	Usuario user = new Usuario();
	        	
	        	Funcionario func = new Funcionario();
	        	
	        	user.setImageUrl(userDTO.getImageUrl());
	            user.setEmail(userDTO.getEmail());
	            user.setAtivo(false);
	            user.setLangKey((userDTO.getLangKey() !=null && !userDTO.getLangKey().isEmpty())? userDTO.getLangKey(): null);
	            String senhaCriptografada = passwordEncoder.encode(userDTO.getSe);
	           
	            if (userDTO.getPermissoes() != null) {
		            Set<Permissao> permissoes = userDTO.getPermissoes().stream()
		                .map(permissaoRepositorio::findOneByDescricaoIgnoreCase)
		                .filter(Optional::isPresent)
		                .map(Optional::get)
		                .collect(Collectors.toSet());
		            user.setPermissoes(permissoes);
	            }else {
	            	 
	 		            Set<Permissao> permissoes = DEFAULT_PERMISSOES_USUARIO_SISTEMA.stream()
	 		                .map(permissaoRepositorio::findOneByDescricaoIgnoreCase)
	 		                .filter(Optional::isPresent)
	 		                .map(Optional::get)
	 		                .collect(Collectors.toSet());
	 		            user.setPermissoes(permissoes);
	            }
	            
	            
	        	if(userDTO.getTipoUsuario() == null) {
	    			tipoUsuarioVORepository.findOneByTipoIgnoreCase(DEFAULT_TIPO_USUARIO_SISTEMA).ifPresent(user::setTipo);
	    		}else {
	    			tipoUsuarioVORepository.findOneByTipoIgnoreCase(userDTO.getTipoUsuario().toUpperCase()).ifPresent(user::setTipo);
	    		}
	        	
	            
	            func.setNome(userDTO.getNome() !=null && !userDTO.getNome().isEmpty() ? userDTO.getNome(): null);
	            func.setSobreNome(userDTO.getSobreNome() != null && !userDTO.getSobreNome().isEmpty()? userDTO.getSobreNome(): null);
	            func.setDataNascimento((userDTO.getDataNascimento() != null && !userDTO.getDataNascimento().isEmpty())? LocalDate.parse(userDTO.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")): null);
	            func.setAtivo(true);	         
	            func.setCargo(cargoRepository.findOneByNomeIgnoreCase(userDTO.getCargo()).orElse(cargoRepository.findOneByCodigoIgnoreCase(userDTO.getCargo()).orElseThrow(() ->  new CargoInexistenteException("Cargo Inexistente Informe um cargo existente"))));
	                 
	     
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
