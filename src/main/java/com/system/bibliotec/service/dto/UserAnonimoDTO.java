package com.system.bibliotec.service.dto;

import static com.system.bibliotec.config.ConstantsUtils.DEFAULT_LANGUAGE;

import java.util.Set;
import java.util.stream.Collectors;

import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAnonimoDTO extends PessoaDTO {


	private static final String MESSAGEM_ERRO_CADASTRO_USUARIO = "DETECTAMOS INCONSISTENCIA DE INFORMAÇÕES EM SEU CADASTRO. COMUNICAR EQUIPE ADMINISTRADORA DO SISTEMA COM URGENCIA";
	

	
	private String email;

	
	private String imageUrl;

	private boolean ativo = false;

	
	private String langKey = DEFAULT_LANGUAGE;

	private Set<String> permissoes;

	private String tipoUsuario;

	private String statusCliente;


	public UserAnonimoDTO(Usuario user) {

		this.email = (!user.getEmail().isEmpty()) ? user.getEmail() : MESSAGEM_ERRO_CADASTRO_USUARIO;
		this.nome = (!user.getNome().isEmpty()) ? user.getNome() : MESSAGEM_ERRO_CADASTRO_USUARIO;
		this.ativo = user.isAtivo();
		this.imageUrl = user.getImageUrl();
		this.langKey = user.getLangKey();
		this.permissoes.addAll(user.getPermissoes().stream().map(Permissao::getDescricao).collect(Collectors.toList()));


		this.statusCliente = (user.getCliente() != null && user.getCliente().getStatusCliente() != null)
				? user.getCliente().getStatusCliente().getstatusCliente()
				: MESSAGEM_ERRO_CADASTRO_USUARIO;

		
		this.tipoUsuario = (user.getTipo() != null && user.getTipo().getTipo() != null
				&& !user.getTipo().getTipo().isEmpty()) ? user.getTipo().getTipo() : MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.rua = (user.getCliente() != null && user.getCliente().getEndereco() != null
				&& !user.getCliente().getEndereco().getLogradouro().isEmpty())
						? user.getCliente().getEndereco().getLogradouro()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.bairro = (user.getCliente() != null && user.getCliente().getEndereco() != null
				&& !user.getCliente().getEndereco().getBairro().isEmpty())
						? user.getCliente().getEndereco().getBairro()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.cep = (user.getCliente() != null && user.getCliente().getEndereco() != null
				&& !user.getCliente().getEndereco().getCep().isEmpty())
						? user.getCliente().getEndereco().getCep()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.cidade = (user.getCliente() != null && user.getCliente().getEndereco() != null
				&& !user.getCliente().getEndereco().getCidade().isEmpty())
						? user.getCliente().getEndereco().getCidade()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.numero = (user.getCliente() != null && user.getCliente().getEndereco() != null
				&& !user.getCliente().getEndereco().getNumero().isEmpty())
						? user.getCliente().getEndereco().getNumero()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.uf = (user.getCliente() != null && user.getCliente().getEndereco() != null
				&& !user.getCliente().getEndereco().getUf().isEmpty()) ? user.getCliente().getEndereco().getUf()
						: " ";

		this.complemento = (user.getCliente() != null && user.getCliente().getEndereco() != null
				&& !user.getCliente().getEndereco().getComplemento().isEmpty())
						? user.getCliente().getEndereco().getComplemento()
						: " ";

		this.telefoneResidencial = (user.getCliente() != null && user.getCliente().getContato() != null
				&& !user.getCliente().getContato().getTelefoneResidencial().isEmpty())
						? user.getCliente().getContato().getTelefoneResidencial()
						: " ";

		this.celular = (user.getCliente() != null && user.getCliente().getContato() != null
				&& !user.getCliente().getContato().getCelular().isEmpty())
						? user.getCliente().getContato().getCelular()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.celular = (user.getCliente() != null && user.getCliente().getContato() != null
				&& !user.getCliente().getContato().getCelular().isEmpty())
						? user.getCliente().getContato().getCelular()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

	}
	

}
