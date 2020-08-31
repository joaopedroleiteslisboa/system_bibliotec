CREATE TABLE IF NOT EXISTS locacoes(

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
            
      FOREIGN KEY (idUsuario) REFERENCES users(id),
      FOREIGN KEY (idLivro) REFERENCES livros(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;

