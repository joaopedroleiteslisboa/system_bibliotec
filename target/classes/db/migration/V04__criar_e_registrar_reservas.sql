CREATE TABLE reservas(

        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        statusReserva VARCHAR(10) NOT NULL,
        horaReserva TIME NOT NULL,
        dataReserva DATE NOT NULL,
        dataLimite DATE NOT NULL,
        idCliente BIGINT NOT NULL,
        idLivro BIGINT NOT NULL,
        
        
        FOREIGN KEY (idCliente) REFERENCES clientes(id),
        FOREIGN KEY (idLivro) REFERENCES livros(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;
