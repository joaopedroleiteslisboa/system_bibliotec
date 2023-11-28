CREATE TABLE IF NOT EXISTS tb_locacoes(

      id BIGINT  primary key AUTO_INCREMENT,
      created_by VARCHAR(70) NOT NULL,
      created_date DATE,
      last_modified_by VARCHAR(70),
      last_modified_date DATE,
      status VARCHAR(20),
      quantidadeDeRenovacao INT NOT NULL,
      horaLocacao TIME NOT NULL,
      dataLocacao DATE NOT NULL,
      dataPrevisaoTermino DATE NOT NULL,
      horaEncerramento TIME,
      dataEncerramento DATE,
      horaCancelamento TIME,
      dataCancelamento DATE,
      idUsuario BIGINT NOT NULL,
      idLivro BIGINT NOT NULL,
      observacoesEntrega VARCHAR(100),
      observacoesDevolucao VARCHAR(100),
            
      FOREIGN KEY (idUsuario) REFERENCES tb_users(id),
      FOREIGN KEY (idLivro) REFERENCES tb_livros(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;



CREATE TABLE IF NOT EXISTS tb_solicitacoes (
	id BIGINT  primary key AUTO_INCREMENT,
    created_by VARCHAR(70) NOT NULL,
    created_date DATE,
    last_modified_by VARCHAR(70),
    last_modified_date DATE,    
    tipo VARCHAR(10) NOT NULL,  
    horaSolicitacao TIME NOT NULL, 
    dataSolicitacao DATE NOT NULL,    
    dataRetiradaExemplar DATE NOT NULL,    
    horaRetiradaExemplar TIME NOT NULL,
    idExemplar BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL,
    descricao VARCHAR(255),
    rejeitado BOOLEAN,
    idUsuario BIGINT NOT NULL,	

    FOREIGN KEY (idUsuario) REFERENCES tb_users(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;

