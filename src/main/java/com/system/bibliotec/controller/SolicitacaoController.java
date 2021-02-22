package com.system.bibliotec.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import com.system.bibliotec.repository.SolicitacaoRepository;
import com.system.bibliotec.repository.filter.SolicitacaoFilter;
import com.system.bibliotec.service.SolicitacaoService;
import com.system.bibliotec.service.dto.FormCancelamentoSolicitacaoLocacao;
import com.system.bibliotec.service.dto.SolicitacaoLocacaoDTO;
import com.system.bibliotec.service.vm.SolicitacaoVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * SolicitacaoController
 */

@RestController
@RequestMapping(value = "/solicitacao")
public class SolicitacaoController {


    @Autowired
    private final SolicitacaoService service;


    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER_ANONIMO','ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('read')")
    @GetMapping
    public List<SolicitacaoVM> pesquisar(SolicitacaoFilter filter) {

        return service.filterQuery(filter);
    }

    @PostMapping("/locacao")
    @PreAuthorize("hasAnyRole('ROLE_USER_ANONIMO' ) and #oauth2.hasScope('write')")
    public ResponseEntity<SolicitacaoVM> solicitarLocacao(@RequestBody @Valid SolicitacaoLocacaoDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.solicitarLocacao(dto));
    }


    @PutMapping("/cancelamento/locacao")
    @PreAuthorize("hasAnyRole('ROLE_USER_ANONIMO' ) and #oauth2.hasScope('write')")
    public ResponseEntity<?> cancelarSolicitacaoLocacao(@RequestBody @Valid FormCancelamentoSolicitacaoLocacao dto) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.solicitarCancelamentoDeSolicitacaoLocacao(dto));
    }


    //Implementar controller de edição de solicitação


    //implementar solicitação de mudança de documentos


}



