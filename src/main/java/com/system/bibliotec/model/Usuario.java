package com.system.bibliotec.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusPessoa;
import com.system.bibliotec.model.enums.TipoPessoa;
import com.system.bibliotec.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Usuario extends AbstractAuditingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2367370144472779826L;

	@NotNull
	@Column(name = "ativo", nullable = false)
	private boolean ativo = false;

	@Column(name = "bloqueado", nullable = false)
	private boolean bloqueado = false;

	@Column(name = "motivoBloqueio")
	private String motivoBloqueio;

	@Column(name = "nome")
	private String nome;

	@NotNull
	@Pattern(regexp = ConstantsUtils.REGEX_EMAIL)
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String email;

	@Column(name = "userName", length = 50, unique = true, nullable = true)
	private String userName;

	@Column(name = "senha")
	private String senha;

	@Size(min = 2, max = 10)
	@Column(name = "lang_key", length = 10)
	private String langKey;

	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	private String imageUrl;

	@Size(max = 20)
	@Column(name = "chave_ativacao", length = 20)
	@JsonIgnore
	private String chaveAtivacao;

	@Size(max = 20)
	@Column(name = "chave_renovacao", length = 20)
	@JsonIgnore
	private String chaveRenovacao;

	@Column(name = "data_reset")
	private Instant resetDate = null;

	@JoinColumn(name = "idTipo")
	@OneToOne
	private TipoUsuarioVO tipo;

	public boolean isAtivo() {
		return ativo;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_permissao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	private Set<Permissao> permissoes = new HashSet<>();

	@Size(min = 3, max = 50)
	@Column(name = "sobreNome")
	private String sobreNome;

	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private Genero genero;

	@Size(min = 11, max = 11, message = "CPF com formatação Invalida")
	@Column(name = "cpf", unique = true)
	private String cpf;

	
	@Column(name = "cnpj", unique = true)
	private String cnpj;

	@Column(name = "dataNascimento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@Embedded
	private Contato contato;

	@Enumerated(EnumType.STRING)
	@Column(name = "statusPessoa")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private StatusPessoa statusPessoa;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoPessoa")
	private TipoPessoa tipoPessoa;

	@JoinColumn(name = "idEndereco")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Endereco endereco;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JoinColumn(name = "idFuncionario")
	@OneToOne
	private Funcionario funcionario;

	public boolean isFuncionario() {

		return (funcionario != null) ? true : false;
	}

	@Override
	public String saudacoes() {
		
		String pattern = "\\S+";
		String primeiroNome = null;		
		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
		try {
			primeiroNome = r.matcher(this.nome).group(0);
		} catch (IllegalStateException e) {
		
		int inicial = 1;
		int espaco = this.getNome().indexOf(" ");		
		primeiroNome = this.getNome().substring(inicial -1, espaco).concat(".");
				
		}
		return (this.genero == Genero.FEMININO) ? "Prezada " + primeiroNome : "Prezado " + primeiroNome;
	}

	
	public boolean isJuridica() {

		return (this.cnpj != null && !this.cnpj.isEmpty() && this.tipoPessoa.equals(TipoPessoa.JURIDICA)) ? true
				: false;
	}
		

	public Usuario(Usuario usuario) {
		// TODO Auto-generated constructor stub

		this.ativo = usuario.isAtivo();
		this.bloqueado = usuario.isBloqueado();
		this.chaveAtivacao = usuario.getChaveAtivacao();
		this.chaveRenovacao = usuario.getChaveRenovacao();
		this.cnpj = usuario.getCnpj();
		this.cpf = usuario.getCpf();
		this.contato = usuario.getContato();
		this.dataNascimento = usuario.getDataNascimento();
		this.email = usuario.getEmail();
		this.endereco = usuario.getEndereco();
		this.funcionario = usuario.getFuncionario();
		this.genero = usuario.getGenero();
		this.imageUrl = usuario.getImageUrl();
		this.langKey = usuario.getLangKey();
		this.motivoBloqueio = usuario.getMotivoBloqueio();
		this.nome = usuario.getNome();
		this.sobreNome = usuario.getSobreNome();
		this.permissoes = usuario.getPermissoes();
		this.resetDate = usuario.getResetDate();
		this.senha = usuario.getSenha();
		this.statusPessoa = usuario.getStatusPessoa();
		this.tipo = usuario.getTipo();
		this.tipoPessoa = usuario.getTipoPessoa();

	}

}
