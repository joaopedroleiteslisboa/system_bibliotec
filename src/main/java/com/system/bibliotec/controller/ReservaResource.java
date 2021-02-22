package com.system.bibliotec.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.controller.util.EndPointUtil;
import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.repository.filter.ReservaFilter;
import com.system.bibliotec.service.ReservaService;
import com.system.bibliotec.service.dto.CancelamentoReservaDTO;

import com.system.bibliotec.service.dto.SolicitacaoReservaDTO;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;

@RestController
@RequestMapping(value = "atendimento/reserva", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReservaResource {


    private final ReservaRepository reservaRepository;


    private final ReservaService reservaService;


    private final ApplicationEventPublisher publisher;


    private final EndPointUtil endPointUtil;

    @Autowired
    public ReservaResource(ReservaRepository reservaRepository, ReservaService reservaService,
                           ApplicationEventPublisher publisher, EndPointUtil endPointUtil) {
        this.reservaRepository = reservaRepository;
        this.reservaService = reservaService;
        this.publisher = publisher;
        this.endPointUtil = endPointUtil;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER_ANONIMO','ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReservaVM> pesquisar(ReservaFilter filter) {

        return reservaService.filterQuery(filter);

    }


    @PreAuthorize("hasAnyRole('ROLE_CADASTRAR_RESERVA','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<ReservaVM> create(@Valid @RequestBody SolicitacaoReservaDTO reservaDTO, HttpServletResponse response) {

        ReservaVM reservaSalved = reservaService.reservaLivro(reservaDTO);

        publisher.publishEvent(new RecursoCriadorEvent(this, response, reservaSalved.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalved);

    }

    @PreAuthorize("hasAnyRole('ROLE_CADASTRAR_RESERVA','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('write')")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReservaCanceladaVM cancelarReserva(@Valid @RequestBody CancelamentoReservaDTO dto) {

        return reservaService.cancelarReserva(dto.getIdReserva());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('read')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return endPointUtil.returnObjectOrNotFound(reservaService.findByIdReservaDoUsuario(id));

    }

}
