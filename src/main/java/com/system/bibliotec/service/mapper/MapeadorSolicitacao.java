package com.system.bibliotec.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.system.bibliotec.exception.ErrorInternoException;
import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.StatusProcessamento;
import com.system.bibliotec.model.enums.TipoSolicitacao;
import com.system.bibliotec.service.UserService;
import com.system.bibliotec.service.dto.SolicitacaoLocacaoDTO;
import com.system.bibliotec.service.vm.SolicitacaoVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapeadorSolicitacao {
    
    @Autowired
    private final UserService userService;



    public MapeadorSolicitacao(UserService userService) {
		this.userService = userService;
    }
    
    public SolicitacaoVM entytiParaEntidadeVM(Solicitacoes s) {
        return new SolicitacaoVM(s);
    }

    public SolicitacaoVM entytiParaEntidadeVM(Solicitacoes s, String nomeExemplar) {
        return new SolicitacaoVM(s, nomeExemplar);
    }

   
    public List<SolicitacaoVM> entytiParaEntidadeVM(List<Solicitacoes> r) {
        return r.stream().filter(Objects::nonNull).map(this::entytiParaEntidadeVM).collect(Collectors.toList());
    }


    public Solicitacoes dtoLocacaoParaEntidade(SolicitacaoLocacaoDTO dto){

        Solicitacoes entity = new Solicitacoes();

        entity.setDataRetiradaExemplar(dto.getDataRetiradaExemplar());
        entity.setHoraRetiradaExemplar(dto.getHoraRetiradaExemplar());
        entity.setIdExemplar(dto.getIdLivro());
        entity.setRejeitado(false);
        entity.setStatus(Status.EM_ANALISE);
        entity.setTipo(TipoSolicitacao.LOCACAO);
        entity.setStatusProcessamento(StatusProcessamento.NAO_HABILITADO_A_PROCESSAMENTO);
        
        try {
        entity.setUsuario(userService.findOneByUsuarioContexto());
       } catch (Exception e) {
           throw new ErrorInternoException("Error interno da Aplicação. Por gentileza informar a um Atendente do System biblitec", e);
       } 
       
        return entity;
    }

	

    


}