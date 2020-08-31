package com.system.bibliotec.service.vm;

import com.system.bibliotec.model.Locacoes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter /* View Model */
public class LocacaoDevolucaoVM extends LocacaoVM {

	private String observacoesDevolucao;
	private String horaEncerramento;
	private String dataEncerramento;

	public LocacaoDevolucaoVM(Locacoes entidade) {
		super(entidade);
		this.observacoesDevolucao = entidade.getObservacoesDevolucao();
		this.dataEncerramento = entidade.getDataEncerramento().toString();
		this.horaEncerramento = entidade.getHoraEncerramento().toString();

	}

}
