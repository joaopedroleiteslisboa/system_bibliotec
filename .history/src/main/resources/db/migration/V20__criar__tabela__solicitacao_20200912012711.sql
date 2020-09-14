CREATE TABLE IF NOT EXISTS solicitacoes (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
    last_modified_date DATE,
    idCliente BIGINT NOT NULL,
    tipo VARCHAR(10) NOT NULL,    
    dataRetiradaExemplar DATE NOT NULL,    
    horaRetiradaExemplar TIME NOT NULL,
    idExemplar BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL,
    descricao VARCHAR(255),
    rejeitado BOOLEAN
    idUsuario BIGINT,	

    FOREIGN KEY (idUsuario) REFERENCES users(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;