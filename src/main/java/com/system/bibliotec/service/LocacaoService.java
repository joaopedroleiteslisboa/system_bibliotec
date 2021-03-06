package com.system.bibliotec.service;

import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.repository.filter.LocacaoFilter;
import com.system.bibliotec.repository.specification.LocacaoSpecification;
import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.security.SecurityUtils;

import java.util.List;
import java.util.Optional;

import com.system.bibliotec.security.UserSystem;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.system.bibliotec.exception.LocacaoInexistenteException;
import com.system.bibliotec.service.dto.AtendimentoLocacaoDTO;
import com.system.bibliotec.service.dto.CancelamentoLocacaoDTO;
import com.system.bibliotec.service.dto.DevolucaoLocacaoDTO;
import com.system.bibliotec.service.dto.DespachoSolicitacaoLocacaoDTO;
import com.system.bibliotec.service.mapper.MapeadorLocacao;
import com.system.bibliotec.service.operacoes.IOperacaoLocacao;
import com.system.bibliotec.service.vm.LocacaoCancelamentoVM;
import com.system.bibliotec.service.vm.LocacaoDevolucaoVM;
import com.system.bibliotec.service.vm.LocacaoVM;

@Service
public class LocacaoService {

    private final IOperacaoLocacao operacao;

    private final MapeadorLocacao mapper;

    private final LocacaoRepository locacaoRepository;

    @Autowired
    public LocacaoService(IOperacaoLocacao operacao, MapeadorLocacao mapper, LocacaoRepository locacaoRepository) {

        this.operacao = operacao;
        this.mapper = mapper;
        this.locacaoRepository = locacaoRepository;
    }

    public LocacaoVM atenderLocacao(AtendimentoLocacaoDTO dto) {

        return operacao.atenderLocacao(dto);

    }


    public LocacaoVM despacharLocacao(DespachoSolicitacaoLocacaoDTO locacao) {

        return operacao.despacharPedidoLocacao(locacao);

    }

    public void renovarLocacao(Long id) {
        operacao.renovarLocacao(id);
    }

    public LocacaoCancelamentoVM cancelarLocacao(CancelamentoLocacaoDTO dto) {
        return operacao.cancelarLocacao(dto);

    }

    public LocacaoDevolucaoVM devolucaoLivro(DevolucaoLocacaoDTO dto) {

        return operacao.encerramento(dto);
    }

    public void updatePropertyStatusLocacao(Long id, Status statusLocacao) {
        operacao.updatePropertyStatusLocacao(id, statusLocacao);
    }

    public List<LocacaoVM> findAllByLocacaoDoUsuario(Pageable pageable) {

        return mapper.locacaoParaLocacaoVM(locacaoRepository.findAllGenericObjectToUser(pageable).orElseThrow(
                () -> new LocacaoInexistenteException("Não foi localizada nenhum registro em nossa Base de dados")));
    }

    public LocacaoVM findByIdLocacao(Long id) {

        return locacaoRepository.findOneGenericObjectToUser(id).map(mapper::locacaoParaLocacaoVM)
                .orElseThrow(() -> new LocacaoInexistenteException("Locação não localizada em nossos registros"));

    }

    public List<LocacaoVM> filterQuery(LocacaoFilter filter) {

        Optional<UserSystem> usuarioAnonimo = Optional.empty();


        if (!SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_ADMIN) || //caso não for ADM ou USER SYSTEM listar apenas os dados vinculado ao principal online
                !SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_USER_SYSTEM)) {

            usuarioAnonimo = SecurityUtils.getCurrentUserPrincipal();

            if (usuarioAnonimo.isPresent()) {

                filter.setIdUsuario(usuarioAnonimo.get().getId());
            }

        }


        Specification<Locacoes> query = Specification.where(

                LocacaoSpecification.porID(filter.getIdLocacao()))

                .and(LocacaoSpecification.porUsuarioContexto(filter.getCreatedBy()))

                .and(LocacaoSpecification.porIntervaloDataCriacao(filter.getDataLocacaoInicio(),
                        filter.getDataLocacaoFim()))
                .and(LocacaoSpecification.porIntervaloHoraCriacao(filter.getHoraLocacaoInicio(),
                        filter.getHoraLocacaoFim()))

                .and(LocacaoSpecification.porIntervaloDataCancelamento(filter.getDataCancelamentoLocacaoInicio(),
                        filter.getDataCancelamentoLocacaoFim()))
                .and(LocacaoSpecification.porIntervaloHoraCancelamento(filter.getHoraCancelamentoLocacaoInicio(),
                        filter.getHoraCancelamentoLocacaoFim()))

                .and(LocacaoSpecification.porStatus(filter.getStatusLocacao()))

                .and(LocacaoSpecification.porLivroId(filter.getIdExemplar()))

                .and(LocacaoSpecification.porUsuarioId(filter.getIdUsuario())
                );
        // fim query

        return mapper.locacaoParaLocacaoVM(locacaoRepository.findAll(query));

    }

}
