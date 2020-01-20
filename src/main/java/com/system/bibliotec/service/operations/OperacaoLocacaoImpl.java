package com.system.bibliotec.service.operations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.LocacaoInvalidaOuInexistenteException;
import com.system.bibliotec.exception.QuantidadeRenovacaoLocacaoLimiteException;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Locacao;
import com.system.bibliotec.model.enums.StatusLocacao;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.service.ClienteService;
import com.system.bibliotec.service.LivroService;
import com.system.bibliotec.service.ultis.HoraDiasDataLocalService;
import com.system.bibliotec.service.validation.IValidaCliente;
import com.system.bibliotec.service.validation.IValidaDataLocacao;
import com.system.bibliotec.service.validation.IValidaLivro;
import com.system.bibliotec.service.validation.IvalidaLocacao;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OperacaoLocacaoImpl implements IOperacaoLocacao {

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private IValidaCliente validaCliente;

	@Autowired
	private LivroService livroService;

	private LivroRepository livroRepository;

	@Autowired
	private IValidaLivro validadorLivro;

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private IvalidaLocacao validadorLocacao;

	@Autowired
	private IValidaDataLocacao validadorDataLocacao;

	@Transactional
	@Override
	public Locacao realizarLocacao(Locacao locacao) {
		// TODO Auto-generated method stub
		log.info("Iniciando Processo de Locação de livro:" + locacao);

		validadorLocacao.validaLocacao(locacao);

		locacao.setHoraLocacao(HoraDiasDataLocalService.horaLocal());

		locacao.setDataLocacao(HoraDiasDataLocalService.dataLocal());

		locacao.setDataPrevisaoTerminoLocacao(HoraDiasDataLocalService.dataLocacaoDevolucao());

		locacao.setStatusLocacao(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_ATIVA);

		locacao.setQuantidadeDeRenovacao(ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_LOCACAO_INICIAL);

		livroService.updateStatusLivro(locacao.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LOCADO);

		livroService.decrescentarEstoque(locacao.getLivro().getId(),
				ConstantsUtils.DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO);

		log.info("Locação realizada:" + locacao);

		return locacaoRepository.save(locacao);
	}

	@Transactional
	@Override
	public void renovarLocacao(Long id) {
		// TODO Auto-generated method stub

		Locacao locacaoSalva = findByIdLocacao(id);

		log.info("Iniciando Processo de Renovação de Locação de livro:" + locacaoSalva);

		validadorDataLocacao.validaDataLocacao(locacaoSalva);

		validaCliente.validaCliente(locacaoSalva.getCliente());

		int quantidadeRenovada = locacaoSalva.getQuantidadeDeRenovacao();

		if (quantidadeRenovada >= ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_RENOVACAO_MAXIMA_LOCACAO) {
			throw new QuantidadeRenovacaoLocacaoLimiteException(
					"Quantidade de Renovação maxima atingida. Operação não realizada");
		}

		locacaoSalva.setQuantidadeDeRenovacao(quantidadeRenovada + ConstantsUtils.DEFAULT_VALUE_QUANTIDADE_LOCACAO);

		locacaoSalva.setDataPrevisaoTerminoLocacao(
				HoraDiasDataLocalService.dataRenovacaoLocacao(locacaoSalva.getDataPrevisaoTerminoLocacao()));

		locacaoRepository.save(locacaoSalva);

		log.info("Processo de Renovação de Locação de livro realizada:" + locacaoSalva);
	}

	@Transactional
	@Override
	public void updatePropertyLivro(Long idLocacao, Long idNovoLivro) {
		// TODO Auto-generated method stub

		Locacao locacaoSalva = findByIdLocacao(idLocacao);

		Livro livroSalvo = livroService.findByIdLivro(idNovoLivro);

		log.info("Iniciando Processo de Atualização de Locação: Atualizando propriedade livro:" + locacaoSalva);

		validadorDataLocacao.validaDataLocacao(locacaoSalva);

		validadorLivro.validaLivro(livroSalvo);

		validaCliente.validaCliente(locacaoSalva.getCliente());

		livroService.updateStatusLivro(locacaoSalva.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LIVRE);

		livroService.acrescentarEstoque(locacaoSalva.getLivro().getId(),
				ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);

		livroService.updateStatusLivro(livroSalvo.getId(), ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LOCADO);

		livroService.acrescentarEstoque(livroSalvo.getId(),
				ConstantsUtils.DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO);

		locacaoSalva.setLivro(livroSalvo);

		locacaoRepository.save(locacaoSalva);

		log.info("Processo de Atualização da pripriedade Livro da  Locação:" + locacaoSalva + "Atualizada com Sucesso");
	}

	@Transactional
	@Override
	public void updatePropertyCliente(Long idLocacao, String cpfNovoCliente) {
		// TODO Auto-generated method stub

		Cliente clienteSalvo = servicoCliente.findByCpfCliente(cpfNovoCliente);

		Locacao locacaoSalva = findByIdLocacao(idLocacao);

		log.info("Iniciando Processo de Atualização de Locação: Atualizando propriedade Cliente:" + locacaoSalva);

		validadorDataLocacao.validaDataLocacao(locacaoSalva);

		validaCliente.validaCliente(clienteSalvo);

		locacaoSalva.setCliente(clienteSalvo);

		locacaoRepository.save(locacaoSalva);

		log.info("Processo de Atualização de Cliente da locação: " + locacaoSalva + " Realizada com sucesso");
	}

	@Override
	public void checarDataLimiteAtingida() {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void cancelarLocacao(Long id) {
		// TODO Auto-generated method stub

		Locacao locacaoSalva = findByIdLocacao(id);
		log.info("Iniciando Processo de Cancelamento de Locação: " + locacaoSalva);

		validaCliente.validaCliente(locacaoSalva.getCliente());

		validadorDataLocacao.validaDataLocacao(locacaoSalva);

		locacaoSalva.setHoraEncerramento(HoraDiasDataLocalService.horaLocal());

		locacaoSalva.setDataEncerramento(HoraDiasDataLocalService.dataLocal());

		locacaoSalva.setStatusLocacao(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_CANCELADA);

		livroService.acrescentarEstoque(locacaoSalva.getLivro().getId(),
				ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);

		// TODO: Usando repositorio livro para poder atualizar o status de forma direta
		// no banco de dados
		livroRepository.updateStatusLivro(ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LIVRE,
				locacaoSalva.getLivro().getId());

		locacaoRepository.save(locacaoSalva);

		log.info("Locação cancelada:" + locacaoSalva);

	}

	@Transactional
	@Override
	public void devolucaoLivro(Long idLocacao) {
		// TODO Auto-generated method stub

		Locacao locacaoSalva = findByIdLocacao(idLocacao);

		log.info("Iniciando Processo de Devolução  e finalização de Locacao:" + locacaoSalva);

		locacaoSalva.setHoraEncerramento(HoraDiasDataLocalService.horaLocal());

		locacaoSalva.setDataEncerramento(HoraDiasDataLocalService.dataLocal());

		locacaoSalva.setStatusLocacao(ConstantsUtils.DEFAULT_VALUE_STATUSLOCACAO_FINALIZADA);

		livroService.acrescentarEstoque(locacaoSalva.getLivro().getId(),
				ConstantsUtils.DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO);

		livroService.updateStatusLivro(locacaoSalva.getLivro().getId(), ConstantsUtils.DEFAULT_VALUE_STATUSLIVRO_LIVRE);

		locacaoRepository.save(locacaoSalva);

	}

	@Transactional
	@Override
	public void updatePropertyStatusLocacao(Long idLocacao, StatusLocacao statusLocacao) {
		// TODO Auto-generated method stub

		Locacao locacaoSalva = findByIdLocacao(idLocacao);

		log.info("Iniciando Processo de atualização da Propriedade Status da locação:" + locacaoSalva);

		validadorDataLocacao.validaDataLocacao(locacaoSalva);

		locacaoSalva.setStatusLocacao(statusLocacao);

		locacaoRepository.save(locacaoSalva);

	}

	@Override
	public Locacao findByIdLocacao(Long id) {
		// TODO Auto-generated method stub
		Optional<Locacao> locacaoSalva = locacaoRepository.findById(id);
		if (!locacaoSalva.isPresent()) {
			throw new LocacaoInvalidaOuInexistenteException("Livro Invalido ou Inexistente. Informe um Livro Valido");
		}
		return locacaoSalva.get();
	}

}