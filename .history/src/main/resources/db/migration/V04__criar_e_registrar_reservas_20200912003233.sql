CREATE TABLE IF NOT EXISTS reservas(

       id BIGINT  primary key AUTO_INCREMENT,
  	   created_by VARCHAR(70) NOT NULL,
       created_date DATE,
       last_modified_by VARCHAR(70),
       last_modified_date DATE,
        status VARCHAR(10) NOT NULL,
        horaReserva TIME NOT NULL,
        dataReserva DATE NOT NULL,
        horaReserva TIME NOT NULL,
        dataReserva DATE NOT NULL,
        dataPrevisaoTermino DATE NOT NULL,
        idUsuario BIGINT NOT NULL,
        idLivro BIGINT NOT NULL,
        
        
        FOREIGN KEY (idUsuario) REFERENCES users(id),
        FOREIGN KEY (idLivro) REFERENCES livros(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;
