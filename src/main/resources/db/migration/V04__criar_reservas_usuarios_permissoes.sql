CREATE TABLE IF NOT EXISTS tb_tipoUsuarioVO (
  id BIGINT  primary key AUTO_INCREMENT,
  created_by VARCHAR(70) NOT NULL,
  created_date DATE,
  last_modified_by VARCHAR(70),
  last_modified_date DATE,
  tipo VARCHAR(100) NOT NULL UNIQUE

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;

CREATE TABLE IF NOT EXISTS tb_users (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
    last_modified_date DATE,
    nome VARCHAR(50) NOT NULL,
    sobreNome VARCHAR(50),
	email VARCHAR(50) NOT NULL UNIQUE,
	senha VARCHAR(150) NOT NULL UNIQUE,
	bloqueado BOOLEAN NOT NULL,
	motivoBloqueio VARCHAR(255),
	lang_key VARCHAR(10),
	image_url VARCHAR(500),
	chave_ativacao VARCHAR(20),
	chave_renovacao VARCHAR(20),
	data_reset DATE,
	ativo BOOLEAN NOT NULL,
	idTipo BIGINT NOT NULL,
    genero VARCHAR(10) NOT NULL,
    cpf varchar(11) UNIQUE,
    cnpj varchar(14) UNIQUE,
    dataNascimento DATE NOT NULL,
    tipoPessoa VARCHAR(15) NOT NULL,
    statusPessoa VARCHAR(13) NOT NULL,
    celular VARCHAR(60) NOT NULL,
    telefoneResidencial VARCHAR(60),
    email_1  VARCHAR(60) NOT NULL UNIQUE,
    email_2 VARCHAR(60) UNIQUE,
    idEndereco BIGINT,
    idFuncionario BIGINT,
    FOREIGN KEY (idEndereco) REFERENCES tb_enderecos(id),
    FOREIGN KEY (idTipo) REFERENCES tb_tipoUsuarioVO(id),
    FOREIGN KEY (idFuncionario) REFERENCES tb_funcionarios(id)


)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;


CREATE TABLE IF NOT EXISTS tb_permissao (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
   	last_modified_date DATE,
	descricao VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;



CREATE TABLE IF NOT EXISTS tb_usuarios_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES tb_users(id),
	FOREIGN KEY (id_permissao) REFERENCES tb_permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS tb_reservas(

       id BIGINT  primary key AUTO_INCREMENT,
  	   created_by VARCHAR(70) NOT NULL,
       created_date DATE,
       last_modified_by VARCHAR(70),
       last_modified_date DATE,
        status VARCHAR(10) NOT NULL,
        horaReserva TIME NOT NULL,
        dataReserva DATE NOT NULL,
        horaRetiradaLivro TIME NOT NULL,
        dataRetiradaLivro DATE NOT NULL,
        dataPrevisaoTermino DATE NOT NULL,
        idUsuario BIGINT NOT NULL,
        idLivro BIGINT NOT NULL,

        FOREIGN KEY (idUsuario) REFERENCES tb_users(id),
        FOREIGN KEY (idLivro) REFERENCES tb_livros(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;
