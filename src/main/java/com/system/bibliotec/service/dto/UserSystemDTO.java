package com.system.bibliotec.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.system.bibliotec.model.Permissao;
import com.system.bibliotec.model.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSystemDTO extends PessoaDTO {

	private static final String MESSAGEM_ERRO_CADASTRO_USUARIO = "DETECTAMOS INCONSISTENCIA DE INFORMAÇÕES EM SEU CADASTRO. COMUNICAR EQUIPE ADMINISTRADORA DO SISTEMA COM URGENCIA";

	private String matricula;

	private String cargo;

	private String statusFuncionario;

	private String email;

	private String imageUrl;

	private boolean ativo;

	private String langKey;

	private List<String> permissoes;

	private String tipoUsuario;

	public UserSystemDTO(Usuario user) {

		this.email = (!user.getEmail().isEmpty()) ? user.getEmail() : MESSAGEM_ERRO_CADASTRO_USUARIO;
		this.nome = (!user.getNome().isEmpty()) ? user.getNome() : MESSAGEM_ERRO_CADASTRO_USUARIO;
		this.ativo = user.isAtivo();
		this.imageUrl = user.getImageUrl();
		this.langKey = user.getLangKey();
		this.permissoes.addAll(user.getPermissoes().stream().map(Permissao::getDescricao).collect(Collectors.toList()));
		this.matricula = (user.getFuncionario() != null && user.getFuncionario().getMatricula() != null)
				? user.getFuncionario().getMatricula()
				: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.matricula = (user.getFuncionario() != null && user.getFuncionario().getMatricula() != null
				&& !user.getFuncionario().getMatricula().isEmpty()) ? user.getFuncionario().getMatricula()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.statusFuncionario = (user.getFuncionario() != null && user.getFuncionario().getStatusFuncionario() != null)
				? user.getFuncionario().getStatusFuncionario().getStatusFuncionario()
				: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.cargo = (user.getFuncionario() != null && user.getFuncionario().getCargo() != null
				&& user.getFuncionario().getCargo().getNome() != null
				&& !user.getFuncionario().getCargo().getNome().isEmpty()) ? user.getFuncionario().getCargo().getNome()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.tipoUsuario = (user.getTipo() != null && user.getTipo().getTipo() != null
				&& !user.getTipo().getTipo().isEmpty()) ? user.getTipo().getTipo() : MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.rua = (user.getFuncionario() != null && user.getFuncionario().getEndereco() != null
				&& !user.getFuncionario().getEndereco().getLogradouro().isEmpty())
						? user.getFuncionario().getEndereco().getLogradouro()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.bairro = (user.getFuncionario() != null && user.getFuncionario().getEndereco() != null
				&& !user.getFuncionario().getEndereco().getBairro().isEmpty())
						? user.getFuncionario().getEndereco().getBairro()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.cep = (user.getFuncionario() != null && user.getFuncionario().getEndereco() != null
				&& !user.getFuncionario().getEndereco().getCep().isEmpty())
						? user.getFuncionario().getEndereco().getCep()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.cidade = (user.getFuncionario() != null && user.getFuncionario().getEndereco() != null
				&& !user.getFuncionario().getEndereco().getCidade().isEmpty())
						? user.getFuncionario().getEndereco().getCidade()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.numero = (user.getFuncionario() != null && user.getFuncionario().getEndereco() != null
				&& !user.getFuncionario().getEndereco().getNumero().isEmpty())
						? user.getFuncionario().getEndereco().getNumero()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.uf = (user.getFuncionario() != null && user.getFuncionario().getEndereco() != null
				&& !user.getFuncionario().getEndereco().getUf().isEmpty()) ? user.getFuncionario().getEndereco().getUf()
						: " ";

		this.complemento = (user.getFuncionario() != null && user.getFuncionario().getEndereco() != null
				&& !user.getFuncionario().getEndereco().getComplemento().isEmpty())
						? user.getFuncionario().getEndereco().getComplemento()
						: " ";

		this.telefoneResidencial = (user.getFuncionario() != null && user.getFuncionario().getContato() != null
				&& !user.getFuncionario().getContato().getTelefoneResidencial().isEmpty())
						? user.getFuncionario().getContato().getTelefoneResidencial()
						: " ";

		this.celular = (user.getFuncionario() != null && user.getFuncionario().getContato() != null
				&& !user.getFuncionario().getContato().getCelular().isEmpty())
						? user.getFuncionario().getContato().getCelular()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

		this.celular = (user.getFuncionario() != null && user.getFuncionario().getContato() != null
				&& !user.getFuncionario().getContato().getCelular().isEmpty())
						? user.getFuncionario().getContato().getCelular()
						: MESSAGEM_ERRO_CADASTRO_USUARIO;

	}

}
