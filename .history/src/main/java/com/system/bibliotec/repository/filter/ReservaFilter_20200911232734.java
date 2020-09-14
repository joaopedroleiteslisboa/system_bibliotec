package com.system.bibliotec.repository.filter;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import com.system.bibliotec.model.enums.Status;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservaFilter {


	private Status status;

	private LocalTime horaReserva;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReservaInicio;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReservaFim;

	



	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataPrevisaoTermino;

	
}
