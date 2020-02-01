package com.system.bibliotec.service.dto;

import java.time.Instant;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.model.TipoUsuarioVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSystemDTO extends PessoaSystemDTO{
	
	
	private Long id;

	@NotBlank
	@Pattern(regexp = ConstantsUtils.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String nome;

	@Email
	@Size(min = 5, max = 254)
	private String email;

	@Size(max = 256)
	private String imageUrl;

	private boolean ativo = false;

	@Size(min = 2, max = 10)
	private String langKey;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;

	private Set<String> permissoes;

	private String tipoUsuario;
	
	

}
