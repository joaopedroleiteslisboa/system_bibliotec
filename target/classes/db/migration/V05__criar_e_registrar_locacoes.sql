CREATE TABLE locacoes(

      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      statusLocacao VARCHAR(20),
      quantidadeDeRenovacao INT NOT NULL,
      horaLocacao TIME NOT NULL,
      dataLocacao DATE NOT NULL,
      dataTerminoLocacao DATE NOT NULL,
      horaCancelamentoLocacao TIME,
      dataCancelamentoLocacao DATE,
      idCliente BIGINT NOT NULL,
      idLivro BIGINT NOT NULL,
      observacoesEntrega VARCHAR(100),
      observacoesDevolucao VARCHAR(100),
            
      FOREIGN KEY (idCliente) REFERENCES clientes(id),
      FOREIGN KEY (idLivro) REFERENCES livros(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;

