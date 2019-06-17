
CREATE TABLE clientes(
                         id BIGINT  primary key AUTO_INCREMENT,
                         StatusCliente VARCHAR(13) NOT NULL,
                         nome VARCHAR(200) NOT NULL,
                         sobreNome VARCHAR(50),
                         idade INT NOT NULL,
                         genero VARCHAR(10) NOT NULL,
                         cpf varchar(11) UNIQUE NOT NULL,
                         dataNascimento DATE NOT NULL,
                         tipoCliente VARCHAR(15) NOT NULL,
                         celular VARCHAR(60) NOT NULL,
                         telefoneResidencial VARCHAR(60),
                         email_1  VARCHAR(60) NOT NULL,
                         email_2 VARCHAR(60),
                         idEndereco BIGINT NOT NULL,
                         FOREIGN KEY (idEndereco) REFERENCES enderecos(id)

                                         

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;

 