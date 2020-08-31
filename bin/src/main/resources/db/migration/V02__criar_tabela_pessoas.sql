CREATE TABLE IF NOT EXISTS pessoas(
                         id BIGINT  primary key AUTO_INCREMENT,
                         created_by VARCHAR(70) NOT NULL,
                         created_date DATE,
                         last_modified_by VARCHAR(70),
                         last_modified_date DATE,
                         ativo BOOLEAN NOT NULL,
                         statusPessoa VARCHAR(13) NOT NULL,
                         nome VARCHAR(200) NOT NULL,
                         sobreNome VARCHAR(50),
                         genero VARCHAR(10) NOT NULL,
                         cpf varchar(11) UNIQUE,
                         cnpj varchar(14) UNIQUE,
                         dataNascimento DATE NOT NULL,
                         tipoPessoa VARCHAR(15) NOT NULL,
                         celular VARCHAR(60) NOT NULL,
                         telefoneResidencial VARCHAR(60),
                         email_1  VARCHAR(60) NOT NULL UNIQUE,
                         email_2 VARCHAR(60) UNIQUE,
                         idEndereco BIGINT,
                         FOREIGN KEY (idEndereco) REFERENCES enderecos(id)

                                   

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;




CREATE TABLE IF NOT EXISTS funcionarios(

                         id BIGINT  primary key AUTO_INCREMENT,
                         created_by VARCHAR(70) NOT NULL,
                         created_date DATE,
                         last_modified_by VARCHAR(70),
                         last_modified_date DATE,
                         ativo BOOLEAN NOT NULL,
                         matricula VARCHAR(100) NOT NULL UNIQUE,
                         cargo BIGINT,
                         FOREIGN KEY (cargo) REFERENCES cargos(id)

                                         

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;


CREATE TABLE IF NOT EXISTS cargos (
   						 id BIGINT  primary key AUTO_INCREMENT,
                         created_by VARCHAR(70) NOT NULL,
                         created_date DATE,
                         last_modified_by VARCHAR(70),
                         last_modified_date DATE,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(80) CHARACTER SET utf8 UNIQUE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;