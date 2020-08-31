package com.system.bibliotec.service.vm;

import com.system.bibliotec.model.Locacoes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter /* View Model */
public class LocacaoCancelamentoVM extends LocacaoVM {

	private String horaCancelamento;
	private String dataCancelamento;

	public LocacaoCancelamentoVM(Locacoes entidade) {
		super(entidade);

		this.dataCancelamento = entidade.getDataCancelamento().toString();
		this.horaCancelamento = entidade.getHoraCancelamento().toString();

	}

}
