package com.system.bibliotec.service.vm;

/**
 * SolicitacaoVM
 */
public class SolicitacaoVM {

    


 
    private String tipo;

    
    private String horaSolicitacao;

   
    private String dataSolicitacao;

    
    private String dataRetiradaExemplar;

    
    private String horaRetiradaExemplar;


    private String nomeExeplar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "rejeitado")
    private boolean rejeitado = false;

    @NotNull(message = "Solicitação Precisa ter um usuario vinculado")
	@ManyToOne	
	@JoinColumn(name = "idUsuario")
    private Usuario usuario;
    

}