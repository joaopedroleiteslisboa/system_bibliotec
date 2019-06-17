SET FOREIGN_KEY_CHECKS = 0; 
CREATE TABLE enderecos(
                         id BIGINT  primary key AUTO_INCREMENT,
                         cep VARCHAR(10),
                         logradouro VARCHAR(200) NOT NULL,
                         numero VARCHAR(20) NOT NULL,
                         complemento varchar(100),
                         bairro varchar(60) NOT NULL,
                         cidade varchar(40) NOT NULL,
                         uf varchar(4) NOT NULL,
                         ibge varchar(100),
                         idCliente BIGINT NOT NULL,
                         FOREIGN KEY (idCliente) REFERENCES clientes(id)
                         
                         
                        

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;