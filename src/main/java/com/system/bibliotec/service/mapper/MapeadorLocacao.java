package com.system.bibliotec.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.service.vm.LocacaoCancelamentoVM;
import com.system.bibliotec.service.vm.LocacaoDevolucaoVM;
import com.system.bibliotec.service.vm.LocacaoVM;

@Component
public class MapeadorLocacao {

	public LocacaoVM locacaoParaLocacaoVM(Locacoes l) {
		return new LocacaoVM(l);
	}

	public List<LocacaoVM> locacaoParaLocacaoVM(List<Locacoes> l) {
		return l.stream().filter(Objects::nonNull).map(this::locacaoParaLocacaoVM).collect(Collectors.toList());
	}

	public LocacaoCancelamentoVM locacaoParaLocacaoCanceladaVM(Locacoes l) {
		return new LocacaoCancelamentoVM(l);
	}

	public List<LocacaoCancelamentoVM> locacaoParaLocacaoCanceladaVM(List<Locacoes> l) {
		return l.stream().filter(Objects::nonNull).map(this::locacaoParaLocacaoCanceladaVM)
				.collect(Collectors.toList());
	}

	public LocacaoDevolucaoVM locacaoParaLocacaoDevolucaoVM(Locacoes l) {
		return new LocacaoDevolucaoVM(l);
	}

	public List<LocacaoDevolucaoVM> locacaoParaLocacaoDevolucaoVM(List<Locacoes> l) {
		return l.stream().filter(Objects::nonNull).map(this::locacaoParaLocacaoDevolucaoVM)
				.collect(Collectors.toList());
	}

}
