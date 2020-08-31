CREATE TABLE IF NOT EXISTS acoesEndPoint(
                         id BIGINT  primary key AUTO_INCREMENT,
                         created_by VARCHAR(70),
                         created_date DATE,
                         last_modified_by VARCHAR(70),
                         last_modified_date DATE,
                         recursoSolicitado VARCHAR(255),                         
                         body varchar(5000),
                         method varchar(100) ,
                         atividadeEsperada varchar(255) ,
                         ip varchar(255)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;



CREATE TABLE IF NOT EXISTS acoesEndPoint_data_headers(
                                                     id BIGINT  primary key AUTO_INCREMENT,
                                                     header VARCHAR(255),
                                                     value VARCHAR(5000)


)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;




CREATE TABLE IF NOT EXISTS acoesEndPoint_data_parans(
                                                     id BIGINT  primary key AUTO_INCREMENT,
                                                     param VARCHAR(1000),
                                                     value VARCHAR(5000)


)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;



