package com.system.bibliotec.service.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.system.bibliotec.config.ConstantsUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.system.bibliotec.config.ConstantsUtils.DEFAULT_LANGUAGE;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAnonimoDTO extends PessoaDTO {



	

	@Pattern(regexp = ConstantsUtils.REGEX_EMAIL)
	@Size(min = 5, max = 254)
	private String email;

	@Size(max = 256)
	private String imageUrl;

	private boolean ativo = false;

	@Size(min = 2, max = 10)
	private String langKey = DEFAULT_LANGUAGE;

	private Set<String> permissoes;

	private String tipoUsuario;

	private String statusCliente;


	
	

}
