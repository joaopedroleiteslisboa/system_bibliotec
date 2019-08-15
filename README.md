
### Sistema - API Gestão de Livraria 
Simplista exemplo de um sistema para Gerenciamento de uma livraria.

### Sobre o Projeto 

O supracitado Projeto tem como objetivo geral, realizar CRUD de clientes e de livros 
e Dispor de funcionabilidades de Solicitação de Reserva de Livro, Emprestimo de Livro
e entre outras funcionabilidades.

### Apresentação Técnica.

Escrito seguindo implementações Java, SpringBoot e Angular.

    
### Tecnologias e Recursos utilizados - Back-End.
    
    * Java 1.8^^
    
    * SpringBoot 2.1.5.RELEASE
 
    * Flyway     
    
 	* Hibernate / Hibernate-jpamodelgen
    
    * jackson-datatype-jsr310 / jackson-databind 

    * apache.commons

    * E entre outros Recursos para funcionamento e disposição da Regrade Negocio.
    
    

### Tecnologias e Recursos utilizados - Front-End.
    
		Em desenvolvimento...



### Autor

Nome: João Pedro Leite S Lisboa.

Linkedin: http://bit.ly/linkedinjoaopedroleiteslisboa


### STATUS DE ANDAMENTO DO PROJETO.

	   Em desenvolvimento...

### Instruções para funcionamento - 


	* Este Projeto possui dois perfis de Execução (application-dev e application-production), 
	Contudo o application-production vem apresentando melhor eficiência no quesito consultas mais aprimoradas devido certas incompatibilidades do h2database...
	
	Desta forma, Lembre-se de adicionar o nome de seu usuario e senha do Banco de dados ( Preferencialmente Mysql).
	
	
### Instruções para uso -	Segue alguns, de varios paths para requisições...  

	*  >>>>> Operações com CLIENTES <<<<<
	
	* Salvando um cliente na Base de dados
	
	RequestMethod.POST >>> Type: application/json >>> 
	
	Body >>> {
	   {           
            
            "tipoCliente": "FISICA",
            "nome": "João Pedro Leite S Lisboa",
            "sobreNome": "Leite Soares",
            "genero": "MASCULINO",
            "cpf": "75573454102",
            "dataNascimento": "1996-03-10",
            "contato": {
                "celular": "83999630573",
                "telefoneResidencial": "83999630555",
                "email_1": "joaopedroleite.s.lisboa@outlook.com",
                "email_2": "joaopedroleite.s.lisboa.info@gmail.com"
            },
            "idEndereco": {
                
                "cep": "58100433",
                "logradouro": "Rua: Tentando Localizar",
                "numero": "333",
                "complemento": "Residencial: Drumounts",
                "bairro": "Santa Catarina",
                "cidade": "João Pessoa",
                "uf": "PB",
                "ibge": "2503209"
            }
        }
        
        
     * Buscando clientes por Id (do banco de dados);
     
       RequestMethod.GET >>> http://127.0.0.1:8080/api/clientes/find/cod/1001
       
    
     * Buscando clientes com paginação
      
     
       RequestMethod.GET >>> 127.0.0.1:8080/api/clientes?page=3&size=2
       

	* Buscando clientes por cpf;
     
      RequestMethod.GET >>> Headers: key = cpf  value = numeroDeUmCPFValidoPresenteNoBancoDeDados  
      			127.0.0.1:8080/api/clientes/find/doc
      			    
    
    * Buscando clientes por intervalo de data de Aniversario
    	
      RequestMethod.GET >>> Params: dataNascimentoDe & dataNascimentoAte >>> 
      			127.0.0.1:8080/api/clientes?dataNascimentoDe=1996-03-10&dataNascimentoAte=2001-10-04
     
