CREATE TABLE livros(

        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        codBarras CHAR(13) NOT NULL,
        imagenUrl varchar(2048),
        nome VARCHAR(200) NOT NULL,
        edicao VARCHAR(9),
        statusLivro VARCHAR(10) DEFAULT 'LIVRE',
        idioma VARCHAR(12) NOT NULL,
        descricao MEDIUMTEXT,
        isbn13 varchar(30) NOT NULL UNIQUE,
        numeroPaginas LONG NOT NULL,
        dataPublicacao DATE NOT NULL,
        preco DECIMAL NOT NULL,
        idEditora BIGINT,
        quantidade int,
  
        FOREIGN KEY (idEditora) REFERENCES editoras(id)


)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;


CREATE TABLE autores(

        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(50) NOT NULL,
        descricao MEDIUMTEXT NOT NULL
       
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

CREATE TABLE livro_has_autores (

	id_livro BIGINT NOT NULL,
	id_autor BIGINT NOT NULL,
	PRIMARY KEY (id_livro, id_autor),
	FOREIGN KEY (id_livro) REFERENCES livros(id),
	FOREIGN KEY (id_autor) REFERENCES autores(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE categorias(

        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(40) NOT NULL UNIQUE

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

CREATE TABLE livro_has_categorias (

	id_livro BIGINT NOT NULL,
	id_categoria BIGINT NOT NULL,
	PRIMARY KEY (id_livro, id_categoria),
	FOREIGN KEY (id_livro) REFERENCES livros(id),
	FOREIGN KEY (id_categoria) REFERENCES categorias(id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE editoras(

        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(50) NOT NULL,
        descricao MEDIUMTEXT NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;