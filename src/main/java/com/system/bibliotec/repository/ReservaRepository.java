package com.system.bibliotec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	boolean existsById(Long id);

	Optional<Reserva> findById(Long idReserva);

	void deleteById(Long id);
}
