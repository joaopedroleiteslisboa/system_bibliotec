package com.system.bibliotec.service.operacoes;

import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.service.dto.CancelamentoLocacaoDTO;
import com.system.bibliotec.service.dto.DevolucaoLocacaoDTO;
import com.system.bibliotec.service.dto.DespachoSolicitacaoLocacaoDTO;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;
import com.system.bibliotec.service.validation.IValidaDataOperacao;
import com.system.bibliotec.service.vm.LocacaoCancelamentoVM;
import com.system.bibliotec.service.vm.LocacaoDevolucaoVM;
import com.system.bibliotec.service.vm.LocacaoVM;

public interface IOperacaoLocacao extends IValidaDataOperacao, IAuditorTokenDeUsuarioDoContexto{

	
	public LocacaoVM despacharPedidoLocacao(DespachoSolicitacaoLocacaoDTO locacao);
	
	public void renovarLocacao(Long id);
		
	public LocacaoCancelamentoVM cancelarLocacao(CancelamentoLocacaoDTO dto);
	
	public LocacaoDevolucaoVM encerramento(DevolucaoLocacaoDTO dto);
	
	public void updatePropertyStatusLocacao(Long idLocacao, Status statusLocacao);


	


	

	
	
	
	

	
}
