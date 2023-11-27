package com.system.bibliotec.model;

import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusPessoa;
import com.system.bibliotec.model.enums.TipoPessoa;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import java.time.LocalDate;

@StaticMetamodel(Usuario.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Usuario_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Usuario#permissoes
	 **/
	public static volatile SetAttribute<Usuario, Permissao> permissoes;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#ativo
	 **/
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#tipo
	 **/
	public static volatile SingularAttribute<Usuario, TipoUsuarioVO> tipo;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#tipoPessoa
	 **/
	public static volatile SingularAttribute<Usuario, TipoPessoa> tipoPessoa;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#resetDate
	 **/
	public static volatile SingularAttribute<Usuario, Instant> resetDate;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#bloqueado
	 **/
	public static volatile SingularAttribute<Usuario, Boolean> bloqueado;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#motivoBloqueio
	 **/
	public static volatile SingularAttribute<Usuario, String> motivoBloqueio;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#cnpj
	 **/
	public static volatile SingularAttribute<Usuario, String> cnpj;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#sobreNome
	 **/
	public static volatile SingularAttribute<Usuario, String> sobreNome;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#senha
	 **/
	public static volatile SingularAttribute<Usuario, String> senha;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#imageUrl
	 **/
	public static volatile SingularAttribute<Usuario, String> imageUrl;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#genero
	 **/
	public static volatile SingularAttribute<Usuario, Genero> genero;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#cpf
	 **/
	public static volatile SingularAttribute<Usuario, String> cpf;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#statusPessoa
	 **/
	public static volatile SingularAttribute<Usuario, StatusPessoa> statusPessoa;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#funcionario
	 **/
	public static volatile SingularAttribute<Usuario, Funcionario> funcionario;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#dataNascimento
	 **/
	public static volatile SingularAttribute<Usuario, LocalDate> dataNascimento;
	
	/**
	 * @see com.system.bibliotec.model.Usuario
	 **/
	public static volatile EntityType<Usuario> class_;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#contato
	 **/
	public static volatile SingularAttribute<Usuario, Contato> contato;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#email
	 **/
	public static volatile SingularAttribute<Usuario, String> email;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#endereco
	 **/
	public static volatile SingularAttribute<Usuario, Endereco> endereco;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#nome
	 **/
	public static volatile SingularAttribute<Usuario, String> nome;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#chaveAtivacao
	 **/
	public static volatile SingularAttribute<Usuario, String> chaveAtivacao;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#userName
	 **/
	public static volatile SingularAttribute<Usuario, String> userName;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#langKey
	 **/
	public static volatile SingularAttribute<Usuario, String> langKey;
	
	/**
	 * @see com.system.bibliotec.model.Usuario#chaveRenovacao
	 **/
	public static volatile SingularAttribute<Usuario, String> chaveRenovacao;

	public static final String PERMISSOES = "permissoes";
	public static final String ATIVO = "ativo";
	public static final String TIPO = "tipo";
	public static final String TIPO_PESSOA = "tipoPessoa";
	public static final String RESET_DATE = "resetDate";
	public static final String BLOQUEADO = "bloqueado";
	public static final String MOTIVO_BLOQUEIO = "motivoBloqueio";
	public static final String CNPJ = "cnpj";
	public static final String SOBRE_NOME = "sobreNome";
	public static final String SENHA = "senha";
	public static final String IMAGE_URL = "imageUrl";
	public static final String GENERO = "genero";
	public static final String CPF = "cpf";
	public static final String STATUS_PESSOA = "statusPessoa";
	public static final String FUNCIONARIO = "funcionario";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String CONTATO = "contato";
	public static final String EMAIL = "email";
	public static final String ENDERECO = "endereco";
	public static final String NOME = "nome";
	public static final String CHAVE_ATIVACAO = "chaveAtivacao";
	public static final String USER_NAME = "userName";
	public static final String LANG_KEY = "langKey";
	public static final String CHAVE_RENOVACAO = "chaveRenovacao";

}

