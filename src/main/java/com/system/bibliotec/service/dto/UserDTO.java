package com.system.bibliotec.service.dto;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

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

	private Set<String> authorities;

	public UserDTO() {
		// Empty constructor needed for Jackson.
	}

	public UserDTO(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.ativo = user.isAtivo();
		this.imageUrl = user.getImageUrl();
		this.langKey = user.getLangKey();
		this.createdBy = user.getCreatedBy();
		this.createdDate = user.getCreatedDate();
		this.lastModifiedBy = user.getLastModifiedBy();
		this.lastModifiedDate = user.getLastModifiedDate();
		this.authorities = user.getPermissoes().stream().map(Permissao::getDescricao).collect(Collectors.toSet());
	}

}
