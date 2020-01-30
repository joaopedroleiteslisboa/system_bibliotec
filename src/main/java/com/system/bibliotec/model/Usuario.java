package com.system.bibliotec.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.bibliotec.config.ConstantsUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

	@Column(name = "nome")
	private String nome;

	@NotNull
	@Pattern(regexp = ConstantsUtils.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String email;

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

	@JoinColumn(name = "tipo")
	@OneToOne
	private TipoUsuarioVO tipo;

	@JoinColumn(name = "id_cliente")
	@OneToOne
	private Cliente cliente;

	@JoinColumn(name = "id_funcionario")
	@OneToOne
	private Funcionario funcionario;

	public boolean isAtivo() {
		return ativo;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_permissao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	private Set<Permissao> permissoes = new HashSet<>();

}
