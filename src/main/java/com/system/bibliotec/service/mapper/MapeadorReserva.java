package com.system.bibliotec.service.mapper;

import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MapeadorReserva {

    public ReservaVM reservaParaReservaVM(Reservas r) {
        return new ReservaVM(r);
    }

    public ReservaCanceladaVM reservaParaReservaCanceladaVM(Reservas r) {
        return new ReservaCanceladaVM(r);
    }

    public List<ReservaVM> reservaParaReservaVM(List<Reservas> r) {
        return r.stream().filter(Objects::nonNull).map(this::reservaParaReservaVM).collect(Collectors.toList());
    }

    public List<ReservaCanceladaVM> reservaParaReservaCanceladaVM(List<Reservas> r) {
        return r.stream().filter(Objects::nonNull).map(this::reservaParaReservaCanceladaVM).collect(Collectors.toList());
    }


}
