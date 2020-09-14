package com.system.bibliotec.repository.filter;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservaFilter {

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReserva;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataLimite;

	
}
