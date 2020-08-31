package com.system.bibliotec.model;

import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusPessoa;
import com.system.bibliotec.model.enums.TipoPessoa;
import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile SetAttribute<Usuario, Permissao> permissoes;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile SingularAttribute<Usuario, TipoUsuarioVO> tipo;
	public static volatile SingularAttribute<Usuario, TipoPessoa> tipoPessoa;
	public static volatile SingularAttribute<Usuario, Instant> resetDate;
	public static volatile SingularAttribute<Usuario, Endereco> endereco;
	public static volatile SingularAttribute<Usuario, Boolean> bloqueado;
	public static volatile SingularAttribute<Usuario, String> motivoBloqueio;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> chaveAtivacao;
	public static volatile SingularAttribute<Usuario, String> cnpj;
	public static volatile SingularAttribute<Usuario, String> userName;
	public static volatile SingularAttribute<Usuario, String> sobreNome;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, String> langKey;
	public static volatile SingularAttribute<Usuario, String> imageUrl;
	public static volatile SingularAttribute<Usuario, Genero> genero;
	public static volatile SingularAttribute<Usuario, String> cpf;
	public static volatile SingularAttribute<Usuario, StatusPessoa> statusPessoa;
	public static volatile SingularAttribute<Usuario, Funcionario> funcionario;
	public static volatile SingularAttribute<Usuario, LocalDate> dataNascimento;
	public static volatile SingularAttribute<Usuario, Contato> contato;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> chaveRenovacao;

	public static final String PERMISSOES = "permissoes";
	public static final String ATIVO = "ativo";
	public static final String TIPO = "tipo";
	public static final String TIPO_PESSOA = "tipoPessoa";
	public static final String RESET_DATE = "resetDate";
	public static final String ENDERECO = "endereco";
	public static final String BLOQUEADO = "bloqueado";
	public static final String MOTIVO_BLOQUEIO = "motivoBloqueio";
	public static final String NOME = "nome";
	public static final String CHAVE_ATIVACAO = "chaveAtivacao";
	public static final String CNPJ = "cnpj";
	public static final String USER_NAME = "userName";
	public static final String SOBRE_NOME = "sobreNome";
	public static final String SENHA = "senha";
	public static final String LANG_KEY = "langKey";
	public static final String IMAGE_URL = "imageUrl";
	public static final String GENERO = "genero";
	public static final String CPF = "cpf";
	public static final String STATUS_PESSOA = "statusPessoa";
	public static final String FUNCIONARIO = "funcionario";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String CONTATO = "contato";
	public static final String EMAIL = "email";
	public static final String CHAVE_RENOVACAO = "chaveRenovacao";

}

