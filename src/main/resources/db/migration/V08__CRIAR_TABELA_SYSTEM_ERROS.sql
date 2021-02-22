CREATE TABLE IF NOT EXISTS TB_EXCECAO_SISTEMA (

                                        id        bigint  primary key AUTO_INCREMENT,
                                        resolvido  	     boolean   DEFAULT FALSE ,
                                        metodo_error      VARCHAR(150) 	,
                                        usuario_error     VARCHAR(150) 	,
                                        id_user           VARCHAR(150) 	,
                                        classs            VARCHAR(150) 	,
                                        ds_descricao      VARCHAR(3000) ,
                                        ds_operacao       VARCHAR(3000) ,
                                        tipo_excecao      VARCHAR(150)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;


