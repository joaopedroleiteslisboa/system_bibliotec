package com.system.bibliotec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bibliotec.model.Reserva;



public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	boolean existsById(Long id);

	Optional<Reserva> findById(Long idReserva);

	void deleteById(Long id);
}
