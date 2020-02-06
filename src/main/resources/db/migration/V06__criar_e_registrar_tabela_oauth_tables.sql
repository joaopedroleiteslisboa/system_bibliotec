CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255) UNIQUE,
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(255) UNIQUE,
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);


CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(255), 
  authentication LONG VARBINARY
);

CREATE TABLE IF NOT EXISTS oauth_approvals (
    userId VARCHAR(255) UNIQUE,
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);


CREATE TABLE IF NOT EXISTS ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS users (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
    last_modified_date DATE,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	senha VARCHAR(150) NOT NULL UNIQUE,
	lang_key VARCHAR(10),
	image_url VARCHAR(500),
	chave_ativacao VARCHAR(20),
	chave_renovacao VARCHAR(20),
	data_reset DATE,
	ativo BOOLEAN NOT NULL,
	tipo BIGINT NOT NULL,
	id_cliente BIGINT,
	id_funcionario BIGINT,
	
	FOREIGN KEY (tipo) REFERENCES tipoUsuarioVO(id),
	FOREIGN KEY (id_cliente) REFERENCES clientes(id),
	FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;



CREATE TABLE IF NOT EXISTS permissao (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
   	last_modified_date DATE,
	descricao VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;



CREATE TABLE IF NOT EXISTS usuarios_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES users(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS tipoUsuarioVO (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
   	last_modified_date DATE,
	tipo VARCHAR(100) NOT NULL UNIQUE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;