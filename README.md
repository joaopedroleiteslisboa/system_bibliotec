
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
	
	RequestMethod.POST >>> Type: application/json >>> 	URI: 127.0.0.1:8080/api/clientes/
	Body >>> {
		 {           
		            
		            "tipoCliente": "FISICA",
		            "nome": "João Pedro Leite S Lisboa",
		            "sobreNome": "Leite Soares",
		            "genero": "MASCULINO",
		            "cpf": "12669673627",
		            "dataNascimento": "1996-03-10",
		            "contato": {
		                "celular": "83999630573",
		                "telefoneResidencial": "83999630555",
		                "email_1": "joaopedroleite.s.lisboa@outlook.com",
		                "email_2": "joaopedroleite.s.lisboa.info@gmail.com"
		            },
		            "endereco": {
		                
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
     
       RequestMethod.GET >>>  params: key = id  value = http://127.0.0.1:8080/api/clientes/find/?id=1004
       
    
    
     * Buscando clientes com paginação
      
     
       RequestMethod.GET >>> 127.0.0.1:8080/api/clientes?page=3&size=2
       
       
       

	* Buscando clientes por cpf;
     
      RequestMethod.GET >>> params: key = cpf  value = numeroDeUmCPFValidoPresenteNoBancoDeDados  
      			http://127.0.0.1:8080/api/clientes/find/doc?cpf=03317970200
      		
      		
      			    
    
    * Buscando clientes por intervalo de data de Aniversario
    	
      RequestMethod.GET >>> Params: dataNascimentoDe & dataNascimentoAte >>> 
      			127.0.0.1:8080/api/clientes?dataNascimentoDe=1996-03-10&dataNascimentoAte=2001-10-04
      			
      		
   	* Atualizando Propriedade CPF de um cliente
    	
      RequestMethod.PUT >>> @PathVariable = id 	127.0.0.1:8080/api/clientes/1000/doc
      
      
    * Atualizando Propriedade Endereco de um cliente
    	
      RequestMethod.PUT >>> params: key = cpf  value = numeroDeUmCPFValidoPresenteNoBancoDeDados
      	
      		URI: 	127.0.0.1:8080/api/clientes/end?cpf=09104537483
     
     Body >>> 
		            
    {
        "id": 1000,
        "cep": "58100432",
        "logradouro": "Endereço atualizado João Pedro Leite S LISBOA",
        "numero": "347 A | APT 433",
        "complemento": "Edificio - La casa de Papel",
        "bairro": "Santa Catarina",
        "cidade": "João Pessoa",
        "uf": "PB",
        "ibge": "2599907"
    }	
    
        
    
				*  >>>>> Operações com LIVROS <<<<<
    
    
    
     
     * Salvando um livro na Base de dados
	
	RequestMethod.POST >>> Type: application/json >>> 	URI: 127.0.0.1:8080/api/livros/
	Body >>> {	

    "nome": "RESTful API Design Part II",
    "autores": [
        {
            "id": 1006
        }
    ],
    "editora": {
        	
        	"id": 1006
    },
    "edicao": "Ed. 1ª",
    "statusLivro": "LIVRE",
    "idioma": "INGLES",
    "categorias": [
        {
            "id": 1000
           
        }
     
    ],
    "descricao": "Looking for Best Practices for RESTful APIs? This book is for you! Why? Because this book is packed with practical experience on what works best for RESTful API Design. You want to design APIs like a Pro? Use API description languages to both design APIs and develop APIs efficiently. The book introduces the two most common API description languages RAML, OpenAPI, and Swagger. Your company cares about its customers? Learn API product management with a customer-centric design and development approach for APIs. Learn how to manage APIs as a product and how to follow an API-first approach. Build APIs your customers love! You want to manage the complete API lifecycle? An API development methodology is proposed to guide you through the lifecycle: API inception, API design, API development, API publication, API evolution, and maintenance. You want to build APIs right? This book shows best practices for REST design, such as the correct use of resources, URIs, representations, content types, data formats, parameters, HTTP status codes, and HTTP methods. Your APIs connect to legacy systems? The book shows best practices for connecting APIs to existing backend systems. Your APIs connect to a mesh of microservices? The book shows the principles for designing APIs for scalable, autonomous microservices. You expect lots of traffic on your API? The book shows you how to achieve high performance, availability and maintainability. You want to build APIs that last for decades? We study API versioning, API evolution, backward- and forward-compatibility and show API design patterns for versioning. The API-University Series is a modular series of books on API-related topics. Each book focuses on a particular API topic, so you can select the topics within APIs, which are relevant for you.",
    "isbn13": "978-1514735169",
    "numeroPaginas": 296,
    "dataPublicacao": "25-08-2016",
    "valorUnitario": 15,
    "quantidade": 100
}
    
     * Buscando livro por Id (do banco de dados);
     
       RequestMethod.GET >>> @PathVariable   http://127.0.0.1:8080/api/livros/1013
       
    
    
     * Buscando Livros com paginação      
         
       RequestMethod.GET >>> 127.0.0.1:8080/api/livros?page=3&size=2
       
     
     
      * Buscando livros com resumo de json       
   		    
   		     RequestMethod.GET >>> 127.0.0.1:8080/api/livros?resumo
   		        		     
   		       			    
    
    * Buscando livros por intervalo de data de Publicação 
    	
      RequestMethod.GET >>> Params: dataPublicacaoDe & dataPublicacaoAte >>> 
      			
      			127.0.0.1:8080/api/livros?dataPublicacaoDe=2015-07-10&dataPublicacaoAte=2017-07-10
    
    
    
    
    * Buscando livros por intervalo de data de Publicação e resumido
    	
      RequestMethod.GET >>> Params: dataPublicacaoDe & dataPublicacaoAte >>> 
      
      			127.0.0.1:8080/api/livros?dataPublicacaoDe=2015-07-10&dataPublicacaoAte=2017-07-10&resumo
    
    
        
					*  >>>>> Estrutura do Retorno de Erros do Projeto <<<<<     
     EX.:     
[
    {
        "mensagemUsuario": "Livro Reservado. Operação não permitida",
        "mensagemDesenvolvedor": "LivroReservadoException: O livro selecionado estar Reservado. Operação não Realizada"
    }
]
     
