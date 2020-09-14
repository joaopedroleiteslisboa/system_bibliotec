CREATE TABLE IF NOT EXISTS solicitacoes (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
    last_modified_date DATE,
    tipo VARCHAR(10) NOT NULL,    
    dataRetiradaExemplar DATE NOT NULL,    
    horaRetiradaExemplar TIME NOT NULL,
    idExemplar BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL,
    descricao VARCHAR(255),
    rejeitado BOOLEAN,


	email VARCHAR(50) NOT NULL UNIQUE,
	senha VARCHAR(150) NOT NULL UNIQUE,
	
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
    FOREIGN KEY (idEndereco) REFERENCES enderecos(id),
    FOREIGN KEY (idTipo) REFERENCES tipoUsuarioVO(id),
    FOREIGN KEY (idFuncionario) REFERENCES funcionarios(id)
	

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;