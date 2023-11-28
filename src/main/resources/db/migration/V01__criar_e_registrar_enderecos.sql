SET FOREIGN_KEY_CHECKS = 0;

commit;

CREATE TABLE IF NOT EXISTS tb_enderecos(
                         id BIGINT  primary key AUTO_INCREMENT,
                         created_by VARCHAR(70) NOT NULL,
                         created_date DATE,
                         last_modified_by VARCHAR(70),
                         last_modified_date DATE,
                         cep VARCHAR(10),
                         logradouro VARCHAR(200) NOT NULL,
                         numero VARCHAR(20) NOT NULL,
                         complemento varchar(100),
                         bairro varchar(60) NOT NULL,
                         cidade varchar(40) NOT NULL,
                         uf varchar(4) NOT NULL,
                         ibge varchar(100)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;