	-- insert enderecos
	
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES('58100432', 'Rua: Desembargador Onaldo da Nobrega Montenegro', '347', 'Primeiro Andar', 'Santa Catarina', 'Cabedelo', 'PB', '2503209');
	
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES ('58039010', 'Avenida Almirante Tamandaré', '229', 'Hotel Tambaú', 'Tambaú', 'João Cliente', 'PB', '2507507');
	
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES ('58045130', 'Rua Paulino Pinto', '1500', 'TI', 'Cabo Branco', 'João Cliente', 'PB', '2507507');
	
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES ('58046088', 'Rua Bancário Elias Feliciano Madruga', '300', 'Tours Mont Blanc', 'Altiplano Cabo Branco', 'João Cliente', 'PB', '2507507');
	
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES ('58046088', 'Rua Bancário Elias Feliciano Madruga', '300', 'Tours Mont Blanc', 'Altiplano Cabo Branco', 'João Cliente', 'PB', '2507507');
	
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES ('58046088', 'Rua Bancário Elias Feliciano Madruga', '300', 'Tours Mont Blanc', 'Altiplano Cabo Branco', 'João Cliente', 'PB', '2507507');
	
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES ('58046088', 'Rua Bancário Elias Feliciano Madruga', '300', 'Tours Mont Blanc', 'Altiplano Cabo Branco', 'João Cliente', 'PB', '2507507');
		
	INSERT INTO enderecos(cep, logradouro, numero, complemento, bairro, cidade, uf, ibge)VALUES ('58046088', 'Rua Bancário Elias Feliciano Madruga', '300', 'Tours Mont Blanc', 'Altiplano Cabo Branco', 'João Cliente', 'PB', '2507507');		
			

	-- insert clientes
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'João Pedro Leite Soares Lisboa', 'Leite Soares', 'MASCULINO', '09104537483', '1996-03-10', 'FISICA',   '83999630573', '8332280775', 'joaopedroleite.s.lisboa@outlook.com', 'joaopedroleite.s.lisboa.info@gmail.com', 1000);
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'Benjamim Gonçalves Soares', 'Gonçalves',  'MASCULINO', '45188013932', '2001-10-04', 'JURIDICA',   '83999638364', '8332283456', 'benjamim@outlook.com', 'benjamim@gmail.com', 1001);
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'Patricia Ferreira Lima', 'Ferreira', 'FEMININO', '87769972000', '1993-10-03', 'JURIDICA',   '83999637843', '8332282345', 'patriciaferreiralima@outlook.com', 'patriciaferreiralima@gmail.com', 1002);
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'Bianca cardoso', 'Cardoso',  'FEMININO', '03317970200', '1992-10-03', 'FISICA',   '83999633543', '8332287887', 'biancacardoso@outlook.com', 'biancacardoso@gmail.com', 1003);
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'Ze lezin', 'Cardoso',  'MASCULINO', '06615286424', '1992-10-03', 'FISICA',   '83999633543', '8332287887', 'bia@outlook.com', 'bia@gmail.com', 1004);
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'Fabiana', 'Soares',  'FEMININO', '74449085914', '1992-10-03', 'FISICA',   '83999633543', '8332287887', 'bianca@outlook.com', 'bianca@gmail.com', 1005);
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'Ze ', 'Cardoso', 'MASCULINO', '43506004506', '1992-10-03', 'FISICA',   '83999633543', '8332287887', 'cardoso@outlook.com', 'cacardoso@gmail.com', 1006);
	
	INSERT INTO clientes(ativo, statusCliente, nome, sobreNome, genero, cpf, dataNascimento, tipoCliente,  celular, telefoneResidencial, email_1, email_2, idEndereco)VALUES (true, 'ADIMPLENTE', 'Lezin', 'Feitosa', 'MASCULINO', '77353603470', '1992-10-03', 'FISICA',   '83999633543', '8332287887', 'microsoft@outlook.com', 'microsoft@gmail.com', 1007);
			
	
	
	
	-- insert livros
	
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('5417740815', 'https://picsum.photos/id/1/200/300','Ansiedade 2 - Autocontrole - Como Controlar o Estresse e Manter o Equilíbrio','Ed. 1ª','RESERVADO','PORTUGUES','Em Ansiedade 2 - Autocontrole, o psiquiatra e psicoterapeuta Augusto Cury revela os segredos para gerenciar o estresse e desenvolver o autocontrole, essencial para uma vida emocional saudável e plena. Além disso, apresenta a diferença entre ansiedade e estresse e ressalta que os dois são essenciais para a sobrevivência humana, mas que, como tudo na vida, precisam ser dosados. Alguns dos conceitos utilizados pelo autor foram apresentados no mega best-seller Ansiedade – Como enfrentar o mal do século, como, por exemplo, a Síndrome do Pensamento Acelerado (SPA), que vem pouco a pouco nos transformando em prisioneiros em nossa própria mente, suas consequências alarmantes e técnicas para enfrentá-la. Neste novo livro, Cury usa partes de sua própria história – de adolescente desinteressado a pesquisador com livros publicados em muitos países – para mostrar como a ansiedade e a SPA podem sabotar a maturidade e impedir o ser humano de ser líder de si mesmo. Cury conta como ele mesmo utilizou essas técnicas para vencer o medo de fracassar e alcançar o êxito profissional e Clientel.', '9788557170438',192,'2016-03-10', 10, 1000, 30);
		
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('9085468531', 'https://picsum.photos/id/2/200/300','Java - Como Programar', 'Ed. 10ª', 'RESERVADO', 'PORTUGUES',   'Milhões de alunos e profissionais aprenderam programação e desenvolvimento de software com os livros Deitel®. Java: como programar, 10ª edição, fornece uma introdução clara, simples, envolvente e divertida à programação Java com ênfase inicial em objetos. Destaques incluem: rica cobertura dos fundamentos com exemplos reais; apresentação com ênfase inicial em classes e objetos; uso com Java™ SE 7, Java™ SE 8 ou ambos; Java™ SE 8 abordado em seções modulares opcionais; lambdas, fluxos e interfaces funcionais usando métodos padrão e estáticos do Java SE 8; Swing e GUI do JavaFX: elementos gráficos e multimídia; conjunto de exercícios "Fazendo a diferença"; tratamento de exceções integrado; arquivos, fluxos e serialização de objetos; concorrência para melhor desempenho com multiprocessamento; o livro contém o conteúdo principal para cursos introdutórios; outros tópicos: recursão, pesquisa, classificação, coleções genéricas, estruturas de dados, multithreading, banco de dados (JDBC e JPA).', '9788543004792', 968, '2016-06-10',15.00, 1001, 60);
		
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('9085345531', 'https://picsum.photos/id/3/200/300','Vade Mecum Saraiva','Ed. 27ª','LOCADO','PORTUGUES', 'Pioneira na exemplar técnica desenvolvida de atualização de Códigos e Legislação, como comprova o avançado número de suas edições e versões, a Editora Saraiva apresenta a edição aumentada e atualizada de sua principal obra: o Vade Mecum Saraiva. Com novo projeto gráfico, conta com o acréscimo de mais de 140 diplomas (entre leis, decretos, regimentos internos etc.) em relação à edição anterior. Assim, passa a reunir as normas mais importantes do ordenamento jurídico brasileiro, com destaque para: - Código Penal Militar, Código de Processo Penal Militar e Estatuto dos Militares - Legislação Previdenciária - Principais Convenções Internacionais - Regimentos Internos do STF e do STJ (divisão exclusiva = agilidade na pesquisa) O conteúdo foi criteriosamente selecionado com base na grade curricular das principais faculdades de direito e em editais de concursos para carreiras jurídicas. Sua estrutura foi pensada de forma a proporcionar o maior número de diplomas legislativos sem comprometer a legibilidade e o trabalho editorial. As notas de correlação entre as matérias, já tão tradicionais nas obras de legislação Saraiva, foram mantidas, assim como os índices multifuncionais, que permitem rápida localização de qualquer assunto por ordem alfabética, cronológica, pelo número do diploma ou por seu tema. Tradição, organização e estrutura, aliadas à confiabilidade do nome Saraiva, explicam que seja há mais de 10 anos o produto de legislação mais vendido no mercado. Últimas Atualizações da 27ª edição (fechamento em 4-1-2019): - Código Civil Condomínio em multipropriedade - Lei n. 13.777, de 20-12-2018 Quórum de deliberação nas sociedades limitadas - Lei n. 13.792, de 3-1-2019 - Código de Processo Civil Processos eletrônicos - Lei n. 13.793, de 3-1-2019 - Código Penal Importunação sexual, estupros coletivo e corretivo e divulgação de sexo, estupro ou nudez de vulnerável – Lei n. 13.718, de 24-9-2018 Exposição da intimidade sexual – Lei n. 13.772, de 19-12-2018 - Código de Processo Penal Prisão domiciliar para gestante – Lei n. 13.769, de 19-12-2018 - CLT Falta justificada ao trabalho para realização de exames preventivos de câncer – Lei n. 13.767, de 18-12-2018 - Estatuto da Criança e do Adolescente Prevenção de gravidez na adolescência – Lei n. 13.798, de 3-1-2019 - Estatuto da OAB Honorários assistenciais - Lei n. 13.725, de 4-10-2018 Processos eletrônicos – Lei n. 13.793, de 3-1-2019 - Reforma Trabalhista – aplicação de normas processuais da CLT (IN n. 41, de 21-6-2018, do TST) - Lei de Proteção de Dados Clienteis (Lei n. 13.709, de 14-8-2018) - Trabalho temporário no Mercosul (Decreto n. 9.499, de 10-9-2018) - Execução indireta de serviços na Administração Pública (Decreto n. 9.507, de 21-9-2018) - Tratado de Marraqueche (Decreto n. 9.522, de 8-10-2018) - Juizados Especiais - contagem de prazos processuais (Lei n. 13.728, de 31-10-2018) - Gestão coletiva de direitos autorais (Decreto n. 9.574, de 22-11-2018) - Criança, adolescente e aprendiz (Decreto n. 9.579, de 22-11-2018) - Organização da Justiça Militar – alterações (Lei n. 13.774, de 19-12-2018) - Duplicata eletrônica (Lei n. 13.775, de 20-12-2018) - Contratos de compra e venda – resolução por inadimplência (Lei n. 13.786, de 27-12-2018) - Autoridade Nacional de Proteção de Dados Clienteis (MP 869, de 27-12-2018) - Ausência escolar por motivo religioso (Lei n. 13.796, de 3-1-2019) - Facilitação da posse de arma (alterações no Decreto do Desarmamento)','9788553603299',2600,'2019-02-11',15.00, 1002, 30);
		
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('5435468531', 'https://picsum.photos/id/4/200/300','História Geral do Brasil', 'Ed. 10ª','LOCADO','PORTUGUES', 'A História Geral do Brasil Parte I é um balanço moderno, atual e renovador do conjunto da História do Brasil, com a participação de historiadores consagrados por suas pesquisas e livros sobre os diversos períodos da nossa história de grandes universidades brasileiras - UFRJ, UFF, UERJ, UPE e UnB. Em linguagem simples, direta e rigorosa o livro percorre a história do país desde a colonização até os nossos dias, com ênfase nas caraterísticas gerais da colonização, na formação dos grupos sociais brasileiros, no Império e suas grandes características, incluindo a escravidão e sua superação. Analisa-se, também ,a criação da República e a ampliação da participação política no Brasil, suas crises, sucessos e transformações, com a emergência da classe operária, da urbanização e da industrialização.','978-8535285435','528', '2015-07-10', 5.00, 1003, 10);
	
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('9085469871', 'https://picsum.photos/id/5/200/300','História Geral do Brasil 2', 'Ed. 10ª','LOCADO','PORTUGUES', 'A História Geral do Brasil  Parte II,  é um balanço moderno, atual e renovador do conjunto da História do Brasil, com a participação de historiadores consagrados por suas pesquisas e livros sobre os diversos períodos da nossa história de grandes universidades brasileiras - UFRJ, UFF, UERJ, UPE e UnB. Em linguagem simples, direta e rigorosa o livro percorre a história do país desde a colonização até os nossos dias, com ênfase nas caraterísticas gerais da colonização, na formação dos grupos sociais brasileiros, no Império e suas grandes características, incluindo a escravidão e sua superação. Analisa-se, também ,a criação da República e a ampliação da participação política no Brasil, suas crises, sucessos e transformações, com a emergência da classe operária, da urbanização e da industrialização.','978-8535285436','528', '2016-07-10', 8.00, 1003, 10);
	
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('9085461231', 'https://picsum.photos/id/6/200/300','História Geral do Brasil 3', 'Ed. 10ª','LIVRE','PORTUGUES', 'A História Geral do Brasil  Parte III,  é um balanço moderno, atual e renovador do conjunto da História do Brasil, com a participação de historiadores consagrados por suas pesquisas e livros sobre os diversos períodos da nossa história de grandes universidades brasileiras - UFRJ, UFF, UERJ, UPE e UnB. Em linguagem simples, direta e rigorosa o livro percorre a história do país desde a colonização até os nossos dias, com ênfase nas caraterísticas gerais da colonização, na formação dos grupos sociais brasileiros, no Império e suas grandes características, incluindo a escravidão e sua superação. Analisa-se, também ,a criação da República e a ampliação da participação política no Brasil, suas crises, sucessos e transformações, com a emergência da classe operária, da urbanização e da industrialização.','978-8535285437','528', '2017-07-10', 15.00, 1003, 10);
	
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('3333333331', 'https://picsum.photos/id/7/200/300','História Geral do Brasil 4', 'Ed. 10ª','LIVRE','PORTUGUES', 'A História Geral do Brasil  Parte IV,  é um balanço moderno, atual e renovador do conjunto da História do Brasil, com a participação de historiadores consagrados por suas pesquisas e livros sobre os diversos períodos da nossa história de grandes universidades brasileiras - UFRJ, UFF, UERJ, UPE e UnB. Em linguagem simples, direta e rigorosa o livro percorre a história do país desde a colonização até os nossos dias, com ênfase nas caraterísticas gerais da colonização, na formação dos grupos sociais brasileiros, no Império e suas grandes características, incluindo a escravidão e sua superação. Analisa-se, também ,a criação da República e a ampliação da participação política no Brasil, suas crises, sucessos e transformações, com a emergência da classe operária, da urbanização e da industrialização.','978-8535285438','528', '2018-07-10', 35.00, 1003, 10);
	
	INSERT INTO livros(codBarras, imagenUrl, nome, edicao, statusLivro, idioma,  descricao, isbn13, numeroPaginas, dataPublicacao, valorUnitario, idEditora, quantidade) VALUES ('9085462341', 'https://picsum.photos/id/8/200/300','História Geral do Brasil 5', 'Ed. 10ª','LIVRE','PORTUGUES', 'A História Geral do Brasil  Parte V,  é um balanço moderno, atual e renovador do conjunto da História do Brasil, com a participação de historiadores consagrados por suas pesquisas e livros sobre os diversos períodos da nossa história de grandes universidades brasileiras - UFRJ, UFF, UERJ, UPE e UnB. Em linguagem simples, direta e rigorosa o livro percorre a história do país desde a colonização até os nossos dias, com ênfase nas caraterísticas gerais da colonização, na formação dos grupos sociais brasileiros, no Império e suas grandes características, incluindo a escravidão e sua superação. Analisa-se, também ,a criação da República e a ampliação da participação política no Brasil, suas crises, sucessos e transformações, com a emergência da classe operária, da urbanização e da industrialização.','978-8535285439','528', '2019-07-10', 50.00, 1003, 10);
		
	
	-- insert autores
	
	INSERT INTO autores(nome, descricao) VALUES ('Cury Augusto', 'Dr. Augusto Cury é psiquiatra, pesquisador e escritor. Seus livros são publicados em mais de 70 países e já vendeu mais de 30 milhões de livros somente no Brasil. O Best-Seller O Vendedor de Sonhos se tornou um filme e foi lançado nos cinemas em 2016. É autor da Teoria da Inteligência Multifocal, que analisa o processo de construção dos pensamentos. Idealizador do programa da Escola da Inteligência, que ensina educação sócio emocional para mais de 300 mil alunos, entre crianças e adolescentes. Criador da AGE - Academia de Gestão da Emoção Online, que tem como missão, democratizar todas as suas ferramentas de Gestão da Emoção, não apenas no Brasil, mas em todos os povos e culturas. Criador do Primeiro Programa Social e Gratuito - Você é Insubstituível – Uma Vacina Emocional contra o Suicídio.');
	
	INSERT INTO autores(nome, descricao) VALUES ('Paul J. Deitel','CEO e Diretor Técnico da Deitel & Associates, Inc., é formado pela MIT Sloan School of Management, onde estudou Tecnologia da Informação. Ele detém as certificações profissionais Java Certified Programmer e Java Certified Developer, foi designado pela Oracle Corporation como um Java Champion - "um membro proeminente da comunidade Java cuja entrada é solicitada pela empresa para melhorar a plataforma Java", e é um MVP do Microsoft C #. Ele ministrou cursos de linguagem de programação para clientes, incluindo várias empresas da Fortune 1000, organizações governamentais e militares, e é um dos treinadores corporativos mais experientes do mundo. ');
	
	INSERT INTO autores(nome, descricao) VALUES ('Autor - Grupo Saraiva','Saraiva é uma livraria fundada em 13 de dezembro de 1914 por Joaquim Inácio da Fonseca Saraiva, imigrante português de Trás-os-Montes, no centro da cidade de São Paulo. Em 2008 a empresa adquiriu a Livraria Siciliano e passou a ter 20% do mercado livreiro do Brasil. Com o setor em crise, a Saraiva entrou com um pedido de recuperação judicial no dia 23 de novembro de 2018, com uma divida de R$ 674 milhões, fechou 19 lojas pelo Brasil no mês de outubro, restando 85 lojas físicas');
	
	INSERT INTO autores(nome, descricao) VALUES ('Maria Yedda','Maria Yedda foi, desde cedo, uma mulher forte e decidida, assumindo, num Brasil ainda provinciano, um papel de protagonismo em decisões pouco usuais: a opção pelo trabalho profissional – inicialmente no recém-criado DASP –, quase que ainda adolescente, a ida para os Estados Unidos (para o Barnard College, da Universidade de Columbia) e, no retorno, em plena Segunda Guerra Mundial, num engajamento completo na luta contra os fascismos em expansão na Europa e no Brasil. Tornou-se uma das organizadoras da União Nacional dos Estudantes, no seu departamento cultural, quando inicia, ao lado de grandes intelectuais brasileiros, um combate coerente e constante em toda sua vida contra o fascismo. ');
	
    
	  -- insert categorias
    INSERT INTO categorias(nome) VALUES('Auto Ajuda');
    
	INSERT INTO categorias(nome) VALUES('Informatica');
    
	INSERT INTO categorias(nome) VALUES('Juridico');
    
	INSERT INTO categorias(nome) VALUES('Historia');
    
	INSERT INTO categorias(nome) VALUES('Revista');
    
	INSERT INTO categorias(nome) VALUES('Artigo');
    
	INSERT INTO categorias(nome) VALUES('Administração Publica');
    
	INSERT INTO categorias(nome) VALUES('Administração de Empresa');
    
	INSERT INTO categorias(nome) VALUES('Administração Pessoal');
    
	INSERT INTO categorias(nome) VALUES('Manual');
    
	INSERT INTO categorias(nome) VALUES('Tutorial');
    
	INSERT INTO categorias(nome) VALUES('Poesia');
    
	INSERT INTO categorias(nome) VALUES('Literatura');
    
	INSERT INTO categorias(nome) VALUES('Indioma');
    
	INSERT INTO categorias(nome) VALUES('Dicionario');
    
	INSERT INTO categorias(nome) VALUES('Religião');
    
	INSERT INTO categorias(nome) VALUES('Musical');
	
    
	-- livro_has_autores
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1000, 1000);
	
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1001, 1001);
	
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1002, 1002);
	
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1003, 1003);
	
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1004, 1003);
	
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1005, 1003);
	
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1006, 1003);
	
	INSERT INTO livro_has_autores (id_livro, id_autor) values (1007, 1003);
	
	
    
	-- livro_has_categorias
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1000, 1000);
	
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1001, 1001);
	
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1002, 1002);
	
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1003, 1003);
	
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1004, 1003);
	
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1005, 1003);
	
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1006, 1003);
	
	INSERT INTO livro_has_categorias (id_livro, id_categoria) values (1007, 1003);
		
	
	-- insert editoras
		
	INSERT INTO editoras(nome, descricao) VALUES ('Benvirá', 'Em 2008, a Saraiva compra a Siciliano, trazendo para a editora o selo ARX, de ficção e não ficção. Dois anos depois, ARX vira Benvirá, e o novo selo se reposiciona no mercado com um catálogo único, mais literário: dos clássicos norte-americanos William Faulkner e John dos Passos a premiados representantes da literatura hispano-americana, como Pola Oloixarac, José Donoso e Juan Eslava Galán.');
	
	INSERT INTO editoras(nome, descricao) VALUES ('Pearson', 'As origens da Pearson datam de 1724, quando Thomas Longman fundou a Longman, mas a Pearson, na verdade, foi fundada em 1844 por Samuel Pearson como S. Pearson and Son, uma pequena empresa de construção, em Yorkshire, no norte da Inglaterra. Por volta de 1880, a Pearson tornou-se uma das maiores construtoras do mundo, em um momento em que a indústria controlava desenvolvimento dos transportes, comércio e telecomunicações que alimentavam as economias mundiais.');
	
	INSERT INTO editoras(nome, descricao) VALUES ('Editora - Grupo Saraiva', 'Saraiva é uma livraria fundada em 13 de dezembro de 1914 por Joaquim Inácio da Fonseca Saraiva, imigrante português de Trás-os-Montes, no centro da cidade de São Paulo. Em 2008 a empresa adquiriu a Livraria Siciliano e passou a ter 20% do mercado livreiro do Brasil. Com o setor em crise, a Saraiva entrou com um pedido de recuperação judicial no dia 23 de novembro de 2018, com uma divida de R$ 674 milhões, fechou 19 lojas pelo Brasil no mês de outubro, restando 85 lojas físicas');
	
	INSERT INTO editoras(nome, descricao) VALUES ('LEFT JOIN TEST','O JOIN É  líder mundial em soluções de informação que vão desde a publicação de literatura científica, médica e técnica até os mais modernos sistemas e plataformas, que possibilitam melhorias no desenvolvimento da ciência, saúde e também na capacitação dos profissionais de tecnologia. Somos uma empresa internacional de publicação multimídia com mais de 20.000 produtos para a ciência educacional e profissional, além de comunidades de saúde em todo o mundo. Com isso, é possível a melhor tomada de decisão para oferecer atendimento de excelência e, por vezes, fazer descobertas inovadoras que avançam as fronteiras do conhecimento e do progresso humano.');
	  	
        
	-- insert reservas
	
	INSERT INTO reservas(statusReserva, horaReserva, dataReserva, dataLimite, idCliente, idLivro) VALUES ('ATIVA', '19:00:00','2019-08-13','2019-08-15',1000,1001);
	
	INSERT INTO reservas(statusReserva, horaReserva, dataReserva, dataLimite, idCliente, idLivro) VALUES ('ATIVA', '19:05:00','2019-08-13','2019-08-15',1001,1000);
	
	-- insert locacoes
	
		
	INSERT INTO locacoes(statusLocacao, quantidadeDeRenovacao, horaLocacao, dataLocacao, dataPrevisaoTerminoLocacao, horaEncerramento, dataEncerramento, idCliente, idLivro) values ('ATIVA', 1,'19:09:32','2019-12-30','2020-01-20',NULL,NULL ,1003,1003);
	
	INSERT INTO locacoes(statusLocacao, quantidadeDeRenovacao, horaLocacao, dataLocacao, dataPrevisaoTerminoLocacao, horaEncerramento, dataEncerramento, idCliente, idLivro) values ('ATIVA', 1, '19:15:11','2019-12-30','2020-01-20',NULL,NULL,1004,1004);
		
	
 -- insert client_web_mobile Apps		
	
	
	INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  	VALUES ('angular', '{bcrypt}$2a$10$eEPHedaBm5onHbuHxQu3COtr8t7z4KqTIfLbNCaNhIShhJNuvvRJO', 'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);	
  
  
  	INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  	VALUES ('mobile', '{bcrypt}$2a$10$uyQJCv.8OMNpBUQB5hXHNuFped8lLqlhsH8uFwTQARDJBDMtlvQ7q', 'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

	
  	-- insert usuarios 
	
	INSERT INTO usuario (id, nome, email, senha) values (1000, 'admin', 'admin@bibliotec.com', '{bcrypt}$2a$10$vxFcjinyrbuDiNPgWNT0hevwdqdLldfsndwr417UCAyqvof8PeNIW');
	INSERT INTO usuario (id, nome, email, senha) values (1001, 'Pedro Leite Soares', 'pedro@gmail.com', '{bcrypt}$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

	INSERT INTO permissao (id, descricao) values (1000, 'ROLE_CADASTRAR_LIVRO');
	INSERT INTO permissao (id, descricao) values (1001, 'ROLE_PESQUISAR_LIVRO');
	INSERT INTO permissao (id, descricao) values (1002, 'ROLE_REMOVER_LIVRO');
	
	INSERT INTO permissao (id, descricao) values (1003, 'ROLE_CADASTRAR_PESSOA');
	INSERT INTO permissao (id, descricao) values (1004, 'ROLE_REMOVER_PESSOA');
	INSERT INTO permissao (id, descricao) values (1005, 'ROLE_PESQUISAR_PESSOA');
	
	INSERT INTO permissao (id, descricao) values (1006, 'ROLE_CADASTRAR_LOCACAO');
	INSERT INTO permissao (id, descricao) values (1007, 'ROLE_REMOVER_LOCACAO');
	INSERT INTO permissao (id, descricao) values (1008, 'ROLE_PESQUISAR_LOCACAO');
	
	INSERT INTO permissao (id, descricao) values (1009, 'ROLE_CADASTRAR_RESERVA');
	INSERT INTO permissao (id, descricao) values (1010, 'ROLE_REMOVER_RESERVA');
	INSERT INTO permissao (id, descricao) values (1011, 'ROLE_PESQUISAR_RESERVA');

	
	INSERT INTO permissao (id, descricao) values (1012, 'ROLE_CADASTRAR_CLIENT_APP');
	INSERT INTO permissao (id, descricao) values (1013, 'ROLE_REMOVER_CLIENT_APP');
	INSERT INTO permissao (id, descricao) values (1014, 'ROLE_PESQUISAR_CLIENT_APP');
	
		
-- EM CASO DE NECESSIDDE PODE-SE CRIAR ROLES PARA CADASTRO DE CATEGORIAS, EDITORAS E AUTORES... CONTUDO O AUTOR ACHU DESNECESSARIO TAL PROCESSO DEVIDO O LIVRO CONTER COMO PRIPRIEDADES ESSENCIAS ESSES SUPRACITADOS ATRIBUTOS....
	
		-- USER PEDRO
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 1001);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 1006);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 1009);
	
	
	-- USER ADMIN DO SISTEMA
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1000);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1001);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1002);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1003);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1004);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1005);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1006);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1007);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1008);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1009);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1010);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1011);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1012);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1013);
	INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1000, 1014);


		
		
		
		
	
	
SET FOREIGN_KEY_CHECKS = 1; 